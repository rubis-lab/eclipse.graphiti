/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2012 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    Volker Wegert - Bug 336828: patterns should support delete,
 *                    remove, direct editing and conditional palette
 *                    creation entry
 *    mwenz - Bug 325084 - Provide documentation for Patterns
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.pattern.internal.T;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ILinkService;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

/**
 * The class DefaultFeatureProviderWithPatterns is the base class for a feature
 * provider that uses patterns. It is also possible to implement aspects of the
 * functionality triggered here using features by simply returning these
 * features here. In case of pattern-based functionality clients in general need
 * to implement nothing here, because the registered patterns (see
 * {@link #addPattern(IPattern)} and
 * {@link #addConnectionPattern(IConnectionPattern)}) delegate to the pattern
 * automatically.
 */
public class DefaultFeatureProviderWithPatterns extends DefaultFeatureProvider implements IFeatureProviderWithPatterns {

	private List<IPattern> patterns;

	private List<IConnectionPattern> connectionPatters;

	/**
	 * Creates a new instance of {@link DefaultFeatureProviderWithPatterns}.
	 * This is usually done from the diagram type provider.
	 * 
	 * @param dtp
	 *            The diagram type provider associated with this feature
	 *            provider.
	 */
	public DefaultFeatureProviderWithPatterns(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	/**
	 * Adds a pattern defined to handle shapes to the list of registered
	 * patterns. For adding connection-based patterns see
	 * {@link #addConnectionPattern(IConnectionPattern)}. The pattern must not
	 * be <code>null</code>, or a {@link IllegalArgumentException} will be
	 * thrown.
	 * 
	 * @param pattern
	 *            The Pattern to add
	 */
	public void addPattern(IPattern pattern) {
		if (pattern == null) {
			throw new IllegalArgumentException("Argument pattern must not be null."); //$NON-NLS-1$
		}

		pattern.setFeatureProvider(this);
		getPatterns().add(pattern);
	}

	/**
	 * Adds a pattern defined to handle connections to the list of registered
	 * patterns. For adding shape-based patterns see
	 * {@link #addPattern(IPattern)}. The pattern must not be <code>null</code>,
	 * or a {@link IllegalArgumentException} will be thrown.
	 * 
	 * @param pattern
	 *            The Pattern to add
	 */
	public void addConnectionPattern(IConnectionPattern pattern) {
		if (pattern == null) {
			throw new IllegalArgumentException("Argument pattern must not be null."); //$NON-NLS-1$
		}
		pattern.setFeatureProvider(this);
		getConnectionPatterns().add(pattern);
	}

	/**
	 * Returns a new list of the registered patterns that deal with shapes
	 * inside this feature provider.
	 * 
	 * @return A {@link List} of the registered shape-based patterns.
	 */
	protected List<IPattern> getPatterns() {
		if (this.patterns == null) {
			this.patterns = new ArrayList<IPattern>();
		}
		return this.patterns;
	}

	/**
	 * Returns a new list of the registered patterns that deal with sonnection
	 * inside this feature provider.
	 * 
	 * @return A {@link List} of the registered connection-based patterns.
	 */
	protected List<IConnectionPattern> getConnectionPatterns() {
		if (this.connectionPatters == null) {
			this.connectionPatters = new ArrayList<IConnectionPattern>();
		}
		return this.connectionPatters;
	}

	/**
	 * Checks if a feature along with its context is available. Delegates to the
	 * {@link IFeature#isAvailable(IContext)} method.
	 * 
	 * @param feature
	 *            The feature to check
	 * @param context
	 *            The according context
	 * 
	 * @return <code>true</code>, if the feature is available,
	 *         <code>false</code> otherwise.
	 */
	protected boolean checkFeatureAndContext(IFeature feature, IContext context) {
		boolean featureOkay = feature != null;
		boolean featureAvailable = feature.isAvailable(context);
		boolean ret = featureOkay && featureAvailable;
		return ret;
	}

	/**
	 * Tries to retrieve an add feature suiting the given add context from the
	 * registered patterns. First the shape patterns, then the connection
	 * patterns are queried. If no suitable pattern functionality is found the
	 * call is delegated to the super class (via the method
	 * {@link #getAddFeatureAdditional(IAddContext)}).
	 * 
	 * @param context
	 *            An {@link IAddContext} describing the needed functionality
	 * @return An {@link IAddFeature} in case a suitable functionality has been
	 *         found, <code>null</code> otherwise.
	 */
	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		if (context == null) {
			throw new IllegalArgumentException("Argument context must not be null."); //$NON-NLS-1$
		}
		IAddFeature ret = null;
		for (IPattern pattern : getPatterns()) {
			if (checkPattern(pattern, context.getNewObject())) {
				IPattern choosenPattern = null;
				IAddFeature f = new AddFeatureForPattern(this, pattern);
				boolean executable = checkFeatureAndContext(f, context);
				if (executable) {
					if (ret == null) {
						ret = f;
						choosenPattern = pattern;
					} else {
						traceWarning("getAddFeature", pattern, choosenPattern); //$NON-NLS-1$
					}
				}
			}
		}
		if (ret == null) {
			for (IConnectionPattern conPattern : getConnectionPatterns()) {
				if (conPattern.canAdd(context)) {
					return new AddFeatureForPattern(this, conPattern);
				}
			}
		}

		if (ret == null) {
			ret = getAddFeatureAdditional(context);
		}

		return ret;
	}

	/**
	 * Gets the additional add feature functionality. The default implementation
	 * simply delegates to
	 * {@link DefaultFeatureProvider#getAddFeature(IAddContext)}.
	 * 
	 * @param context
	 *            An {@link IAddContext} describing the needed functionality
	 * 
	 * @return An {@link IAddFeature} in case a suitable functionality has been
	 *         found by the super class, <code>null</code> otherwise.
	 */
	protected IAddFeature getAddFeatureAdditional(IAddContext context) {
		return super.getAddFeature(context);
	}

	/**
	 * Retrieves an array of create features that are available from the
	 * registered patterns and the super class of this feature provider. Only
	 * the shape patterns are queried. Then the call is delegated to the super
	 * class (via the method {@link #getCreateConnectionFeaturesAdditional()}).
	 * 
	 * @return An array of {@link ICreateFeature}s in case a suitable
	 *         functionality has been found, an empty array otherwise.
	 */
	@Override
	public ICreateFeature[] getCreateFeatures() {
		ICreateFeature[] ret = new ICreateFeature[0];
		List<ICreateFeature> retList = new ArrayList<ICreateFeature>();

		for (IPattern pattern : getPatterns()) {
			if (pattern.isPaletteApplicable()) {
				retList.add(new CreateFeatureForPattern(this, pattern));
			}
		}

		ICreateFeature[] a = getCreateFeaturesAdditional();
		for (ICreateFeature element : a) {
			retList.add(element);
		}

		return retList.toArray(ret);
	}

	/**
	 * Gets the additional create feature functionality. The default
	 * implementation simply delegates to
	 * {@link DefaultFeatureProvider#getCreateFeatures()}.
	 * 
	 * @return An array of {@link ICreateFeature}s in case a suitable
	 *         functionality has been found by the super class, an empty array
	 *         otherwise.
	 */
	protected ICreateFeature[] getCreateFeaturesAdditional() {
		ICreateFeature[] ret = new ICreateFeature[0];
		List<ICreateFeature> retList = new ArrayList<ICreateFeature>();
		ICreateFeature[] s = super.getCreateFeatures();
		for (ICreateFeature element : s) {
			retList.add(element);
		}
		return retList.toArray(ret);
	}

	/**
	 * Tries to retrieve a delete feature suiting the given delete context from
	 * the registered patterns. Only the shape patterns are queried. If no
	 * suitable pattern functionality is found the call is delegated to the
	 * super class (via the method
	 * {@link #getDeleteFeatureAdditional(IDeleteContext)}).
	 * 
	 * @param context
	 *            An {@link IDeleteContext} describing the needed functionality
	 * @return An {@link IDeleteFeature} in case a suitable functionality has
	 *         been found, <code>null</code> otherwise.
	 */
	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		if (context == null) {
			throw new IllegalArgumentException("Argument context must not be null."); //$NON-NLS-1$
		}
		IDeleteFeature ret = null;
		for (IPattern pattern : getPatterns()) {
			if (checkPattern(pattern, getBusinessObjectForPictogramElement(context.getPictogramElement()))) {
				IPattern choosenPattern = null;
				IDeleteFeature f = new DeleteFeatureForPattern(this, pattern);
				if (checkFeatureAndContext(f, context)) {
					if (ret == null) {
						ret = f;
						choosenPattern = pattern;
					} else {
						traceWarning("getDeleteFeature", pattern, choosenPattern); //$NON-NLS-1$
					}
				}
			}
		}

		if (ret == null) {
			ret = getDeleteFeatureAdditional(context);
		}

		return ret;
	}

	/**
	 * Gets the additional delete feature functionality. The default
	 * implementation simply delegates to
	 * {@link DefaultFeatureProvider#getDeleteFeature(IDeleteContext)}.
	 * 
	 * @param context
	 *            An {@link IDeleteContext} describing the needed functionality
	 * 
	 * @return An {@link IDeleteFeature} in case a suitable functionality has
	 *         been found by the super class, <code>null</code> otherwise.
	 */
	protected IDeleteFeature getDeleteFeatureAdditional(IDeleteContext context) {
		return super.getDeleteFeature(context);
	}

	/**
	 * Tries to retrieve a remove feature suiting the given remove context from
	 * the registered patterns. Only the shape patterns are queried. If no
	 * suitable pattern functionality is found the call is delegated to the
	 * super class (via the method
	 * {@link #getRemoveFeatureAdditional(IRemoveContext)}).
	 * 
	 * @param context
	 *            An {@link IRemoveContext} describing the needed functionality
	 * @return An {@link IRemoveFeature} in case a suitable functionality has
	 *         been found, <code>null</code> otherwise.
	 */
	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		if (context == null) {
			throw new IllegalArgumentException("Argument context must not be null."); //$NON-NLS-1$
		}
		IRemoveFeature ret = null;
		for (IPattern pattern : getPatterns()) {
			if (checkPattern(pattern, getBusinessObjectForPictogramElement(context.getPictogramElement()))) {
				IPattern choosenPattern = null;
				IRemoveFeature f = new RemoveFeatureForPattern(this, pattern);
				if (checkFeatureAndContext(f, context)) {
					if (ret == null) {
						ret = f;
						choosenPattern = pattern;
					} else {
						traceWarning("getRemoveFeature", pattern, choosenPattern); //$NON-NLS-1$
					}
				}
			}
		}

		if (ret == null) {
			ret = getRemoveFeatureAdditional(context);
		}

		return ret;
	}

	/**
	 * Gets the additional remove feature functionality. The default
	 * implementation simply delegates to
	 * {@link DefaultFeatureProvider#getRemoveFeature(IRemoveContext)}.
	 * 
	 * @param context
	 *            An {@link IRemoveContext} describing the needed functionality
	 * 
	 * @return An {@link IRemoveFeature} in case a suitable functionality has
	 *         been found by the super class, <code>null</code> otherwise.
	 */
	protected IRemoveFeature getRemoveFeatureAdditional(IRemoveContext context) {
		return super.getRemoveFeature(context);
	}

	/**
	 * Tries to retrieve a layout feature suiting the given layout context from
	 * the registered patterns. Only the shape patterns are queried. If no
	 * suitable pattern functionality is found the call is delegated to the
	 * super class (via the method
	 * {@link #getLayoutFeatureAdditional(ILayoutContext)}).
	 * 
	 * @param context
	 *            An {@link ILayoutContext} describing the needed functionality
	 * @return An {@link ILayoutFeature} in case a suitable functionality has
	 *         been found, <code>null</code> otherwise.
	 */
	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		if (context == null) {
			throw new IllegalArgumentException("Argument context must not be null."); //$NON-NLS-1$
		}
		ILayoutFeature ret = null;
		for (IPattern pattern : getPatterns()) {
			if (checkPattern(pattern, getBusinessObjectForPictogramElement(context.getPictogramElement()))) {
				IPattern choosenPattern = null;
				ILayoutFeature f = new LayoutFeatureForPattern(this, pattern);
				if (checkFeatureAndContext(f, context)) {
					if (ret == null) {
						ret = f;
						choosenPattern = pattern;
					} else {
						traceWarning("getLayoutFeature", pattern, choosenPattern); //$NON-NLS-1$
					}
				}
			}
		}

		if (ret == null) {
			ret = getLayoutFeatureAdditional(context);
		}

		return ret;
	}

	/**
	 * Gets the additional layout feature functionality. The default
	 * implementation simply delegates to
	 * {@link DefaultFeatureProvider#getLayoutFeature(ILayoutContext)}.
	 * 
	 * @param context
	 *            An {@link ILayoutContext} describing the needed functionality
	 * 
	 * @return An {@link ILayoutFeature} in case a suitable functionality has
	 *         been found by the super class, <code>null</code> otherwise.
	 */
	protected ILayoutFeature getLayoutFeatureAdditional(ILayoutContext context) {
		return super.getLayoutFeature(context);
	}

	/**
	 * Tries to retrieve a move feature for shapes suiting the given move
	 * context from the registered patterns. Only the shape patterns are
	 * queried. If no suitable pattern functionality is found the call is
	 * delegated to the super class (via the method
	 * {@link #getMoveShapeFeatureAdditional(IMoveShapeContext)}).
	 * 
	 * @param context
	 *            An {@link IMoveShapeContext} describing the needed
	 *            functionality
	 * @return An {@link IMoveShapeFeature} in case a suitable functionality has
	 *         been found, <code>null</code> otherwise.
	 */
	@Override
	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		if (context == null) {
			throw new IllegalArgumentException("Argument context must not be null."); //$NON-NLS-1$
		}
		IMoveShapeFeature ret = null;
		for (IPattern pattern : getPatterns()) {
			if (checkPattern(pattern, getBusinessObjectForPictogramElement(context.getPictogramElement()))) {
				IPattern choosenPattern = null;
				IMoveShapeFeature f = new MoveShapeFeatureForPattern(this, pattern);
				if (checkFeatureAndContext(f, context)) {
					if (ret == null) {
						ret = f;
						choosenPattern = pattern;
					} else {
						traceWarning("getMoveShapeFeature", pattern, choosenPattern); //$NON-NLS-1$
					}
				}
			}
		}

		if (ret == null) {
			ret = getMoveShapeFeatureAdditional(context);
		}

		return ret;
	}

	/**
	 * Gets the additional move feature functionality for shapes. The default
	 * implementation simply delegates to
	 * {@link DefaultFeatureProvider#getMoveShapeFeature(IMoveShapeContext)}.
	 * 
	 * @param context
	 *            An {@link IMoveShapeContext} describing the needed
	 *            functionality
	 * 
	 * @return An {@link IMoveShapeFeature} in case a suitable functionality has
	 *         been found by the super class, <code>null</code> otherwise.
	 */
	protected IMoveShapeFeature getMoveShapeFeatureAdditional(IMoveShapeContext context) {
		return super.getMoveShapeFeature(context);
	}

	/**
	 * Tries to retrieve a resize feature for shapes suiting the given resize
	 * context from the registered patterns. Only the shape patterns are
	 * queried. If no suitable pattern functionality is found the call is
	 * delegated to the super class (via the method
	 * {@link #getResizeShapeFeatureAdditional(IResizeShapeContext)}).
	 * 
	 * @param context
	 *            An {@link IResizeShapeContext} describing the needed
	 *            functionality
	 * @return An {@link IResizeShapeFeature} in case a suitable functionality
	 *         has been found, <code>null</code> otherwise.
	 */
	@Override
	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
		if (context == null) {
			throw new IllegalArgumentException("Argument context must not be null."); //$NON-NLS-1$
		}
		IResizeShapeFeature ret = null;
		for (IPattern pattern : getPatterns()) {
			if (checkPattern(pattern, getBusinessObjectForPictogramElement(context.getPictogramElement()))) {
				IPattern choosenPattern = null;
				IResizeShapeFeature f = new ResizeShapeFeatureForPattern(this, pattern);
				if (checkFeatureAndContext(f, context)) {
					if (ret == null) {
						ret = f;
						choosenPattern = pattern;
					} else {
						traceWarning("getResizeShapeFeature", pattern, choosenPattern); //$NON-NLS-1$
					}
				}
			}
		}

		if (ret == null) {
			ret = getResizeShapeFeatureAdditional(context);
		}

		return ret;
	}

	/**
	 * Checks the given pattern if it can deal with the given domain object. The
	 * default implementation simply delegates to the pattern's
	 * {@link IPattern#isMainBusinessObjectApplicable(Object)} method.
	 * 
	 * @param pattern
	 *            The pattern to check
	 * @param object
	 *            The domain object
	 * 
	 * @return <code>true</code>, if the pattern can handle the domain object,
	 *         <code>false</code> otherwise.
	 */
	protected boolean checkPattern(IPattern pattern, Object object) {
		return pattern.isMainBusinessObjectApplicable(object);
	}

	/**
	 * Gets the additional resize feature functionality for shapes. The default
	 * implementation simply delegates to
	 * {@link DefaultFeatureProvider#getResizeShapeFeature(IResizeShapeContext)}
	 * .
	 * 
	 * @param context
	 *            An {@link IResizeShapeContext} describing the needed
	 *            functionality
	 * 
	 * @return An {@link IResizeShapeFeature} in case a suitable functionality
	 *         has been found by the super class, <code>null</code> otherwise.
	 */
	protected IResizeShapeFeature getResizeShapeFeatureAdditional(IResizeShapeContext context) {
		return super.getResizeShapeFeature(context);
	}

	/**
	 * Tries to retrieve an update feature suiting the given update context from
	 * the registered patterns. Only the shape patterns are queried. If no
	 * suitable pattern functionality is found the call is delegated to the
	 * super class (via the method
	 * {@link #getUpdateFeatureAdditional(IUpdateContext)}).
	 * 
	 * @param context
	 *            An {@link IUpdateContext} describing the needed functionality
	 * @return An {@link IUpdateFeature} in case a suitable functionality has
	 *         been found, <code>null</code> otherwise.
	 */
	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		if (context == null) {
			throw new IllegalArgumentException("Argument context must not be null."); //$NON-NLS-1$
		}
		IUpdateFeature ret = null;
		Object businessObject = getBusinessObjectForPictogramElement(context.getPictogramElement());
		for (IPattern pattern : getPatterns()) {
			if (checkPattern(pattern, businessObject) || pattern.canUpdate(context)) {
				IPattern choosenPattern = null;
				IUpdateFeature f = new UpdateFeatureForPattern(this, pattern);
				if (checkFeatureAndContext(f, context)) {
					if (ret == null) {
						ret = f;
						choosenPattern = pattern;
					} else {
						traceWarning("getUpdateFeature", pattern, choosenPattern); //$NON-NLS-1$
					}
				}
			}
		}

		if (ret == null) {
			ret = getUpdateFeatureAdditional(context);
		}

		return ret;
	}

	/**
	 * Gets the additional update feature functionality. The default
	 * implementation simply delegates to
	 * {@link DefaultFeatureProvider#getUpdateFeature(IUpdateContext)} .
	 * 
	 * @param context
	 *            An {@link IUpdateContext} describing the needed functionality
	 * 
	 * @return An {@link IUpdateFeature} in case a suitable functionality has
	 *         been found by the super class, <code>null</code> otherwise.
	 */
	protected IUpdateFeature getUpdateFeatureAdditional(IUpdateContext context) {
		return super.getUpdateFeature(context);
	}

	public IPattern getPatternForPictogramElement(PictogramElement pe) {
		if (pe == null) {
			throw new IllegalArgumentException("Argument pe must not be null."); //$NON-NLS-1$
		}
		for (IPattern pattern : getPatterns()) {
			if (pattern instanceof AbstractPattern) {
				AbstractPattern ap = (AbstractPattern) pattern;
				if (ap.isPatternRoot(pe)) {
					return pattern;
				}
			}
		}
		return null;
	}

	/**
	 * Convenience method to activate the direct editing for the given
	 * {@link PictogramElement} and domain object. The default implementation
	 * tries to retrieve the direct editing functionality from the registered
	 * patterns for shapes.
	 * 
	 * @param mainPictogramElement
	 *            The root {@link PictogramElement} for which direct editing
	 *            shall be triggered. This pictogram element is used to find a
	 *            suitable pattern for this request via
	 *            {@link AbstractPattern#isPatternRoot(PictogramElement)}.
	 * @param domainObject
	 *            The domain object behind the direct editing request. This
	 *            object is passed to the {@link IDirectEditingInfo}.
	 */
	public void activateDirectEditingForPatterns(PictogramElement mainPictogramElement, Object domainObject) {
		IPattern pattern = getPatternForPictogramElement(mainPictogramElement);
		if (pattern != null) {
			IDirectEditingInfo dei = getDirectEditingInfo();
			dei.setMainPictogramElement(mainPictogramElement);
			pattern.completeInfo(dei, domainObject);
			dei.setActive(true);
		}
	}

	/**
	 * Convenience method to activate the direct editing for the given
	 * {@link PictogramElement} and domain object. The default implementation
	 * tries to retrieve the direct editing functionality from the registered
	 * patterns for shapes.
	 * 
	 * @param mainPictogramElement
	 *            The root {@link PictogramElement} for which direct editing
	 *            shall be triggered. This pictogram element is used to find a
	 *            suitable pattern for this request via
	 *            {@link AbstractPattern#isPatternRoot(PictogramElement)}.
	 * @param domainObject
	 *            The domain object behind the direct editing request. This
	 *            object is passed to the {@link IDirectEditingInfo}.
	 * @param keyProperty
	 *            An additional key property that is passed to the
	 *            {@link IDirectEditingInfo}.
	 */
	public void activateDirectEditingForPatterns(PictogramElement mainPictogramElement, Object domainObject,
			String keyProperty) {
		IPattern pattern = getPatternForPictogramElement(mainPictogramElement);
		if (pattern != null) {
			IDirectEditingInfo dei = getDirectEditingInfo();
			dei.setMainPictogramElement(mainPictogramElement);
			pattern.completeInfo(dei, domainObject, keyProperty);
			dei.setActive(true);
		}
	}

	/**
	 * Retrieves an array of create connection features that are available from
	 * the registered patterns and the super class of this feature provider.
	 * Only the connection patterns are queried. Then the call is delegated to
	 * the super class (via the method
	 * {@link #getCreateConnectionFeaturesAdditional()}).
	 * 
	 * @return An array of {@link ICreateConnectionFeature}s in case a suitable
	 *         functionality has been found, an empty array otherwise.
	 */
	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		ICreateConnectionFeature[] ret = new ICreateConnectionFeature[0];
		List<ICreateConnectionFeature> retList = new ArrayList<ICreateConnectionFeature>();

		for (IConnectionPattern conPattern : getConnectionPatterns()) {
			retList.add(new CreateConnectionFeatureForPattern(this, conPattern));
		}

		ICreateConnectionFeature[] a = getCreateConnectionFeaturesAdditional();
		for (ICreateConnectionFeature element : a) {
			retList.add(element);
		}

		return retList.toArray(ret);
	}

	/**
	 * Gets the additional create connection feature functionality. The default
	 * implementation simply delegates to
	 * {@link DefaultFeatureProvider#getCreateConnectionFeatures()}.
	 * 
	 * @return An array of {@link ICreateConnectionFeature}s in case a suitable
	 *         functionality has been found by the super class, an empty array
	 *         otherwise.
	 */
	protected ICreateConnectionFeature[] getCreateConnectionFeaturesAdditional() {
		return super.getCreateConnectionFeatures();
	}

	/**
	 * Tries to retrieve a direct editing feature suiting the given direct
	 * editing context from the registered patterns. Only the shape patterns are
	 * queried. If no suitable pattern functionality is found the call is
	 * delegated to the super class (via the method
	 * {@link #getDirectEditingFeatureAdditional(IDirectEditingContext)}).
	 * 
	 * @param context
	 *            An {@link IDirectEditingContext} describing the needed
	 *            functionality
	 * @return An {@link IDirectEditingFeature} in case a suitable functionality
	 *         has been found, <code>null</code> otherwise.
	 */
	@Override
	public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
		if (context == null) {
			throw new IllegalArgumentException("Argument context must not be null."); //$NON-NLS-1$
		}
		IDirectEditingFeature ret = null;
		for (IPattern pattern : getPatterns()) {
			if (checkPattern(pattern, getBusinessObjectForPictogramElement(context.getPictogramElement()))) {
				IPattern choosenPattern = null;
				IDirectEditingFeature f = new DirectEditingFeatureForPattern(this, pattern);
				if (checkFeatureAndContext(f, context)) {
					if (ret == null) {
						ret = f;
						choosenPattern = pattern;
					} else {
						traceWarning("getDirectEditingFeature", pattern, choosenPattern); //$NON-NLS-1$
					}
				}
			}
		}

		if (ret == null) {
			ret = getDirectEditingFeatureAdditional(context);
		}

		return ret;
	}

	/**
	 * Gets the additional direct editing feature functionality. The default
	 * implementation simply delegates to
	 * {@link DefaultFeatureProvider#getDirectEditingFeature(IDirectEditingContext)}
	 * .
	 * 
	 * @param context
	 *            An {@link IDirectEditingContext} describing the needed
	 *            functionality
	 * 
	 * @return An {@link IDirectEditingFeature} in case a suitable functionality
	 *         has been found by the super class, <code>null</code> otherwise.
	 */
	protected IDirectEditingFeature getDirectEditingFeatureAdditional(IDirectEditingContext context) {
		return super.getDirectEditingFeature(context);
	}

	/**
	 * Helper method to trace warnings when more than one pattern is executed.
	 * 
	 * @param string
	 *            The string information to trace
	 * @param pattern
	 *            The pattern
	 * @param choosenPattern
	 *            The additionally chosen pattern
	 */
	protected void traceWarning(String string, IPattern pattern, IPattern choosenPattern) {
		T.racer()
				.warning(
						string
								+ ": " + "Pattern " + pattern + " is executable additionally to pattern " + choosenPattern + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

	/**
	 * Helper method to find the domain object for a given
	 * {@link PictogramElement}. The default implementation first delegates to
	 * {@link DefaultFeatureProvider#getBusinessObjectForPictogramElement(PictogramElement)}
	 * and then directly tries to follow an eventually set link property, see
	 * {@link ILinkService#setLinkProperty(PictogramElement, String)}.
	 * 
	 * @param pictogramElement
	 *            The {@link PictogramElement} to find the domain object for.
	 * @return A domain object in case it was found, <code>null</code>
	 *         otherwise.
	 */
	@Override
	public Object getBusinessObjectForPictogramElement(PictogramElement pictogramElement) {
		Object ret = super.getBusinessObjectForPictogramElement(pictogramElement);
		if (ret == null) {
			Property linkProperty = Graphiti.getLinkService().getLinkProperty(pictogramElement);
			if (linkProperty != null) {
				ret = linkProperty.getValue();
			}
		}
		return ret;
	}
}

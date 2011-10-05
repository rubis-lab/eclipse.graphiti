/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *    mwenz - Bug 336075 - DiagramEditor accepts URIEditorInput
 *    mwenz - Bug 346932 - Navigation history broken
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.editor;

import org.eclipse.core.commands.operations.DefaultOperationHistory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.graphiti.ui.internal.editor.GFWorkspaceCommandStackImpl;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.graphiti.ui.internal.util.ReflectionUtil;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

/**
 * The Class DiagramEditorFactory.
 * 
 * A factory for creating DiagramEditorInternal objects.
 * 
 * @see {@link DiagramEditorFactory}
 * @see {@link DiagramEditorInput}
 */
public class DiagramEditorFactory implements IElementFactory {


	/**
	 * Creates a new {@link DiagramEditorInput} with a self created
	 * {@link TransactionalEditingDomain} in case the passed
	 * {@link IEditorInput} is either a {@link IFileEditorInput} or a
	 * {@link URIEditorInput}. It returns otherInput, if it is a
	 * {@link DiagramEditorInput}. The created editor input object will care
	 * about the disposal of the editing domain.
	 * 
	 * @param otherInput
	 *            an {@link IEditorInput} editor input
	 * @return a {@link DiagramEditorInput} editor input if the conversion is
	 *         supported and succeeded, otherwise <code>null</code>.
	 */
	public DiagramEditorInput createEditorInput(IEditorInput otherInput) {
		if (otherInput instanceof DiagramEditorInput) {
			DiagramEditorInput input = (DiagramEditorInput) otherInput;
			if (input.getAdapter(TransactionalEditingDomain.class) == null) {
				// This might happen in case the editor input comes from the
				// navigation history: there the editing domain is disposed and
				// the diagram can no longer be resolved. Simply create a new
				// transactional editing domain
				// See Bug 346932
				TransactionalEditingDomain ed = DiagramEditorFactory.createResourceSetAndEditingDomain();
				input.setEditingDomain(ed);
			}
			return input;
		}
		IFile file = ReflectionUtil.getFile(otherInput);
		if (file != null) {
			final TransactionalEditingDomain domain = createResourceSetAndEditingDomain();
			URI diagramFileUri = GraphitiUiInternal.getEmfService().getFileURI(file, domain.getResourceSet());
			if (diagramFileUri != null) {
				// the file's first base node has to be a diagram
				URI diagramUri = GraphitiUiInternal.getEmfService().mapDiagramFileUriToDiagramUri(diagramFileUri);
				return new DiagramEditorInput(diagramUri, domain, null, true);
			}
		}
		if (otherInput instanceof URIEditorInput) {
			final URIEditorInput uriInput = (URIEditorInput) otherInput;
			final TransactionalEditingDomain domain = createResourceSetAndEditingDomain();
			URI diagramFileUri = uriInput.getURI();
			if (diagramFileUri != null) {
				// the file's first base node has to be a diagram
				URI diagramUri = GraphitiUiInternal.getEmfService().mapDiagramFileUriToDiagramUri(diagramFileUri);
				return new DiagramEditorInput(diagramUri, domain, null, true);
			}
		}

		return null;
	}

	@Override
	public IAdaptable createElement(IMemento memento) {
		// get diagram URI
		final String diagramUriString = memento.getString(DiagramEditorInput.KEY_URI);
		if (diagramUriString == null) {
			return null;
		}

		// get diagram type provider id
		final String providerID = memento.getString(DiagramEditorInput.KEY_PROVIDER_ID);
		// TODO if (providerID == null)
		// return null;

		final TransactionalEditingDomain domain = createResourceSetAndEditingDomain();
		return new DiagramEditorInput(diagramUriString, domain, providerID, true);
	}

	/**
	 * Creates a {@link TransactionalEditingDomain} with a {@link ResourceSet}
	 * resource set and a {@link IWorkspaceCommandStack} command stack.
	 * 
	 * @return a {@link TransactionalEditingDomain} editing domain
	 */
	public static TransactionalEditingDomain createResourceSetAndEditingDomain() {
		final ResourceSet resourceSet = new ResourceSetImpl();
		final IWorkspaceCommandStack workspaceCommandStack = new GFWorkspaceCommandStackImpl(new DefaultOperationHistory());

		final TransactionalEditingDomainImpl editingDomain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE), workspaceCommandStack, resourceSet);
		WorkspaceEditingDomainFactory.INSTANCE.mapResourceSet(editingDomain);
		return editingDomain;
	}

}
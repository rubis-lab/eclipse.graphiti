package $packageName$.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

import $packageName$.features.Add$connectionDomainObjectClassNameShort$ConnectionFeature;
import $packageName$.features.Add$shapeDomainObjectClassNameShort$Feature;
import $packageName$.features.Create$connectionDomainObjectClassNameShort$ConnectionFeature;
import $packageName$.features.Create$shapeDomainObjectClassNameShort$Feature;
import $packageName$.features.Layout$shapeDomainObjectClassNameShort$Feature;

%if useShapeDomainObject
import $shapeDomainObjectClassName$;
%endif
%if useConnectionDomainObject
import $connectionDomainObjectClassName$;
%endif

public class $featureProviderClassName$ extends DefaultFeatureProvider {

	public $featureProviderClassName$(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] {new Create$shapeDomainObjectClassNameShort$Feature(this)};
	}
	
	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] {new Create$connectionDomainObjectClassNameShort$ConnectionFeature(this)};
	}
	
	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		// TODO: check for right domain object instances below
		if (context instanceof IAddConnectionContext /* && context.getNewObject() instanceof <DomainObject> */) {
			return new Add$connectionDomainObjectClassNameShort$ConnectionFeature(this);
		} else if (context instanceof IAddContext /* && context.getNewObject() instanceof <DomainObject> */) {
			return new Add$shapeDomainObjectClassNameShort$Feature(this);
		}

		return super.getAddFeature(context);
	}
	
	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		// TODO: check for right domain object instances below
		if (context.getPictogramElement() instanceof ContainerShape /* && getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof <DomainObject> */) {
			return  new Layout$shapeDomainObjectClassNameShort$Feature(this);
		}
	
		return super.getLayoutFeature(context);
	}
}

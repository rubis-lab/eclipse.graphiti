package $packageName$.features;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

public class Create$shapeDomainObjectClassNameShort$Feature extends AbstractCreateFeature implements
		ICreateFeature {

	public Create$shapeDomainObjectClassNameShort$Feature(IFeatureProvider fp) {
		super(fp, "$shapeDomainObjectClassNameShort$", "Creates a new $shapeDomainObjectClassNameShort$");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		// TODO: create the domain object here
		Object new$shapeDomainObjectClassNameShort$ = null;
		
		// TODO: in case of an EMF object add the new object to a suitable resource
		// getDiagram().eResource().getContents().add(new$shapeDomainObjectClassNameShort$);

		addGraphicalRepresentation(context, new$shapeDomainObjectClassNameShort$);
		return new Object[] { new$shapeDomainObjectClassNameShort$ };
	}
}

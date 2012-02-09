package org.eclipse.graphiti.ui.internal.parts;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.mm.pictograms.CompositeConnection;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.graphiti.ui.internal.partfactory.PictogramsEditPartFactory;

/**
 * 
 * @author Benjamin.Schmeling@googlemail.com
 * 
 */
public class CompositeConnectionEditPart extends ConnectionEditPart {

	private Collection<org.eclipse.gef.ConnectionEditPart> editParts = new HashSet<org.eclipse.gef.ConnectionEditPart>();

	public CompositeConnectionEditPart(IConfigurationProvider configurationProvider, CompositeConnection connection,
			PictogramsEditPartFactory factory, EditPart contextParent) {
		super(configurationProvider, connection, contextParent);

		for (Connection con : ((CompositeConnection) getConnection()).getChildren()) {
			org.eclipse.gef.ConnectionEditPart editPart = (org.eclipse.gef.ConnectionEditPart) getConfigurationProvider()
					.getEditPartFactory().createEditPart(this, con);
			this.editParts.add(editPart);
		}
	}

	@Override
	public void setSource(EditPart editPart) {
		super.setSource(editPart);
		for (org.eclipse.gef.ConnectionEditPart editPartChild : this.editParts) {
			editPartChild.setSource(editPart);
		}
	}

	@Override
	public void setTarget(EditPart editPart) {
		super.setTarget(editPart);
		for (org.eclipse.gef.ConnectionEditPart editPartChild : this.editParts) {
			editPartChild.setTarget(editPart);
		}
	}

}

package org.eclipse.graphiti.ui.editor;

import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Adds and removes update adapters to the respective resources as they come and
 * go.
 */
final class ResourceSetUpdateAdapter extends AdapterImpl {

	private Adapter adapter;

	/**
	 * @param diagramEditorBehavior
	 */
	ResourceSetUpdateAdapter(Adapter a) {
		this.adapter = a;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void notifyChanged(Notification msg) {
		if (msg.getFeatureID(ResourceSet.class) == ResourceSet.RESOURCE_SET__RESOURCES) {
			switch (msg.getEventType()) {
			case Notification.ADD:
				((Resource) msg.getNewValue()).eAdapters().add(adapter);
				break;
			case Notification.ADD_MANY:
				for (final Resource res : (Collection<Resource>) msg.getNewValue()) {
					res.eAdapters().add(adapter);
				}
				break;
			case Notification.REMOVE:
				((Resource) msg.getOldValue()).eAdapters().remove(adapter);
				break;
			case Notification.REMOVE_MANY:
				for (final Resource res : (Collection<Resource>) msg.getOldValue()) {
					res.eAdapters().remove(adapter);
				}
				break;

			default:
				break;
			}
		}
	}
}
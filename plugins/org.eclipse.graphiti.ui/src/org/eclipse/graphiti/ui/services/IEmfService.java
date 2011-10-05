package org.eclipse.graphiti.ui.services;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;

/**
 * Services regarding EMF.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 * @since 0.9
 */
public interface IEmfService {
	
	/**
	 * Creates a {@link TransactionalEditingDomain} with a {@link ResourceSet}
	 * resource set and a {@link IWorkspaceCommandStack} command stack.
	 * 
	 * @return a {@link TransactionalEditingDomain} editing domain
	 */
	public abstract TransactionalEditingDomain createResourceSetAndEditingDomain();

}

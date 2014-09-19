/*
 * <copyright>
 *
 * Copyright (c) 2014, IETR/INSA of Rennes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IETR/INSA of Rennes - initial API, implementation and documentation
 *
 * </copyright>
 *
 */
package org.eclipse.graphiti.features.custom;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;

/**
 * <p>
 * This class should be used if for some reason a CustomFeature can be long to
 * execute. It runs {@link #execute(ICustomContext, IProgressMonitor)} in a
 * Command on top of current TransactionalEditingDomain. This command itself is
 * run in a Job, and can use the associated IProgressMonitor
 * </p>
 * <p>
 * This is useful to indicate to users that the job is running, but eclipse has
 * not crashed.
 * </p>
 * 
 * @author Antoine Lorence
 * @since 0.12
 */
public abstract class AbstractAsynchronousCustomFeature extends AbstractCustomFeature {

	public AbstractAsynchronousCustomFeature(IFeatureProvider fp) {
		super(fp);
	}

	/**
	 * Concrete code to execute. Sub-classes should use the given monitor
	 * correctly: create tasks (and eventually sub-tasks), notify for worked and
	 * done tasks and check if user cancelled the task.
	 * 
	 * @param context
	 *            The CustomFeature context
	 * @param monitor
	 *            The monitor used to manage progress bar and Job cancellation
	 * @return The execution status
	 */
	protected abstract void execute(ICustomContext context, IProgressMonitor monitor);

	/**
	 * Callback executed just before job scheduling; called in the feature
	 * execution thread. Default implementation is empty.
	 */
	protected void beforeJobExecution() {
	}

	/**
	 * Callback executed immediately after job execution in the feature
	 * execution thread. Default implementation is empty.
	 */
	protected void afterJobExecution() {
	}

	/**
	 * Initialize the Job.
	 * 
	 * @param context
	 *            The CustomContext that will be given to
	 *            {@link #execute(ICustomContext, IProgressMonitor)}.
	 * @return The Job instance
	 */
	protected Job initializeJob(final ICustomContext context) {
		return new Job(getName()) {
			@Override
			protected IStatus run(final IProgressMonitor monitor) {

				final TransactionalEditingDomain editingDomain = getDiagramBehavior().getEditingDomain();

				final RecordingCommand command = new RecordingCommand(editingDomain, getName()) {

					private IStatus result = null;

					@Override
					protected void doExecute() {
						try {
							AbstractAsynchronousCustomFeature.this.execute(context, monitor);
							result = Status.OK_STATUS;
						} catch (OperationCanceledException e) {
							result = Status.CANCEL_STATUS;
						}
					}

					@Override
					public Collection<?> getResult() {
						return result == null ? Collections.EMPTY_LIST : Collections.singletonList(result);
					}
				};

				// Execute (synchronously) the defined command in a proper EMF
				// transaction
				editingDomain.getCommandStack().execute(command);

				// Update the dirty state of the diagram
				getDiagramBehavior().getDiagramContainer().updateDirtyState();

				// Callback
				afterJobExecution();

				return (IStatus) command.getResult().iterator().next();
			}
		};
	}

	/**
	 * Initialize parameters of the given Job
	 * 
	 * @param job
	 *            The Job instance to configure
	 */
	protected void configureJob(Job job) {
		job.setUser(true);
		job.setPriority(Job.LONG);
	}

	/**
	 * Must not be overridden in order to guarantee correct delegation to the
	 * background job, see {@link #execute(ICustomContext)}.
	 */
	@Override
	final public void execute(IContext context) {
		super.execute(context);
	}

	/**
	 * The implementation of this method will during the execution of the
	 * feature trigger the creation of a background job, configure it and
	 * execute it.<br>
	 * Clients should implement their feature functionality in
	 * {@link #execute(ICustomContext, IProgressMonitor)}, potentially also in
	 * the {@link #beforeJobExecution()} and {@link #afterJobExecution()} hooks.
	 * The background job and its behavior can be changed in the methods
	 * {@link #initializeJob(ICustomContext)} and {@link #configureJob(Job)}.
	 * 
	 * @param context
	 *            The CustomFeature context
	 * 
	 */
	@Override
	final public void execute(ICustomContext context) {

		final Job job = initializeJob(context);
		configureJob(job);

		// Callback
		beforeJobExecution();

		// Job is run
		job.schedule();
	}

	/**
	 * The default implementation returns <code>false</code> in any case. This
	 * is usually the desired return value, because this method will be queried
	 * already before the asynchronous feature will be executed (
	 * {@link #execute(ICustomContext, IProgressMonitor)} is called). Returning
	 * <code>true</code> here would mean that users could already undo the
	 * feature while it is still being executed; this would mean also canceling
	 * the a running job. Because of potentially strange effects and complexity
	 * this is not possible.
	 * 
	 * @return Always <code>false</code>
	 */
	@Override
	final public boolean hasDoneChanges() {
		return false;
	}
}

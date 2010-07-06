package org.eclipse.graphiti.sample.sketch;

import java.net.URL;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The main-class of the plugin. It is mostly used to acces the 'environment' of
 * this plugin.
 */
public class SketchPlugin extends AbstractUIPlugin {

	private static SketchPlugin _plugin;

	/**
	 * Creates the Plugin and caches its default instance.
	 */
	public SketchPlugin() {
		_plugin = this;
	}

	// ======================== static access methods ==========================

	/**
	 * Gets the default-instance of this plugin. Actually the default-instance
	 * should always be the only instance -> Singleton.
	 * 
	 * @return the default
	 */
	public static SketchPlugin getDefault() {
		return _plugin;
	}

	// =========================== public helper methods ======================

	/**
	 * Returns the current Workspace.
	 * 
	 * @return The current Workspace.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	/**
	 * Returns the URL, which points to where this Plugin is installed.
	 * 
	 * @return The URL, which points to where this Plugin is installed.
	 */
	public static URL getInstallURL() {
		return getDefault().getBundle().getEntry("/");
	}

	/**
	 * Returns the Plugin-ID.
	 * 
	 * @return The Plugin-ID.
	 */
	public static String getID() {
		return getDefault().getBundle().getSymbolicName();
	}

	/**
	 * Returns the currently active WorkbenchPage.
	 * 
	 * @return The currently active WorkbenchPage.
	 */
	public static IWorkbenchPage getActivePage() {
		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (workbenchWindow != null)
			return workbenchWindow.getActivePage();
		return null;
	}

	/**
	 * Returns the currently active Shell.
	 * 
	 * @return The currently active Shell.
	 */
	public static Shell getShell() {
		return getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
	}
}

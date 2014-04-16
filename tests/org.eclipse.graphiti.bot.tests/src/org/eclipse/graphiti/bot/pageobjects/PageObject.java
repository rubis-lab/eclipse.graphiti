package org.eclipse.graphiti.bot.pageobjects;

import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Encapsulates SWTBot access and technical details, thereby enabling
 * to write semantic tests, that is, tests were the test flow is more readable
 * and technicalities are hidden.
 *
 */
public abstract class PageObject {

	protected SWTGefBot bot = new SWTGefBot();

}

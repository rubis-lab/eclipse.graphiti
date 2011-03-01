/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2011 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.internal.services.impl;

import java.util.HashSet;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IMigrationService;

/**
 *
 */
public class MigrationServiceImpl implements IMigrationService {


	@Override
	public void migrate070To080(Diagram d) {

		// Traverse model and collect fonts
		HashSet<Font> fonts = new HashSet<Font>();
		Resource eResource = d.eResource();
		TreeIterator<EObject> allContents = eResource.getAllContents();
		while (allContents.hasNext()) {
			EObject next = allContents.next();
			if (next instanceof AbstractText) {
				AbstractText t = (AbstractText) next;
				Font font = t.getFont();
				fonts.add(font);
			} else if (next instanceof Style) {
				Style s = (Style) next;
				Font font = s.getFont();
				fonts.add(font);
			}
		}

		// Manage collected fonts, including possibly a write.
		// The caller has to use a write transaction.
		for (Font font : fonts) {
			Graphiti.getGaService().manageFont(d, font.getName(), font.getSize(), font.isItalic(), font.isBold());
		}
	}

}

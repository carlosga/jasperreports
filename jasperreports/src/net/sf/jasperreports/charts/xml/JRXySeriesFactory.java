/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.charts.xml;

import net.sf.jasperreports.charts.design.JRDesignXySeries;
import net.sf.jasperreports.engine.xml.JRBaseFactory;

import org.xml.sax.Attributes;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id$
 */
public class JRXySeriesFactory extends JRBaseFactory
{
	private static final String ATTRIBUTE_autoSort = "autoSort";
	
	/**
	 *
	 */
	public Object createObject(Attributes atts)
	{
		JRDesignXySeries xySeries = new JRDesignXySeries();
		String autoSort = atts.getValue( ATTRIBUTE_autoSort );
		if( autoSort != null && autoSort.length() > 0 ){
			xySeries.setAutoSort(Boolean.valueOf(autoSort) );
		}
		
		return xySeries;
	}

}

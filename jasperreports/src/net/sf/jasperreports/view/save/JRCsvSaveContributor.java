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
package net.sf.jasperreports.view.save;

import java.io.File;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.view.JRSaveContributor;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id$
 */
public class JRCsvSaveContributor extends JRSaveContributor
{

	/**
	 * 
	 */
	private static final String EXTENSION_CSV = ".csv"; 

	/**
	 * @see #JRCsvSaveContributor(JasperReportsContext, Locale, ResourceBundle)
	 */
	public JRCsvSaveContributor(Locale locale, ResourceBundle resBundle)
	{
		super(locale, resBundle);
	}
	
	/**
	 * 
	 */
	public JRCsvSaveContributor(
		JasperReportsContext jasperReportsContext, 
		Locale locale, 
		ResourceBundle resBundle
		)
	{
		super(jasperReportsContext, locale, resBundle);
	}
	
	/**
	 * 
	 */
	public boolean accept(File file)
	{
		if (file.isDirectory())
		{
			return true;
		}
		return file.getName().toLowerCase().endsWith(EXTENSION_CSV);
	}

	/**
	 * 
	 */
	public String getDescription()
	{
		return getBundleString("file.desc.csv");
	}

	/**
	 * 
	 */
	public void save(JasperPrint jasperPrint, File file) throws JRException
	{
		if (!file.getName().toLowerCase().endsWith(EXTENSION_CSV))
		{
			file = new File(file.getAbsolutePath() + EXTENSION_CSV);
		}
		
		if (
			!file.exists() ||
			JOptionPane.OK_OPTION == 
				JOptionPane.showConfirmDialog(
					null, 
					MessageFormat.format(
						getBundleString("file.exists"),
						new Object[]{file.getName()}
						), 
					getBundleString("save"), 
					JOptionPane.OK_CANCEL_OPTION
					)
			)
		{
			JRCsvExporter exporter = new JRCsvExporter(getJasperReportsContext());
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint)); 
			exporter.setExporterOutput(new SimpleWriterExporterOutput(file));
			exporter.exportReport();
		}
	}

}

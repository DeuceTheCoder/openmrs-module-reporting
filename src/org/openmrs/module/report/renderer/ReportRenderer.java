/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.report.renderer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Collection;
import java.util.List;

import org.openmrs.module.report.ReportData;
import org.openmrs.module.report.ReportDefinition;

/**
 * Takes a ReportDefinition and renders it.
 */
public interface ReportRenderer {
	
	/**
	 * @return the key for which to retrieve the localized label for this ReportRenderer
	 */
	public String getLabel();
	
	/**
	 * Returns the ReportDesigns in which this report schema could be rendered.
	 * 
	 * @param schema - The {@link ReportDefinition} to check
	 * @return a <code>Collection<ReportDesign></code> of all modes in which the given ReportDefinition can be rendered
	 */
	public Collection<RenderingMode> getRenderingModes(ReportDefinition schema);
	
	/**
	 * The content-type that will be rendered
	 * 
	 * @param schema The ReportDefinition to render
	 * @param argument Argument from the RenderingMode that the user selected
	 * @return the <code>String</code> representation of the rendered content type
	 */
	public String getRenderedContentType(ReportDefinition schema, String argument);
	
	/**
	 * @param schema
	 * @param argument Argument from the RenderingMode that the user selected
	 * @return Suggested filename to save the rendered report as.
	 */
	public String getFilename(ReportDefinition schema, String argument);
	
	
	/**
	 * @return	the columns that have been specified for display
	 */
	public List<String> getDisplayColumns();
	
	/**
	 * Setting the columns that should be displayed when rendered.
	 * @param displayColumns
	 */
	public void setDisplayColumns(List<String> displayColumns);
	
	/**
	 * Render the report's data to a stream
	 * 
	 * @param reportData Data that was calculated by the Reporting API and service
	 * @param argument Argument from the RenderingMode that the user selected
	 * @param out
	 * @throws ReportRenderingException
	 */
	public void render(ReportData reportData, String argument, OutputStream out) throws IOException, RenderingException;
	
	/**
	 * Render the report's data to a stream
	 * 
	 * @param reportData Data that was calculated by the Reporting API and service
	 * @param argument Argument from the RenderingMode that the user selected
	 * @param writer the object to write the output to
	 * @throws ReportRenderingException
	 */
	public void render(ReportData reportData, String argument, Writer writer) throws IOException, RenderingException;
}

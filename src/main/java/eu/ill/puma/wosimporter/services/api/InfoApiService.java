/*
 * Copyright 2019 Institut Laue–Langevin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.ill.puma.wosimporter.services.api;

import eu.ill.puma.wosimporter.domain.capabilities.*;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

@Service
public class InfoApiService {


	public Response getInfo() {
		SupportedOperations supportedOperations = new SupportedOperations();
		supportedOperations.setGetAll(false);

		SupportedQueryFilter supportedQueryFilter = new SupportedQueryFilter();
		supportedQueryFilter.setMonth(true);
		supportedQueryFilter.setYear(true);
		supportedQueryFilter.setSearch(true);
		supportedQueryFilter.setIds(true);
		supportedQueryFilter.setStart(false);

		MetaDataAnalysisState metaDataAnalysisState = new MetaDataAnalysisState();
		metaDataAnalysisState.setDoi(AnalysisState.CLOSED);
		metaDataAnalysisState.setTitle(AnalysisState.CLOSED);
		metaDataAnalysisState.setAbstract(AnalysisState.CLOSED);
		metaDataAnalysisState.setDate(AnalysisState.CLOSED);
		metaDataAnalysisState.setPerson(AnalysisState.CLOSED);
		metaDataAnalysisState.setLaboratory(AnalysisState.CLOSED);
		metaDataAnalysisState.setInstrument(AnalysisState.TO_ANALYSE);
		metaDataAnalysisState.setExperimentTechnique(AnalysisState.TO_ANALYSE);
		metaDataAnalysisState.setKeyword(AnalysisState.TO_ANALYSE);
		metaDataAnalysisState.setFormula(AnalysisState.TO_ANALYSE);
		metaDataAnalysisState.setReference(AnalysisState.TO_ANALYSE);
		metaDataAnalysisState.setCitation(AnalysisState.TO_ANALYSE);
		metaDataAnalysisState.setResearchDomain(AnalysisState.CLOSED);
		metaDataAnalysisState.setJournal(AnalysisState.CLOSED);
		metaDataAnalysisState.setPublisher(AnalysisState.CLOSED);
		metaDataAnalysisState.setExtractedImage(AnalysisState.TO_ANALYSE);
		metaDataAnalysisState.setAdditionalText(AnalysisState.TO_ANALYSE);
		metaDataAnalysisState.setFullText(AnalysisState.TO_ANALYSE);

		Capabilities capabilities = new Capabilities();
		capabilities.setImporterName("wos_importer");
		capabilities.setImporterShortName("wos");
		capabilities.setSupportedOperations(supportedOperations);
		capabilities.setSupportedQueryFilter(supportedQueryFilter);
		capabilities.setMetaDataAnalysisState(metaDataAnalysisState);
		capabilities.setVersion("1.1");

		return Response.ok().entity(capabilities).build();
	}
}

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
package eu.ill.puma.wosimporter.entrypoint;

import eu.ill.puma.wosimporter.domain.document.ImporterResponse;
import eu.ill.puma.wosimporter.domain.exception.BusyException;
import eu.ill.puma.wosimporter.domain.exception.NotFoundException;
import eu.ill.puma.wosimporter.services.api.DocumentApiService;
import eu.ill.puma.wosimporter.utils.ResponseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/documents")
@Produces({"application/json"})
@Component
public class DocumentApi {

	private static final Logger log = LoggerFactory.getLogger(DocumentApi.class);

	@Value("${app.retrieveReferences}")
	private boolean retrieveReferences;

	@Value("${app.retrieveCitation}")
	private boolean retrieveCitation;

	private final DocumentApiService controller;

	@Autowired
	public DocumentApi(DocumentApiService controller) {
		this.controller = controller;
	}

	@GET
	@Path("/cursor/{token}")
	@Produces({"application/json"})
	@ApiOperation(value = "return all document", notes = "url to use cursor ", response = ImporterResponse.class, tags = {})
	@ApiResponse(code = 200, message = "A list of document", response = ImporterResponse.class)
	public Response getDocumentsByCursor(@ApiParam(value = "cursor id", required = true) @PathParam("token") String token) throws NotFoundException {
		log.info("fetch cursor : " + token);
		Long startTime = System.currentTimeMillis();
		Response response;

		try {
			response = controller.getDocumentsByCursor(token, this.retrieveCitation, this.retrieveReferences, true);
		} catch (BusyException e) {
			log.error("an error occur, importer is busy : " + e.getMessage());
			response = ResponseService.BuildBusyResponse(e.getMessage());
		} catch (Exception e) {
			log.error("an error occur : " + e.getMessage());
			response = ResponseService.BuildErrorResponse(e.getMessage());
		}

		((ImporterResponse) response.getEntity()).getMetadata().setRequestDuration(System.currentTimeMillis() - startTime);
		return response;
	}

	@GET
	@Produces({"application/json"})
	@ApiOperation(value = "return all document", notes = "If the importer support all query mode, all the document from the data source will be returned in the data section of the response. The meta data of the section contains the iterator over results ", response = ImporterResponse.class, tags = {})
	@ApiResponse(code = 200, message = "A list of document", response = ImporterResponse.class)
	public Response getDocuments(
			@ApiParam(value = "IDs of document to retrieve") @QueryParam("ids") String ids,
			@ApiParam(value = "the user search query") @QueryParam("query") String query,
			@ApiParam(value = "year of document to retrieve") @QueryParam("startYear") Integer startYear,
			@ApiParam(value = "month of document to retrieve") @QueryParam("startMonth") Integer startMonth,
			@ApiParam(value = "month of document to retrieve") @QueryParam("startId") Integer startId) throws NotFoundException {
		Response response;
		Long startTime = System.currentTimeMillis();

		try {
			if (ids != null) {
				/**
				 * IDS section
				 */
				ids = ids.substring(1);
				ids = ids.substring(0, ids.length() - 1);
				List<String> idList = Arrays.asList(ids.replaceAll("\\s+", "").split(","));

				log.info("get documents by ids : " + ids);

				response = controller.getDocuments(idList, this.retrieveCitation, this.retrieveReferences, true);
			} else if (ids == null && query != null) {
				/**
				 * Search section
				 */
				log.info("run search query : " + query);
				try {
					response = controller.getDocumentsBySearchQuery(query, startYear, startMonth, this.retrieveCitation, this.retrieveReferences, true);
				} catch (BusyException e) {
					log.error("an error occur, importer is busy : " + e.getMessage());
					response = ResponseService.BuildBusyResponse(e.getMessage());
				} catch (Exception e) {
					log.error("an error occur : " + e.getMessage());
					response = ResponseService.BuildErrorResponse(e.getMessage());
				}
			} else {
				/**
				 * all section
				 */
				log.info("get all documents");
				response = controller.getAll();
			}
		} catch (BusyException e) {
			log.error("an error occur, importer is busy : " + e.getMessage());
			response = ResponseService.BuildBusyResponse(e.getMessage());
		} catch (Exception e) {
			log.error("an error occur : " + e.getMessage());
			response = ResponseService.BuildErrorResponse(e.getMessage());
		}

		((ImporterResponse) response.getEntity()).getMetadata().setRequestDuration(System.currentTimeMillis() - startTime);
		return response;
	}


}

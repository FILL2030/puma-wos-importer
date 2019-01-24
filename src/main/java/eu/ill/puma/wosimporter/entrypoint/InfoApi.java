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

import eu.ill.puma.wosimporter.domain.capabilities.MetaDataAnalysisState;
import eu.ill.puma.wosimporter.domain.exception.NotFoundException;
import eu.ill.puma.wosimporter.services.api.InfoApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/info")

@Produces({"application/json"})
@Api(description = "the info API")
public class InfoApi {

	private InfoApiService controller;

	@Autowired
	public InfoApi(InfoApiService controller) {
		this.controller = controller;
	}

	@GET
	@Produces({"application/json"})
	@ApiOperation(value = "return importer capabilities", notes = "The info endpoint returns information about the capabilities of the importer. ", response = MetaDataAnalysisState.class, tags = {})
	@ApiResponses(value = {@ApiResponse(code = 200, message = "A list of capabilities", response = MetaDataAnalysisState.class)})
	public Response getInfo()throws NotFoundException {
		return controller.getInfo();
	}
}

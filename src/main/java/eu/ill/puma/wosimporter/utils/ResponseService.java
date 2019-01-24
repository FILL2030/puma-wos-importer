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
package eu.ill.puma.wosimporter.utils;

import eu.ill.puma.wosimporter.domain.document.BaseDocument;
import eu.ill.puma.wosimporter.domain.document.ImporterResponse;
import eu.ill.puma.wosimporter.domain.document.ImporterResponseMetadata;
import eu.ill.puma.wosimporter.domain.document.enumeration.ImporterStatusEnum;

import javax.ws.rs.core.Response;
import java.util.List;

public class ResponseService {
	/**
	 * send final response to the client
	 *
	 * @param responseData
	 * @param responseMetadata
	 * @return
	 */
	public static Response buildResponse(List<BaseDocument> responseData, ImporterResponseMetadata responseMetadata) {
		//build response
		ImporterResponse response = new ImporterResponse();
		response.setData(responseData);
		response.setMetadata(responseMetadata);

		//return response
		return Response.ok().entity(response).build();
	}

	/**
	 * call to send a void document to the client
	 *
	 * @return
	 */
	public static Response notFoundResponse(String message) {
		ImporterResponse response = new ImporterResponse();
		ImporterResponseMetadata responseMetadata = new ImporterResponseMetadata();

		responseMetadata.setStatus(ImporterStatusEnum.NOT_FOUND);
		responseMetadata.setTotalCount(0L);
		responseMetadata.setCount(0L);
		responseMetadata.setMessage(message);

		response.setMetadata(responseMetadata);

		//return response
		return Response.ok().entity(response).build();
	}

	/**
	 * call to send a void document to the client
	 *
	 * @return
	 */
	public static Response notSupportedResponse(String message) {
		ImporterResponse response = new ImporterResponse();
		ImporterResponseMetadata responseMetadata = new ImporterResponseMetadata();

		responseMetadata.setStatus(ImporterStatusEnum.NOT_SUPPORTED);
		responseMetadata.setTotalCount(0L);
		responseMetadata.setCount(0L);
		responseMetadata.setMessage(message);

		response.setMetadata(responseMetadata);

		//return response
		return Response.ok().entity(response).build();
	}

	public static Response BuildBusyResponse(String message){
		ImporterResponse response = new ImporterResponse();
		ImporterResponseMetadata responseMetadata= new ImporterResponseMetadata();

		responseMetadata.setCount(0L);
		responseMetadata.setTotalCount(0L);
		responseMetadata.setStatus(ImporterStatusEnum.BUSY);
		responseMetadata.setMessage(message);

		response.setMetadata(responseMetadata);

		return Response.ok().entity(response).build();
	}

	public static Response BuildErrorResponse(String message){
		ImporterResponse response = new ImporterResponse();
		ImporterResponseMetadata responseMetadata= new ImporterResponseMetadata();

		responseMetadata.setCount(0L);
		responseMetadata.setTotalCount(0L);
		responseMetadata.setStatus(ImporterStatusEnum.ERROR);
		responseMetadata.setMessage(message);

		response.setMetadata(responseMetadata);

		return Response.ok().entity(response).build();
	}
}

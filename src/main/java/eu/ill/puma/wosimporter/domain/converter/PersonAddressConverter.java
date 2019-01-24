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
package eu.ill.puma.wosimporter.domain.converter;

import eu.ill.puma.wosimporter.domain.document.BaseDocument;
import eu.ill.puma.wosimporter.domain.document.entities.BaseLaboratory;
import eu.ill.puma.wosimporter.domain.document.entities.BasePerson;
import eu.ill.puma.wosimporter.domain.document.enumeration.MetadataConfidence;
import eu.ill.puma.wosimporter.domain.entities.Record;
import eu.ill.puma.wosimporter.domain.entities.staticdata.AddressName;
import eu.ill.puma.wosimporter.domain.entities.staticdata.Contributor;
import eu.ill.puma.wosimporter.domain.entities.staticdata.LaboratoryAddress;
import eu.ill.puma.wosimporter.domain.entities.staticdata.Name;
import org.unbescape.html.HtmlEscape;

import java.util.ArrayList;
import java.util.List;

public class PersonAddressConverter {
	public static void convert(BaseDocument document, Record record) {
		Long laboIdCounter = 0L;

		List<BasePerson> allImporterPersons = new ArrayList<BasePerson>();

		if (record.getStaticData().getSummary() != null) {
			List<Name> names = record.getStaticData().getSummary().getNames();
			if (names != null) {
				for (Name name : names) {
					if (name.getRole().equals("author")) {
						//person data copy
						BasePerson importerPerson = new BasePerson();

						importerPerson.setFirstName(name.getFirstName());
						importerPerson.setLastName(name.getLastName());
						importerPerson.setPublicationName(name.getDisplayName());
						importerPerson.setEmail(name.getEmailAddress());
						importerPerson.setConfidence(MetadataConfidence.SURE);

						//set ID
						addImporterPersonIdDetails(name, importerPerson, record);

						// add to list
						if (!allImporterPersons.contains(importerPerson)) {
							allImporterPersons.add(importerPerson);
						}
					}
				}
			}
		}


		if (record.getStaticData().getFullRecordMetaData().getAddress() != null) {

			//foreach address entry
			for (AddressName address : record.getStaticData().getFullRecordMetaData().getAddress()) {
				//next labo id
				laboIdCounter++;

				//get labo detail form records
				LaboratoryAddress laboratoryAddress = address.getLaboAddress();

				//copy address and country data
				BaseLaboratory importerLaboratory = new BaseLaboratory();

				importerLaboratory.setId(laboIdCounter);
				importerLaboratory.setAddress(HtmlEscape.unescapeHtml(laboratoryAddress.getFullAddress()));
				importerLaboratory.setCity(HtmlEscape.unescapeHtml(laboratoryAddress.getCity()));
				importerLaboratory.setCountry(HtmlEscape.unescapeHtml(laboratoryAddress.getCountry()));
				importerLaboratory.setConfidence(MetadataConfidence.SURE);

				//set labo name, if the name is too short, we consider it as a shortname
				if (laboratoryAddress.getOrganizations() != null) {
					// Use only first name: no guarantee of order of the other names coming from WoS
					String organisationName = laboratoryAddress.getOrganizations().get(0);
					if (organisationName.length() < 6) {
						importerLaboratory.setShortName(HtmlEscape.unescapeHtml(organisationName));
					} else {
						importerLaboratory.setName(HtmlEscape.unescapeHtml(organisationName));
					}
				}

				//add the current laboratory to the document
				document.getLaboratories().add(importerLaboratory);

				List<Name> names = address.getNames();
				if (names != null) {
					for (Name name : names) {

						//person data copy
						BasePerson importerPerson = new BasePerson();

						importerPerson.setFirstName(name.getFirstName());
						importerPerson.setLastName(name.getLastName());
						importerPerson.setPublicationName(name.getDisplayName());
						importerPerson.setEmail(name.getEmailAddress());
						importerPerson.setConfidence(MetadataConfidence.SURE);

						//set ID
						addImporterPersonIdDetails(name, importerPerson, record);

						//link with laboratories
						importerPerson.setLaboratoryId(laboIdCounter);

						//add the current person to the document
						document.getPersons().add(importerPerson);

						// Remove from allImporterPersons
						if (allImporterPersons.contains(importerPerson)) {
							allImporterPersons.remove(importerPerson);
						}
					}
				}
			}
		}

		// Add all remaining importer persons
		for (BasePerson importerPerson : allImporterPersons) {
			// make fake link
			importerPerson.setLaboratoryId(-1l);

			//add the current person to the document
			document.getPersons().add(importerPerson);
		}
	}

	private static void addImporterPersonIdDetails(Name name, BasePerson importerPerson, Record record) {
		if (record.getStaticData().getContributors() != null) {
			for (Contributor contributor : record.getStaticData().getContributors()) {
				if (contributor.getName() != null && contributor.getName().getDisplayName() != null && (
						contributor.getName().getDisplayName().equals(name.getDisplayName())
								|| contributor.getName().getDisplayName().contains(name.getDisplayName())
								|| name.getDisplayName().contains(contributor.getName().getDisplayName())
				)) {
					if (contributor.getName().getOrcidId() != null && contributor.getName().getOrcidId().length() > 5) {
						importerPerson.setOrcidId(contributor.getName().getOrcidId());
					}
					if (contributor.getName().getResearcherId() != null && contributor.getName().getResearcherId().length() > 5) {
						importerPerson.setResearcherId(contributor.getName().getResearcherId());
					}
				}
			}
		}
	}
}

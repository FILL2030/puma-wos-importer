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
package eu.ill.puma.wosimporter.domain.document;


import eu.ill.puma.wosimporter.domain.document.entities.*;
import eu.ill.puma.wosimporter.domain.document.enumeration.BaseDocumentType;
import eu.ill.puma.wosimporter.domain.document.enumeration.BaseDocumentVersionSubType;

import java.util.ArrayList;
import java.util.List;

public class BaseDocument {

	private Long id = null;
	private String sourceId = null;
	private boolean isModifiedAtSource = false;

	private BaseStringEntity doi;
	private BaseStringEntity shortName = null;
	private BaseStringEntity title = null;
	private BaseStringEntity abstractText = null;

	private BaseDocumentType type = null;
	private BaseDocumentVersionSubType subType = null;
	private BaseStringEntity releaseDate = null;
	private BaseJournal journal = null;

	private List<BaseStringEntity> keywords = new ArrayList();
	private List<BaseStringEntity> references = new ArrayList();
	private List<BaseStringEntity> citations = new ArrayList();
	private List<BaseStringEntity> researchDomains = new ArrayList();

	private List<BaseLaboratory> laboratories = new ArrayList();
	private List<BasePerson> persons = new ArrayList();
	private List<BaseInstrument> instruments = new ArrayList();
	private List<BaseFormula> formulas = new ArrayList();
	private List<BasePublisher> publishers = new ArrayList();
	private List<BaseFile> files = new ArrayList();
	private List<BaseFileToDownload> filesToDownload = new ArrayList();
	private List<BaseAdditionalText> additionalTexts = new ArrayList();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public boolean isModifiedAtSource() {
		return isModifiedAtSource;
	}

	public void setModifiedAtSource(boolean modifiedAtSource) {
		isModifiedAtSource = modifiedAtSource;
	}

	public BaseStringEntity getDoi() {
		return doi;
	}

	public void setDoi(BaseStringEntity doi) {
		this.doi = doi;
	}

	public BaseStringEntity getShortName() {
		return shortName;
	}

	public void setShortName(BaseStringEntity shortName) {
		this.shortName = shortName;
	}

	public BaseStringEntity getTitle() {
		return title;
	}

	public void setTitle(BaseStringEntity title) {
		this.title = title;
	}

	public BaseStringEntity getAbstract() {
		return abstractText;
	}

	public void setAbstract(BaseStringEntity abstractText) {
		this.abstractText = abstractText;
	}

	public BaseDocumentType getType() {
		return type;
	}

	public void setType(BaseDocumentType type) {
		this.type = type;
	}

	public BaseDocumentVersionSubType getSubType() {
		return subType;
	}

	public void setSubType(BaseDocumentVersionSubType subType) {
		this.subType = subType;
	}

	public BaseStringEntity getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(BaseStringEntity releaseDate) {
		this.releaseDate = releaseDate;
	}

	public BaseJournal getJournal() {
		return journal;
	}

	public void setJournal(BaseJournal journal) {
		this.journal = journal;
	}

	public List<BaseStringEntity> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<BaseStringEntity> keywords) {
		this.keywords = keywords;
	}

	public List<BaseStringEntity> getReferences() {
		return references;
	}

	public void setReferences(List<BaseStringEntity> references) {
		this.references = references;
	}

	public List<BaseStringEntity> getCitations() {
		return citations;
	}

	public void setCitations(List<BaseStringEntity> citations) {
		this.citations = citations;
	}

	public List<BaseStringEntity> getResearchDomains() {
		return researchDomains;
	}

	public void setResearchDomains(List<BaseStringEntity> researchDomains) {
		this.researchDomains = researchDomains;
	}

	public List<BaseLaboratory> getLaboratories() {
		return laboratories;
	}

	public void setLaboratories(List<BaseLaboratory> laboratories) {
		this.laboratories = laboratories;
	}

	public List<BasePerson> getPersons() {
		return persons;
	}

	public void setPersons(List<BasePerson> persons) {
		this.persons = persons;
	}

	public List<BaseInstrument> getInstruments() {
		return instruments;
	}

	public void setInstruments(List<BaseInstrument> instruments) {
		this.instruments = instruments;
	}

	public List<BaseFormula> getFormulas() {
		return formulas;
	}

	public void setFormulas(List<BaseFormula> formulas) {
		this.formulas = formulas;
	}

	public List<BasePublisher> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<BasePublisher> publishers) {
		this.publishers = publishers;
	}

	public List<BaseFile> getFiles() {
		return files;
	}

	public void setFiles(List<BaseFile> files) {
		this.files = files;
	}

	public List<BaseFileToDownload> getFilesToDownload() {
		return filesToDownload;
	}

	public void setFilesToDownload(List<BaseFileToDownload> filesToDownload) {
		this.filesToDownload = filesToDownload;
	}

	public List<BaseAdditionalText> getAdditionalTexts() {
		return additionalTexts;
	}

	public void setAdditionalTexts(List<BaseAdditionalText> additionalTexts) {
		this.additionalTexts = additionalTexts;
	}
}


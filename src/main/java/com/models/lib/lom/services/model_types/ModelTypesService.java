package com.models.lib.lom.services.model_types;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.models.lib.lom.components.services.Dao;
import com.models.lib.lom.components.services.Service;
import com.models.lib.lom.components.services.Query.Comparator;
import com.models.lib.lom.services.contributors.ContributorsService;
import com.models.lib.lom.services.contributors.ContributorsTable;
import com.models.lib.lom.services.files.FilesService;
import com.models.lib.lom.services.files.FilesTable;
import com.models.lib.lom.services.nn_files_v_all.NNFilesVAll;
import com.models.lib.lom.services.nn_files_v_all.NNFilesVAllService;
import com.models.lib.lom.services.nn_files_v_all.NNFilesVAllTable;
import com.models.lib.lom.services.nn_model_types_v_tags.NNModelTypesVTags;
import com.models.lib.lom.services.nn_model_types_v_tags.NNModelTypesVTagsService;
import com.models.lib.lom.services.nn_model_types_v_tags.NNModelTypesVTagsTable;
import com.models.lib.lom.services.tags.TagsService;
import com.models.lib.lom.services.tags.TagsTable;

@Repository
public class ModelTypesService extends Service<ModelTypes> {

	@Autowired
	private ContributorsService sContributors;

	@Autowired
	private NNModelTypesVTagsService sNNTags;

	@Autowired
	private TagsService sTags;

	@Autowired
	private NNFilesVAllService sNNFiles;

	@Autowired
	private FilesService sFiles;

	@Autowired
	public ModelTypesService(Dao<ModelTypes> dao) {
		super(dao);
	}

	@Override
	public void getComplexEntity(ModelTypes e) {
		// Get related author from Contributors table
		e.setAuthor(sContributors.selectOne(ContributorsTable.colId, Comparator.eq, e.getAuthor_id()));
		
		// Get tag ids from nn_model_types_v_tags table
		List<NNModelTypesVTags> nn_tags = sNNTags.select(NNModelTypesVTagsTable.colModelTypeId, Comparator.eq, e.getId());
		List<Long> tag_ids = nn_tags.stream().map(n -> n.getTag_id()).collect(Collectors.toList());
		
		// Get tags from tags table using the tag ids found above
		e.setTags(tag_ids.isEmpty() ? Collections.emptyList() : sTags.select(TagsTable.colId, Comparator.in, tag_ids));

		// Get file ids from nn_files_v_all table
		List<NNFilesVAll> nn_files = sNNFiles.select(NNFilesVAllTable.colSourceId, Comparator.eq, e.getId());
		List<Long> file_ids = nn_files.stream().map(n -> n.getFile_id()).collect(Collectors.toList());

		// Get complete files from files table using the file ids found above
		e.setFiles(file_ids.isEmpty() ? Collections.emptyList() : sFiles.select(FilesTable.colId, Comparator.in, file_ids, true));
	}
}

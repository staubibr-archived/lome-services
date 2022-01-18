package com.lom.services.model_types;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lom.components.services.Dao;
import com.lom.components.services.Query.Comparator;
import com.lom.components.services.Service;
import com.lom.services.contributors.ContributorsService;
import com.lom.services.contributors.ContributorsTable;
import com.lom.services.files.Files;
import com.lom.services.files.FilesService;
import com.lom.services.files.FilesTable;
import com.lom.services.nn_files_v_all.NNFilesVAll;
import com.lom.services.nn_files_v_all.NNFilesVAllService;
import com.lom.services.nn_files_v_all.NNFilesVAllTable;
import com.lom.services.nn_model_types_v_tags.NNModelTypesVTags;
import com.lom.services.nn_model_types_v_tags.NNModelTypesVTagsService;
import com.lom.services.nn_model_types_v_tags.NNModelTypesVTagsTable;
import com.lom.services.tags.TagsService;
import com.lom.services.tags.TagsTable;

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
		List<NNFilesVAll> nn_src_files = sNNFiles.select(NNFilesVAllTable.colSourceId, Comparator.eq, e.getId());
		List<Long> file_ids = nn_src_files.stream().map(n -> n.getFile_id()).collect(Collectors.toList());

		// Get complete files from files table using the file ids found above
		e.setSrc_files(file_ids.isEmpty() ? Collections.emptyList() : sFiles.select(FilesTable.colId, Comparator.in, file_ids, true));

		// Get file ids from nn_files_v_all table
		NNFilesVAll nn_meta_files = sNNFiles.selectOne(NNFilesVAllTable.colMetadataId, Comparator.eq, e.getId());

		if (nn_meta_files == null) return;
		
		Files meta_file = sFiles.selectOne(FilesTable.colId, Comparator.eq, nn_meta_files.getFile_id(), true);
		 
		try {
			e.setMeta(sFiles.getJson(meta_file));
		} catch (IOException e1) {
			e.setMeta(null);
			e1.printStackTrace();
		}
	}
}

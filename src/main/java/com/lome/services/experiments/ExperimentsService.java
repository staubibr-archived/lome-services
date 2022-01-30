

package com.lome.services.experiments;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lome.components.Dao;
import com.lome.components.Service;
import com.lome.components.Query.Comparator;
import com.lome.services.contributors.ContributorsService;
import com.lome.services.contributors.ContributorsTable;
import com.lome.services.model_types.ModelTypesService;
import com.lome.services.model_types.ModelTypesTable;
import com.lome.services.nn_files_v_all.NNFilesVAll;
import com.lome.services.nn_files_v_all.NNFilesVAllService;
import com.lome.services.nn_files_v_all.NNFilesVAllTable;

@Repository
public class ExperimentsService extends Service<Experiments> {

	@Autowired
	private ContributorsService sContributors;

	@Autowired
	private ModelTypesService sModelTypes;

	@Autowired
	private NNFilesVAllService sNNFilesVAll;
	
	@Autowired
	public ExperimentsService(Dao<Experiments> dao) {
		super(dao);
	}

	@Override
	public void getComplexEntity(Experiments e) {
		// Get related author and top model type from Contributors table
		e.setAuthor(sContributors.selectOne(ContributorsTable.colId, Comparator.eq, e.getAuthor_id(), false));
		e.setTop_model_type(sModelTypes.selectOne(ModelTypesTable.colId, Comparator.eq,e.getTop_model_type_id(), false));
		
		// set experiment json files on entity
		List<NNFilesVAll> nn_json_file = sNNFilesVAll.select(NNFilesVAllTable.colExperimentId, Comparator.eq, e.getId(), true);
		e.setExp_files(nn_json_file.stream().map(n -> n.getFile()).collect(Collectors.toList()));
		
		// set document files on entity
		List<NNFilesVAll> nn_doc_files = sNNFilesVAll.select(NNFilesVAllTable.colDocumentId, Comparator.eq, e.getId(), true);		
		e.setDoc_files(nn_doc_files.stream().map(n -> n.getFile()).collect(Collectors.toList()));
		
		// set raw results files on entity
		List<NNFilesVAll> nn_raw_res_files = sNNFilesVAll.select(NNFilesVAllTable.colRawResultId, Comparator.eq, e.getId(), true);
		e.setRaw_res_files(nn_raw_res_files.stream().map(n -> n.getFile()).collect(Collectors.toList()));
		
		// set converted results files on entity
		List<NNFilesVAll> nn_conv_res_files = sNNFilesVAll.select(NNFilesVAllTable.colConvertedResultId, Comparator.eq, e.getId(), true);
		e.setConv_res_files(nn_conv_res_files.stream().map(n -> n.getFile()).collect(Collectors.toList()));
		
		// set visualization files on entity
		List<NNFilesVAll> nn_viz_files = sNNFilesVAll.select(NNFilesVAllTable.colVisualizationId, Comparator.eq, e.getId(), true);
		e.setViz_files(nn_viz_files.stream().map(n -> n.getFile()).collect(Collectors.toList()));
	}
}

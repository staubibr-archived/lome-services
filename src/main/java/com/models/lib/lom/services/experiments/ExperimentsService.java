package com.models.lib.lom.services.experiments;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.models.lib.lom.components.Dao;
import com.models.lib.lom.components.Query;
import com.models.lib.lom.components.Query.Comparator;
import com.models.lib.lom.components.Query.Condition;
import com.models.lib.lom.components.Service;
import com.models.lib.lom.services.contributors.Contributors;
import com.models.lib.lom.services.contributors.ContributorsDao;
import com.models.lib.lom.services.contributors.ContributorsTable;
import com.models.lib.lom.services.files.Files;
import com.models.lib.lom.services.files.FilesDao;
import com.models.lib.lom.services.files.FilesTable;
import com.models.lib.lom.services.model_types.ModelTypes;
import com.models.lib.lom.services.model_types.ModelTypesDao;
import com.models.lib.lom.services.model_types.ModelTypesTable;
import com.models.lib.lom.services.nn_files_v_all.NNFilesVAll;
import com.models.lib.lom.services.nn_files_v_all.NNFilesVAllDao;
import com.models.lib.lom.services.nn_files_v_all.NNFilesVAllTable;
import com.models.lib.lom.services.nn_model_types_v_tags.NNModelTypesVTags;
import com.models.lib.lom.services.nn_model_types_v_tags.NNModelTypesVTagsDao;
import com.models.lib.lom.services.nn_model_types_v_tags.NNModelTypesVTagsTable;
import com.models.lib.lom.services.tags.Tags;
import com.models.lib.lom.services.tags.TagsDao;
import com.models.lib.lom.services.tags.TagsTable;

@Repository
public class ExperimentsService extends Service<Experiments> {

	@Autowired
	private ExperimentsDao dao;
	
	@Autowired
	private ContributorsDao contributorsDao;
	
	@Autowired
	private ModelTypesDao modelTypesDao;
	
	@Autowired
	private NNModelTypesVTagsDao nnTagsDao;

	@Autowired
	private TagsDao TagsDao;
	
	@Autowired
	private FilesDao filesdao;
	
	@Autowired
	private NNFilesVAllDao nnFilesVAllDao;
	
	public ExperimentsService(Dao<Experiments> dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}
	 
	public List<Experiments> select(Query query) {
    	List<Experiments> entities = dao.select(query);
    	
    	if (!query.getComplex()) return entities;

    	return entities.stream().map(e -> {
    		Query author_query = new Query(false, new Condition(ContributorsTable.colId, Comparator.eq, e.getAuthor()));
    		Contributors author = contributorsDao.selectOne(author_query);
    		
    		Query top_model_type_query= new Query(false, new Condition(ModelTypesTable.colId, Comparator.eq,e.getTop_model_type()));
    		ModelTypes top_model_type= modelTypesDao.selectOne(top_model_type_query);
    		
    		Query nn_tags_query = new Query(false, new Condition(NNModelTypesVTagsTable.colModelTypeId, Comparator.eq, e.getTop_model_type()));
    		List<NNModelTypesVTags> nn_tags = nnTagsDao.select(nn_tags_query);

    		List<Long> tag_ids = nn_tags.stream().map(n -> n.getTag_id()).collect(Collectors.toList());
    		
    		Query tags_query = new Query(false, new Condition(TagsTable.colId, Comparator.in, tag_ids));
    		List<Tags> tags = TagsDao.select(tags_query);
    		
    		Query nn_files_v_all_query_json= new Query(false,new Condition(NNFilesVAllTable.colExperimentId,Comparator.eq,e.getId()));
    		NNFilesVAll nn_json_file= nnFilesVAllDao.selectOne(nn_files_v_all_query_json);
    		
    		Files json_file=null;
    		if(nn_json_file!=null) {
    		Query json_file_query= new Query(false, new Condition(FilesTable.colId,Comparator.eq,nn_json_file.getFile_id()));
    		json_file= filesdao.selectOne(json_file_query);
    		}
    		
    		Query nn_files_v_all_query_documents= new Query(false,new Condition(NNFilesVAllTable.colDocumentId,Comparator.eq,e.getId()));
    		List<NNFilesVAll> nn_document_files= nnFilesVAllDao.select(nn_files_v_all_query_documents);
    		
    		List<Long> document_file_ids= nn_document_files.stream().map(d -> d.getFile_id()).collect(Collectors.toList());
    		
    		List<Files> document_files= new ArrayList<>();
    		if(document_file_ids.size()>0) {
    		Query document_files_query= new Query(false, new Condition(FilesTable.colId,Comparator.in,document_file_ids));
    		document_files= filesdao.select(document_files_query);
    		}
    		
    		
    		Query nn_files_v_all_query_raw_result= new Query(false,new Condition(NNFilesVAllTable.colRawResultId,Comparator.eq,e.getId()));
    		List<NNFilesVAll> nn_raw_result_files= nnFilesVAllDao.select(nn_files_v_all_query_raw_result);
    		
    	
    		List<Long> raw_result_file_ids= nn_raw_result_files.stream().map(d -> d.getFile_id()).collect(Collectors.toList());
    		
    		List<Files> raw_result_files=new ArrayList<>();;
    		if(raw_result_file_ids.size()>0) {
    		Query raw_result_files_query= new Query(false, new Condition(FilesTable.colId,Comparator.in,raw_result_file_ids));
    		raw_result_files= filesdao.select(raw_result_files_query);
    		}
    		
    		Query nn_files_v_all_query_converted_result= new Query(false,new Condition(NNFilesVAllTable.colConvertedResultId,Comparator.eq,e.getId()));
    		List<NNFilesVAll> nn_raw_converted_result_files= nnFilesVAllDao.select(nn_files_v_all_query_converted_result);
    		
    		List<Long> converted_result_file_ids= nn_raw_converted_result_files.stream().map(d -> d.getFile_id()).collect(Collectors.toList());
    		
    		List<Files> converted_result_files= new ArrayList<>();
    		if(converted_result_file_ids.size()>0) {
    		Query converted_result_files_query= new Query(false, new Condition(FilesTable.colId,Comparator.in,converted_result_file_ids));
    		converted_result_files= filesdao.select(converted_result_files_query);
    		}
    		
    		Query nn_files_v_all_query_visualization= new Query(false,new Condition(NNFilesVAllTable.colVisualizationId,Comparator.eq,e.getId()));
    		List<NNFilesVAll> nn_visualtion_files= nnFilesVAllDao.select(nn_files_v_all_query_visualization);
    		
    		List<Long> visualization_file_ids= nn_visualtion_files.stream().map(d -> d.getFile_id()).collect(Collectors.toList());
    		
    		List<Files> visualization_files= new ArrayList<>();
    		if(visualization_file_ids.size()>0) {
    		Query visualization_files_query= new Query(false, new Condition(FilesTable.colId,Comparator.in,visualization_file_ids));
    		visualization_files= filesdao.select(visualization_files_query);
    		}
    		
    		return new ExperimentsComplete(e, author,top_model_type, tags, json_file, document_files, raw_result_files, converted_result_files, visualization_files);
    		
    		
    	}).collect(Collectors.toList());        
    }
    
    public Experiments selectOne(Query query) {
    	List<Experiments> entities = this.select(query);
    	
        return entities.isEmpty() ? null : entities.get(0);
    }
	

}

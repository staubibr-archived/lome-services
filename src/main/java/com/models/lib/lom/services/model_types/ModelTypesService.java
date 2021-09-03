package com.models.lib.lom.services.model_types;

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
import com.models.lib.lom.services.nn_model_types_v_tags.NNModelTypesVTags;
import com.models.lib.lom.services.nn_model_types_v_tags.NNModelTypesVTagsDao;
import com.models.lib.lom.services.nn_model_types_v_tags.NNModelTypesVTagsTable;
import com.models.lib.lom.services.tags.Tags;
import com.models.lib.lom.services.tags.TagsDao;
import com.models.lib.lom.services.tags.TagsTable;

@Repository
public class ModelTypesService extends Service<ModelTypes> {

	@Autowired
    private ModelTypesDao dao;

	@Autowired
	private ContributorsDao contributorsDao;

	@Autowired
	private NNModelTypesVTagsDao nnTagsDao;

	@Autowired
	private TagsDao TagsDao;
    
    public ModelTypesService(Dao<ModelTypes> dao) {
		super(dao);
	}
    
    public List<ModelTypes> select(Query query) {
    	List<ModelTypes> entities = dao.select(query);
    	
    	if (!query.getComplex()) return entities;

    	return entities.stream().map(e -> {
    		Query author_query = new Query(false, new Condition(ContributorsTable.colId, Comparator.eq, e.getAuthor()));
    		Contributors author = contributorsDao.selectOne(author_query);
    		
    		Query nn_tags_query = new Query(false, new Condition(NNModelTypesVTagsTable.colModelTypeId, Comparator.eq, e.getId()));
    		List<NNModelTypesVTags> nn_tags = nnTagsDao.select(nn_tags_query);

    		List<Long> tag_ids = nn_tags.stream().map(n -> n.getTag_id()).collect(Collectors.toList());
    		
    		Query tags_query = new Query(false, new Condition(TagsTable.colId, Comparator.in, tag_ids));
    		List<Tags> tags = TagsDao.select(tags_query);
    		
    		return new ModelTypesComplete(e, author, tags);
    	}).collect(Collectors.toList());        
    }
    
    public ModelTypes selectOne(Query query) {
    	List<ModelTypes> entities = this.select(query);
    	
        return entities.isEmpty() ? null : entities.get(0);
    }
}

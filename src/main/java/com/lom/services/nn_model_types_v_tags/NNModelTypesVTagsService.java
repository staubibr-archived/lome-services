package com.lom.services.nn_model_types_v_tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lom.components.services.Dao;
import com.lom.components.services.Service;
import com.lom.components.services.Query.Comparator;
import com.lom.services.tags.TagsService;
import com.lom.services.tags.TagsTable;

@Repository
public class NNModelTypesVTagsService extends Service<NNModelTypesVTags> {

	// Circular dependy because of ModelTypesService
	// @Autowired
	// private ModelTypesService sModelTypes;

	@Autowired
	private TagsService sTags;
	
	@Autowired
	public NNModelTypesVTagsService(Dao<NNModelTypesVTags> dao) {
		super(dao);
	}

	@Override
	public void getComplexEntity(NNModelTypesVTags e) {
		// e.setModel_type(sModelTypes.selectOne(ModelTypesTable.colId, Comparator.eq, e.getModel_type_id(), false));
		e.setTag(sTags.selectOne(TagsTable.colId, Comparator.eq, e.getTag_id(), false));
	}
}

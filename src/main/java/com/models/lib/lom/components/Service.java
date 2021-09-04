package com.models.lib.lom.components;

import java.util.List;
import java.util.stream.Collectors;

import com.models.lib.lom.components.Query.Comparator;
import com.models.lib.lom.components.Query.Condition;

public abstract class Service<T> {

    private Dao<T> dao;

    public Service(Dao<T> dao) {
    	this.dao = dao;
    }
    
    public Object create(T entity) {
        return dao.create(entity);
    }
    
    public List<Object> create(List<T> entities) {
        return dao.create(entities);
    }
    
    public List<T> select(Query query, Boolean complex) {
        List<T> entities = dao.select(query);

        return complex ? entities.stream().map(e -> this.getComplexEntity(e)).collect(Collectors.toList()) : entities;
    }
    
    public List<T> select(String col, Comparator comp, Object value, Boolean complex) {
		Query query = new Query();
    	
		if (value != null) query.addCondition(new Condition(col, comp, value));
		
		return select(query, complex);
    }
    
    public List<T> select(String col, Comparator comp, Object value) {    	
		return select(col, comp, value, false);
    }

    public T selectOne(Query query, Boolean complex) {
        List<T> entities = select(query, complex);

        return entities.isEmpty() ? null : entities.get(0);
    }
    
	public T selectOne(String col, Comparator comp, Object value, Boolean complex) {
		Query query = new Query();
    	
		if (value != null) query.addCondition(new Condition(col, comp, value));
		
		return selectOne(query, complex);
	}
	
	public T selectOne(String col, Comparator comp, Object value) {	
		return selectOne(col, comp, value, false);
	}

    public Object update(T entity) {
        return dao.update(entity);
    }
    
    public List<Object> update(List<T> entities) {
        return dao.update(entities);
    }

    public Object delete(Object key) {
        return dao.delete(key);
    }
    
    public List<Object> delete(List<Object> ids) {        
        return dao.delete(ids);
    }
    
    public abstract T getComplexEntity(T entity);
}


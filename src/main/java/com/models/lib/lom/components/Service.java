package com.models.lib.lom.components;

import java.util.List;

public class Service<T> {

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
    
    public List<T> select(Query query) {
        return dao.select(query);
    }
    
    public T selectOne(Query query) {
        return dao.selectOne(query);
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
}


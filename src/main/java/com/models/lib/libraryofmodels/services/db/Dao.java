package com.models.lib.libraryofmodels.services.db;

import java.util.List;

public interface Dao<T extends Persistable> {

    void create(T entity);

    List<T> get(List<String> keys);

    Page<T> search(DbWhereClause query);
}

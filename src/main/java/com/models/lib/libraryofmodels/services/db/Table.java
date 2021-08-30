package com.models.lib.libraryofmodels.services.db;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.RowMapper;

public interface Table<K> {

    String name();

    default Collection<String> allCols() {
        return getTableColumns().stream().map(DbColumn::name).collect(Collectors.toList());
    }

    default List<DbColumn> pkColumns() {
        return getTableColumns().stream().filter(DbColumn::isPkColumn).collect(Collectors.toList());
    }

    default Collection<DbColumn> allColsExceptPk() {
        return getTableColumns().stream().filter(col -> !col.isPkColumn()).collect(Collectors.toList());
    }

    default void insertIntoParamMap(DbColumn key, Long value, Map<String, String> paramMap) {
		if (value != null) paramMap.put(key.name(), value.toString());
	}
	
    default void insertIntoParamMap(DbColumn key, String value, Map<String, String> paramMap) {
		if (value != null) paramMap.put(key.name(), value);
	}

    default void insertIntoParamMap(DbColumn key, Date value, Map<String, String> paramMap) {
		if (value != null) paramMap.put(key.name(), value.toString());
	}

    Class<? extends DbColumn> getTableColumnClass();

    RowMapper<K> rowMapper();

    Map<String, String> getParamMap(K entity);

    default Collection<DbColumn> getTableColumns() {
        return Arrays.asList(getTableColumnClass().getEnumConstants());
    }

    interface DbColumn {
        String name();
        boolean isPkColumn();
    }
}

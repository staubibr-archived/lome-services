package com.models.lib.libraryofmodels.services.db;

import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

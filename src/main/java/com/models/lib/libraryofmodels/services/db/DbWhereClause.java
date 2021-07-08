package com.models.lib.libraryofmodels.services.db;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.models.lib.libraryofmodels.services.db.Table.DbColumn;

@Data
public class DbWhereClause {

    private Integer pageSize;
    private Integer pageNumber;
    private Integer offset;
    private final List<Condition> conditions = new ArrayList<>();

    public DbWhereClause(Integer pageSize, Integer pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public void addCondition(Condition condition) {
        conditions.add(condition);
    }

    @Data
    public static class Condition {
        private final DbColumn column;
        private final Comparator comparator;
        private final Object value;

        public Condition(DbColumn column, Comparator comparator, Object value) {
            this.column = column;
            this.comparator = comparator;
            this.value = value;
        }
    }

    public enum Comparator {
        eq, in, not_in;
    }
}

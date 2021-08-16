package com.models.lib.libraryofmodels.services.db;

import lombok.extern.slf4j.Slf4j;

import com.google.common.collect.ImmutableMap;
import org.apache.logging.log4j.util.Strings;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractDao<T extends Persistable> implements Dao<T> {

    public static final String SELECT = "SELECT %s FROM %s";
    public static final String SELECT_WHERE = "SELECT %s FROM %s WHERE %s";
    public static final String SELECT_COUNT = "SELECT COUNT(*) FROM %s";
    public static final String SELECT_COUNT_WHERE = SELECT_COUNT + " WHERE %s";
    public static final String PAGINATION = " LIMIT %d OFFSET %d ";
    public static final String EQ_PARAMETER = "%s=:%s";
    public static final String IN_PARAMETER = "%s IN (:%s)";
    public static final String NOT_IN_PARAMETER = "%s NOT IN (:%s)";
    public static final String INSERT = "INSERT INTO %s VALUES(%s)";
    public static final String INSERT_IN_COLUMNS= "INSERT INTO %s (%s) VALUES(%s)";
    public static final String NN_QUERY = "SELECT %s FROM %s WHERE %s IN (SELECT %s FROM %s WHERE %s in (SELECT %s FROM %s WHERE %s))";
    public static final String UPDATE = "UPDATE %s SET %s WHERE %s";
    public static final String DELETE_WHERE = "DELETE FROM %s WHERE %s";

    protected final NamedParameterJdbcTemplate jdbcTemplate;
    protected final Table<T> table;

    public AbstractDao(NamedParameterJdbcTemplate jdbcTemplate, Table<T> table) {
        this.jdbcTemplate = jdbcTemplate;
        this.table = table;
    }

    @Override
    public void create(T entity) {
    	Map<String,String> paramMap= table.getParamMap(entity);
        String query = String.format(INSERT_IN_COLUMNS, table.name(),getColumns(paramMap), getTableColumnsForInsertFromParamMap(paramMap));
        String query2 = String.format(INSERT, table.name(),getTableColsForInsert());
    	String query3=getTableColsForInsert();
    	String query4= getTableColumnsForInsertFromParamMap(paramMap);
        jdbcTemplate.update(query, paramMap);
    }
   

    @Override
    public void update(List<T> entities) {
        if (!CollectionUtils.isEmpty(entities)) {
            String query = String.format(UPDATE,
                    table.name(),
                    table.allColsExceptPk().stream().map(col -> String.format(EQ_PARAMETER, col, col)).collect(Collectors.joining(",")),
                    String.format(EQ_PARAMETER, table.pkColumns().get(0), table.pkColumns().get(0)));
            entities.forEach(entity -> jdbcTemplate.update(query, table.getParamMap(entity)));
        }
    }

    @Override
    public void delete(List<String> keys) {
        if (!CollectionUtils.isEmpty(keys)) {
            String pkColumn = table.pkColumns().get(0).name();
            String query = String.format(DELETE_WHERE,
                    table.name(),
                    String.format(IN_PARAMETER, pkColumn, pkColumn));
            jdbcTemplate.update(query, ImmutableMap.of(pkColumn, keys));
        }
    }

    private String getTableColsForInsert() {
        return table.allCols().stream().map(col -> ":" + col).collect(Collectors.joining(","));
    }

    private String getTableColumnsForInsertFromParamMap(Map<String,String> paramMap) {
    	return paramMap.keySet().stream().map(col -> ":" + col).collect(Collectors.joining(","));	
    	
    }
    
    private String getColumns(Map<String,String> paramMap) {
    	return paramMap.keySet().stream().map(col -> col).collect(Collectors.joining(","));
    }
    @Override
    public List<T> get(List<String> keys) {
        String pkCol = table.pkColumns().get(0).name();
        String query = String.format(SELECT_WHERE, String.join(",", table.allCols()), table.name(), String.format(IN_PARAMETER, pkCol, pkCol));
        Map<String, Object> namedParamsMap = new HashMap<String, Object>() {{ put(pkCol, keys); }};
        List<T> entities = jdbcTemplate.query(query, namedParamsMap, table.rowMapper());
        entities.forEach(entity -> setRelated(entity));
        return entities;
    }

    public void setRelated(T entity) {
    }

    @Override
    public Page<T> search(DbWhereClause query) {
        Map<String, Object> namedParams = getNamedParams(query);
        String whereClause = getWhereClause(query);
        String cols = String.join(",", table.allCols());
        String selectClause = Strings.isNotBlank(whereClause) ? String.format(SELECT_WHERE, cols, table.name(), whereClause) : String.format(SELECT, cols, table.name());
        int offset = query.getOffset() != null ? query.getOffset() : (query.getPageNumber() - 1) * query.getPageSize();
        String sqlFetch = selectClause + " " + String.format(PAGINATION, query.getPageSize(), offset);
        List<T> data = jdbcTemplate.query(sqlFetch, namedParams, table.rowMapper());
            
        return Page.<T>builder().data(data).currentPage(query.getPageNumber()).pageSize(query.getPageSize()).count(data.size()).build();
    }

    private String getWhereClause(DbWhereClause query) {
        return query.getConditions().stream().map(condition -> {
            if (condition.getComparator().equals(DbWhereClause.Comparator.eq)) {
                return String.format(EQ_PARAMETER, condition.getColumn().name(), condition.getColumn().name());
            } else if (condition.getComparator().equals(DbWhereClause.Comparator.in)) {
                return String.format(IN_PARAMETER, condition.getColumn().name(), condition.getColumn().name());
            } else {
                return String.format(NOT_IN_PARAMETER, condition.getColumn().name(), condition.getColumn().name());
            }
        }).collect(Collectors.joining(" AND "));
    }

    private Map<String, Object> getNamedParams(DbWhereClause query) {
        Map<String, Object> namedParams = new HashMap<>();
        query.getConditions().forEach(condition -> namedParams.put(condition.getColumn().name(), condition.getValue()));
        return namedParams;
    }
}

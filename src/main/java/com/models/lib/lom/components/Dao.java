package com.models.lib.lom.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.CollectionUtils;

public class Dao<T> {

    public static final String EQ_PARAMETER = "%s=:%s";
    public static final String IN_PARAMETER = "%s IN (:%s)";
    public static final String NOT_IN_PARAMETER = "%s NOT IN (:%s)";
    public static final String NN_QUERY = "SELECT %s FROM %s WHERE %s IN (SELECT %s FROM %s WHERE %s in (SELECT %s FROM %s WHERE %s))";

    public static final String CREATE = "INSERT INTO %s (%s) VALUES(%s)";
    public static final String READ = "SELECT %s FROM %s";
    public static final String UPDATE = "UPDATE %s SET %s WHERE %s";
    public static final String DELETE = "DELETE FROM %s WHERE %s";
    public static final String WHERE = " WHERE %s";
    public static final String PAGINATION = " LIMIT %d OFFSET %d ";

    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;
    
    protected final Table<T> table;

    public Dao(Table<T> table) {
        this.table = table;
    }
    
    private interface Template {
        public String format(String value);
    }
    
    private String getColumns(MapSqlParameterSource sqlMap, Template template) {
    	Map<String, Object> map = sqlMap.getValues();
    	
    	return map.keySet().stream().map(col -> template.format(col)).collect(Collectors.joining(","));
    }
    
    private MapSqlParameterSource MapToSqlMap(Map<String, Object> map) {
    	MapSqlParameterSource sqlMap = new MapSqlParameterSource();
    	
    	map.entrySet().stream().forEach(entry -> sqlMap.addValue(entry.getKey(), entry.getValue()));
    	
    	return sqlMap;
    }
    
    private MapSqlParameterSource sqlMapEntity(T entity) {    	
    	return MapToSqlMap(table.mapEntity(entity));
    }
    
    private MapSqlParameterSource sqlMapEntityNoPk(T entity) {    	
    	return MapToSqlMap(table.mapEntityNoPk(entity));
    }

    public Object create(T entity) {
        KeyHolder kh = new GeneratedKeyHolder();
    	MapSqlParameterSource map = sqlMapEntityNoPk(entity);
        String query = String.format(CREATE, table.name(), getColumns(map, s -> s), getColumns(map, s -> ":" + s));
        
        jdbcTemplate.update(query, map, kh);

        return kh.getKey();
    }
    
    public List<Object> create(List<T> entities) {        
        if (CollectionUtils.isEmpty(entities)) return new ArrayList<Object>();
        
        return entities.stream().map(entity -> create(entity)).collect(Collectors.toList());
    }
    
    public List<T> select(Query query) {
        String sql = String.format(Dao.READ, "*", table.name());
    	        
        if (query.getConditions().size() > 0) sql += query.getSQL();
        
        return jdbcTemplate.query(sql, query.ToMap(), table.rowMapper());
    }
    
    public T selectOne(Query query) {
    	List<T> entities = select(query);
    	
        return entities.isEmpty() ? null : entities.get(0);
    }
    /*
    public Page<T> selectByPage(Query query) {
        List<T> entities = select(query);
        
        return Page.<T>builder().data(entities).currentPage(query.getPageNumber()).pageSize(query.getPageSize()).count(entities.size()).build();
    }
     */
    public Object update(T entity) {
    	MapSqlParameterSource map = sqlMapEntity(entity);

    	String columns = getColumns(map, s -> String.format(EQ_PARAMETER, s, s));
    	String where = String.format(EQ_PARAMETER, table.pk(), table.pk());
        String query = String.format(UPDATE, table.name(), columns, where);

    	jdbcTemplate.update(query, map);

        return map.getValue(table.pk());
    }
    
    public List<Object> update(List<T> entities) {        
        if (CollectionUtils.isEmpty(entities)) return new ArrayList<Object>();

        return entities.stream().map(entity -> update(entity)).collect(Collectors.toList());
    }

    public Object delete(Object key) {        
        String query = String.format(DELETE, table.name(), String.format(EQ_PARAMETER, table.pk(), table.pk()));
                
        jdbcTemplate.update(query, MapToSqlMap(Map.of(table.pk(), key)));
        
        return key;
    }
    
    public List<Object> delete(List<Object> ids) {        
        if (CollectionUtils.isEmpty(ids)) return new ArrayList<Object>();

        return ids.stream().map(id -> delete(id)).collect(Collectors.toList());
    }
}

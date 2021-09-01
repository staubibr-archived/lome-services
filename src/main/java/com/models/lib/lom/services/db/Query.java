package com.models.lib.lom.services.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class Query {
    
    private Integer pageSize;
    private Integer pageNumber;
    private final List<Condition> conditions = new ArrayList<>();

    public Query(Integer pageSize, Integer pageNumber) {
    	this.pageSize = pageSize == null ? 20 : pageSize;
    	this.pageNumber = pageNumber == null ? 1 : pageNumber;
    }
    
    public Query() {
    	this(null, null);
    }
    
    public void addCondition(Condition condition) {
        conditions.add(condition);
    }

    public enum Comparator {
        eq, in, not_in;
    }
    
    public int getOffset() {
    	return (getPageNumber() - 1) * getPageSize();
    }
    
	public String getSQL() {
    	String where = getConditions().stream().map(c -> c.getSQL()).collect(Collectors.joining(" AND "));
    	
    	return String.format(Dao.WHERE, where) + " " + String.format(Dao.PAGINATION, getPageSize(), getOffset());
	};

	public Map<String, Object> ToMap() {
        Map<String, Object> map = new HashMap<>();
        
        getConditions().forEach(condition -> map.put(condition.getColumn(), condition.getValue()));
        
        return map;
    }

    @Data
    public static class Condition {
        private final String column;
        private final Comparator comparator;
        private final Object value;

        public Condition(String column, Comparator comparator, Object value) {
            this.column = column;
            this.comparator = comparator;
            this.value = value;
        }
        
        public String getSQL() {
        	String comp = Dao.NOT_IN_PARAMETER;
        	
        	if (comparator.equals(Comparator.eq)) comp = Dao.EQ_PARAMETER;

        	if (comparator.equals(Comparator.in)) comp = Dao.IN_PARAMETER;
        	
            return String.format(comp, getColumn(), getColumn());	
        }
    }
}
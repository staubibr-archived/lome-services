package com.models.lib.lom.components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import lombok.Data;

@Data
public class Query {
    
	// private Boolean complex;
    private final List<Condition> conditions = new ArrayList<>();

    public Query(/*Boolean complex, */Condition... conditions) {
    	// this.complex = complex == null ? false : complex;
    	
    	Stream.of(conditions).forEach(c -> addCondition(c));
    }
    
    public Query(/*Boolean complex*/) {
    	// this.complex = complex == null ? false : complex;
    }
    
    // public Query() {
    //	this(null);
    // }
    
    public void addCondition(Condition condition) {
        conditions.add(condition);
    }

    public enum Comparator {
        eq, in, not_in;
    }
    
	public String getSQL() {
    	String where = getConditions().stream().map(c -> c.getSQL()).collect(Collectors.joining(" AND "));
    	
    	return String.format(Dao.WHERE, where);
	};

	public MapSqlParameterSource ToMap() {
		MapSqlParameterSource map = new MapSqlParameterSource();
        
        getConditions().forEach(condition -> map.addValue(condition.getColumn(), condition.getValue()));
        
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
package com.lome.unused;

import com.lome.components.Dao;
import com.lome.components.Query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PagedQuery extends Query {
    
    private Integer pageSize;
    private Integer pageNumber;

    public PagedQuery(Integer pageSize, Integer pageNumber) {
    	super();
    	
    	this.pageSize = pageSize == null ? 20 : pageSize;
    	this.pageNumber = pageNumber == null ? 1 : pageNumber;
    }
    
    public PagedQuery() {
    	this(null, null);
    }
    
    public int getOffset() {
    	return (getPageNumber() - 1) * getPageSize();
    }
    
	public String getSQL() {
    	return super.getSQL() + " " + String.format(Dao.PAGINATION, getPageSize(), getOffset());
	};
}
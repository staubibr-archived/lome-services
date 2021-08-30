package com.models.lib.libraryofmodels.services.contributors;

import java.util.List;

import com.models.lib.libraryofmodels.services.contributors.ContributorsTable.ContributorsDbColumn;
import com.models.lib.libraryofmodels.services.db.DbWhereClause;
import com.models.lib.libraryofmodels.services.db.DbWhereClause.Comparator;
import com.models.lib.libraryofmodels.services.db.DbWhereClause.Condition;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ContributorsQuery {

    private List<Long> ids;
    private Integer pageSize;
    private Integer pageNumber;
    
    public DbWhereClause ToWhere() {
        DbWhereClause ret = new DbWhereClause(getPageSize(), getPageNumber());
        
        if (getIds() != null) {
            ret.addCondition(new Condition(ContributorsDbColumn.id, Comparator.in, getIds()));
        }

        ret.setPageNumber(0);
        ret.setOffset(0);
        ret.setPageSize(100);
        
        return ret;
    }
}
package com.models.lib.libraryofmodels.services.experiments;

import java.util.List;

import com.models.lib.libraryofmodels.services.db.DbWhereClause;
import com.models.lib.libraryofmodels.services.db.DbWhereClause.Comparator;
import com.models.lib.libraryofmodels.services.db.DbWhereClause.Condition;
import com.models.lib.libraryofmodels.services.experiments.ExperimentsTable.ExperimentsDbColumn;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ExperimentsQuery {

    private List<String> ids;
    private List<String> names;
    private Integer pageSize;
    private Integer pageNumber;
    
    public DbWhereClause ToWhere() {
        DbWhereClause ret = new DbWhereClause(getPageSize(), getPageNumber());
       
        if (getIds() != null) {
            ret.addCondition(new Condition(ExperimentsDbColumn.id, Comparator.in, getIds()));
        }
        
        if (getNames() != null) {
            ret.addCondition(new Condition(ExperimentsDbColumn.name, Comparator.in, getNames()));
        }
        
        return ret;
    }
}
/*package com.models.lib.libraryofmodels.services.contributors.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.libraryofmodels.services.db.Table;

@Component
public class ContributorsTable implements Table<Contributor> {

    @Override
    public String name() {
        return "contributors";
    }

    @Override
    public RowMapper<Contributor> rowMapper() {
        return new ContributorsEntityMapper();
    }

    private static class ContributorsEntityMapper implements RowMapper<Contributor> {
        @Override
        public Contributor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Contributor res = new Contributor();
            res.setId(resultSet.getLong(ContributorsDbColumn.id.name()));
            res.setFirst_name(resultSet.getString(ContributorsDbColumn.first_name.name()));
            res.setLast_name(resultSet.getString(ContributorsDbColumn.last_name.name()));
            res.setMiddle_name(resultSet.getString(ContributorsDbColumn.middle_name.name()));
            res.setEmail(resultSet.getString(ContributorsDbColumn.email.name()));
            res.setAffiliation(resultSet.getString(ContributorsDbColumn.affiliation.name()));
            res.setCreation_date(resultSet.getDate(ContributorsDbColumn.creation_date.name()));
            return res;
        }
    }

    public enum ContributorsDbColumn implements DbColumn {

        id(true), first_name(true), last_name(true), middle_name(false), email(true), affiliation(false), creation_date(false);

        private final boolean pkCol;

        ContributorsDbColumn(boolean pkCol) {
            this.pkCol = pkCol;
        }

        @Override
        public boolean isPkColumn() {
            return pkCol;
        }
    }

    @Override
    public Class<? extends DbColumn> getTableColumnClass() {
        return ContributorsDbColumn.class;
    }
}
*/
package com.models.lib.libraryofmodels.services.file_types;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.libraryofmodels.services.db.Table;


@Component
public class FileTypesTable implements Table<FileTypes> {

    @Override
    public String name() {
        return "file_types";
    }

    @Override
    public RowMapper<FileTypes> rowMapper() {
        return new FileTypesEntityMapper();
    }

    @Override
    public Map<String, String> getParamMap(FileTypes entity) {
       Map<String,String> paramMap= new HashMap<String, String>();
        
       insertIntoParamMap(FileTypesDbColumn.id, entity.getId(), paramMap);
       insertIntoParamMap(FileTypesDbColumn.description, entity.getDescription(), paramMap);
       insertIntoParamMap(FileTypesDbColumn.extension, entity.getExtension(), paramMap);

       return paramMap;
    }
	
    private static class FileTypesEntityMapper implements RowMapper<FileTypes> {
        @Override
        public FileTypes mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        	FileTypes res = new FileTypes();
            res.setId(resultSet.getLong(FileTypesDbColumn.id.name()));
            res.setDescription(resultSet.getString(FileTypesDbColumn.description.name()));
            res.setExtension(resultSet.getString(FileTypesDbColumn.extension.name()));
            return res;
        }
    }

    public enum FileTypesDbColumn implements DbColumn {
        id(true), description(false), extension(false);

        private final boolean pkCol;

        FileTypesDbColumn(boolean pkCol) {
            this.pkCol = pkCol;
        }

        @Override
        public boolean isPkColumn() {
            return pkCol;
        }
    }

    @Override
    public Class<? extends DbColumn> getTableColumnClass() {
        return FileTypesDbColumn.class;
    }
}

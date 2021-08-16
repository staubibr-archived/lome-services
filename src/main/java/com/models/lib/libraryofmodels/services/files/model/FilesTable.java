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
package com.models.lib.libraryofmodels.services.files.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.libraryofmodels.services.db.Table;
import com.models.lib.libraryofmodels.services.db.Table.DbColumn;


@Component
public class FilesTable implements Table<Files> {

    @Override
    public String name() {
        return "files";
    }

    @Override
    public RowMapper<Files> rowMapper() {
        return new FilesEntityMapper();
    }

    @Override
    public Map<String, String> getParamMap(Files entity) {
        Map<String,String> paramMap= new HashMap<String, String>();
        insertIntoParamMap(FilesDbColumn.id, entity.getId(), paramMap);
        insertIntoParamMap(FilesDbColumn.name,entity.getName(),paramMap);
        insertIntoParamMap(FilesDbColumn.file_type_id, entity.getFile_type_id(), paramMap);
        insertIntoParamMap(FilesDbColumn.last_modification, entity.getLast_modifications(), paramMap);
        insertIntoParamMap(FilesDbColumn.last_author, entity.getLast_author(), paramMap);
        insertIntoParamMap(FilesDbColumn.path,entity.getPath(),paramMap);
        insertIntoParamMap(FilesDbColumn.model_type_id,entity.getModel_type_id(),paramMap);
        insertIntoParamMap(FilesDbColumn.document_id, entity.getDocument_id(), paramMap);
        insertIntoParamMap(FilesDbColumn.experiment_id, entity.getExperiment_id(), paramMap);
        insertIntoParamMap(FilesDbColumn.raw_result_id, entity.getRaw_result_id(), paramMap);
        insertIntoParamMap(FilesDbColumn.converted_result_id,entity.getConverted_result_id(),paramMap);
        insertIntoParamMap(FilesDbColumn.visualization_id, entity.getVisualization_id(), paramMap);
//            put(FilesDbColumn.id.name(), entity.getId().toString());
//            put(FilesDbColumn.name.name(), entity.getName());
//            put(FilesDbColumn.file_type_id.name(), entity.getFile_type_id().toString());
//            put(FilesDbColumn.last_modification.name(), entity.getLast_modifications().toString());
//            put(FilesDbColumn.last_author.name(), entity.getLast_author().toString());
//            put(FilesDbColumn.path.name(), entity.getPath());
//            put(FilesDbColumn.model_type_id.name(), entity.getModel_type_id().toString());
//            put(FilesDbColumn.document_id.name(), entity.getDocument_id().toString());
//            put(FilesDbColumn.experiment_id.name(), entity.getExperiment_id().toString());
//            put(FilesDbColumn.raw_result_id.name(), entity.getRaw_result_id().toString());
//            put(FilesDbColumn.converted_result_id.name(), entity.getConverted_result_id().toString());
//            put(FilesDbColumn.visualization_id.name(), entity.getVisualization_id().toString());
       
         return paramMap;
    }
    
    private void insertIntoParamMap(DbColumn key, Long value, Map<String, String> paramMap) {

		if (value != null) {
			paramMap.put(key.name(), value.toString());
		}

	}

	private void insertIntoParamMap(DbColumn key, Date value, Map<String, String> paramMap) {

		if (value != null) {
			paramMap.put(key.name(), value.toString());
		}

	}
	
	private void insertIntoParamMap(DbColumn key, String value, Map<String, String> paramMap) {

		if (value != null) {
			paramMap.put(key.name(), value);
		}

	}

    private static class FilesEntityMapper implements RowMapper<Files> {
        @Override
        public Files mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Files res = new Files();
            res.setId(resultSet.getLong(FilesDbColumn.id.name()));
            res.setName(resultSet.getString(FilesDbColumn.name.name()));
            res.setFile_type_id(resultSet.getLong(FilesDbColumn.file_type_id.name()));
            res.setLast_modifications(resultSet.getDate(FilesDbColumn.last_modification.name()));
            res.setPath(resultSet.getString(FilesDbColumn.path.name()));
            res.setLast_author(resultSet.getLong(FilesDbColumn.last_author.name()));
            res.setModel_type_id(resultSet.getLong(FilesDbColumn.model_type_id.name()));
            res.setDocument_id(resultSet.getLong(FilesDbColumn.document_id.name()));
            res.setExperiment_id(resultSet.getLong(FilesDbColumn.experiment_id.name()));
            res.setRaw_result_id(resultSet.getLong(FilesDbColumn.raw_result_id.name()));
            res.setConverted_result_id(resultSet.getLong(FilesDbColumn.converted_result_id.name()));
            res.setVisualization_id(resultSet.getLong(FilesDbColumn.visualization_id.name()));
            return res;
        }
    }

    public enum FilesDbColumn implements DbColumn {

        id(true), name(false), file_type_id(false),
        last_modification(false), last_author(false), path(false), model_type_id(false),
        document_id(false), experiment_id(false), raw_result_id(false), converted_result_id(false),
        visualization_id(false);

        private final boolean pkCol;

        FilesDbColumn(boolean pkCol) {
            this.pkCol = pkCol;
        }

        @Override
        public boolean isPkColumn() {
            return pkCol;
        }
    }

    @Override
    public Class<? extends DbColumn> getTableColumnClass() {
        return FilesDbColumn.class;
    }

}

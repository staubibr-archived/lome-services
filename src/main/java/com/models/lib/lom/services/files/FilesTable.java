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
            res.setId(resultSet.getLong(ContributorsDbColumn.id));
            res.setFirst_name(resultSet.getString(ContributorsDbColumn.first_name));
            res.setLast_name(resultSet.getString(ContributorsDbColumn.last_name));
            res.setMiddle_name(resultSet.getString(ContributorsDbColumn.middle_name));
            res.setEmail(resultSet.getString(ContributorsDbColumn.email));
            res.setAffiliation(resultSet.getString(ContributorsDbColumn.affiliation));
            res.setCreation_date(resultSet.getDate(ContributorsDbColumn.creation_date));
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
package com.models.lib.lom.services.files;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.lom.services.db.Table;

@Component
public class FilesTable extends Table<Files> {

    public String name() {
        return "files";
    }
    
	static String colId = "id";
	static String colName = "name";
	static String colFileTypeId = "file_type_id";
	static String colLastModification = "last_modification";
	static String colLastAuthor = "last_author";
	static String colPath = "path";
	static String colModelTypeId = "model_type_id";
	static String colDocumentId = "document_id";
	static String colExperimentId = "experiment_id";
	static String colRawResultId = "raw_result_id";
	static String colConvertedResultId = "converted_result_id";
	static String colVisualizationId = "visualization_id";
	
	public String pk() {
		return colId;
	}
	
    public List<String> columns() {
		return List.of(colId, colName, colFileTypeId, colLastModification, colLastAuthor, colPath, colModelTypeId, colDocumentId, colExperimentId, colRawResultId, colConvertedResultId, colVisualizationId);
    }

    public Map<String, Object> mapEntity(Files entity) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put(colId, entity.getId());
        map.put(colName, entity.getName());
        map.put(colFileTypeId, entity.getFile_type_id());
        map.put(colLastModification, entity.getLast_modification());
        map.put(colLastAuthor, entity.getLast_author());
        map.put(colPath, entity.getPath());

        return map;
    }
    
    private static class FilesEntityMapper implements RowMapper<Files> {
        @Override
        public Files mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Files res = new Files();

            res.setId(resultSet.getLong(colId));
            res.setName(resultSet.getString(colName));
            res.setFile_type_id(resultSet.getLong(colFileTypeId));
            res.setLast_modification(resultSet.getDate(colLastModification));
            res.setLast_author(resultSet.getLong(colLastAuthor));
            res.setPath(resultSet.getString(colPath));

            return res;
        }
    }

    public RowMapper<Files> rowMapper() {
        return new FilesEntityMapper();
    }
}

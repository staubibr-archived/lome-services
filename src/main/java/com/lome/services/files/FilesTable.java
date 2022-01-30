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
package com.lome.services.files;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.lome.components.Table;

@Component
public class FilesTable extends Table<Files> {

	public String name() {
		return "files";
	}

	public static String colId = "id";
	public static String colName = "name";
	public static String colFileTypeId = "file_type_id";
	public static String colLastModification = "last_modification";
	public static String colLastAuthorId = "last_author";
	public static String colPath = "path";

	public String pk() {
		return colId;
	}

	public List<String> columns() {
		return List.of(colId, colName, colFileTypeId, colLastModification, colLastAuthorId, colPath);
	}

	public Map<String, Object> mapEntity(Files entity) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put(colId, entity.getId());
		map.put(colName, entity.getName());
		map.put(colFileTypeId, entity.getFile_type_id());
		map.put(colLastModification, entity.getLast_modification());
		map.put(colLastAuthorId, entity.getLast_author_id());
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
			res.setLast_author_id(resultSet.getLong(colLastAuthorId));
			res.setPath(resultSet.getString(colPath));

			return res;
		}
	}

	public RowMapper<Files> rowMapper() {
		return new FilesEntityMapper();
	}
}

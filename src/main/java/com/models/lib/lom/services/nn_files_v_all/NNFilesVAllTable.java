package com.models.lib.lom.services.nn_files_v_all;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.lom.services.db.Table;

@Component
public class NNFilesVAllTable extends Table<NNFilesVAll> {

    public String name() {
        return "nn_files_v_all";
    }

	static String colId = "id";
	static String colFileId = "file_id";
	static String colDocumentId = "document_id";
	static String colSourceId = "source_id";
	static String colExperimentId = "experiment_id";
	static String colRawResultId = "raw_result_id";
	static String colConvertedResultId = "converted_result_id";
	static String colVisualizationId = "visualization_id";
	
	public String pk() {
		return colId;
	}
	
    public List<String> columns() {
		return List.of(colFileId, colDocumentId, colSourceId, colExperimentId, colRawResultId, colConvertedResultId, colVisualizationId);
    }

    public Map<String, Object> mapEntity(NNFilesVAll entity) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put(colId, entity.getId());
        map.put(colFileId, entity.getFile_id());
        map.put(colDocumentId, entity.getDocument_id());
        map.put(colSourceId, entity.getSource_id());
        map.put(colExperimentId, entity.getExperiment_id());
        map.put(colRawResultId, entity.getRaw_result_id());
        map.put(colConvertedResultId, entity.getConverted_result_id());
        map.put(colVisualizationId, entity.getVisualization_id());

        return map;
    }
    
    private static class NNFilesVAllEntityMapper implements RowMapper<NNFilesVAll> {
        @Override
        public NNFilesVAll mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        	NNFilesVAll res = new NNFilesVAll();

            res.setId(resultSet.getLong(colId));
            res.setFile_id(resultSet.getLong(colFileId));
            res.setDocument_id(resultSet.getLong(colDocumentId));
            res.setSource_id(resultSet.getLong(colSourceId));
            res.setExperiment_id(resultSet.getLong(colExperimentId));
            res.setRaw_result_id(resultSet.getLong(colRawResultId));
            res.setConverted_result_id(resultSet.getLong(colConvertedResultId));
            res.setVisualization_id(resultSet.getLong(colVisualizationId));

            return res;
        }
    }

    public RowMapper<NNFilesVAll> rowMapper() {
        return new NNFilesVAllEntityMapper();
    }
}

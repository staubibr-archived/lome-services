import requests
import json


def insert(file_id, document_id=None, source_id=None, metadata_id=None, experiment_id=None, raw_result_id=None, converted_result_id=None, visualization_id=None):
    return {
            "file_id": file_id,
            "document_id": document_id,
            "source_id": source_id,
            "metadata_id": metadata_id,
            "experiment_id": experiment_id,
            "raw_result_id": raw_result_id,
            "converted_result_id": converted_result_id,
            "visualization_id": visualization_id
    }


def load(files, experiments, model_types):
    payload = [insert(files["emergency_area.hpp"], source_id=model_types["Emergency Area"]),
               insert(files["emergency.hpp"], source_id=model_types["Emergency Area"]),
               insert(files["emergency_area.json"], metadata_id=model_types["Emergency Area"]),
               insert(files["hospital.hpp"], source_id=model_types["Hospital"]),
               insert(files["emergency.hpp"], source_id=model_types["Hospital"]),
               insert(files["hospital.json"], metadata_id=model_types["Hospital"]),
               insert(files["emergency_area.hpp"], source_id=model_types["GIS Emergencies"]),
               insert(files["hospital.hpp"], source_id=model_types["GIS Emergencies"]),
               insert(files["emergency.hpp"], source_id=model_types["GIS Emergencies"]),
               insert(files["main.cpp"], source_id=model_types["GIS Emergencies"]),
               insert(files["makefile"], source_id=model_types["GIS Emergencies"]),
               insert(files["emergency_areas.geojson"], source_id=model_types["GIS Emergencies"]),
               insert(files["hospitals.geojson"], source_id=model_types["GIS Emergencies"]),
               insert(files["network.geojson"], source_id=model_types["GIS Emergencies"]),
               insert(files["simulation_area.geojson"], source_id=model_types["GIS Emergencies"]),
               insert(files["gis_emergencies.json"], metadata_id=model_types["GIS Emergencies"]),
               insert(files["emergency_areas.geojson"], visualization_id=experiments["Emergency 100-100-100"]),
               insert(files["hospitals.geojson"], visualization_id=experiments["Emergency 100-100-100"]),
               insert(files["network.geojson"], visualization_id=experiments["Emergency 100-100-100"]),
               insert(files["simulation_area.geojson"], visualization_id=experiments["Emergency 100-100-100"]),
               insert(files["output_messages.txt"], raw_result_id=experiments["Emergency 100-100-100"]),
               insert(files["state_messages.txt"], raw_result_id=experiments["Emergency 100-100-100"]),
               insert(files["messages.log"], converted_result_id=experiments["Emergency 100-100-100"]),
               insert(files["structure.json"], converted_result_id=experiments["Emergency 100-100-100"]),
               insert(files["gis_emergencies_experiment_1.json"], experiment_id=experiments["Emergency 100-100-100"]),
               insert(files["visualization.json"], visualization_id=experiments["Emergency 100-100-100"])]

    r = requests.post('http://localhost:8080/api/nn_files_v_all', json=payload)
    ids = json.loads(r.content)

    print("G_insert_into_nn_files_v_all.py -> status " + str(r.status_code))

    return ids

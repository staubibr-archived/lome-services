import requests

payload = [{
            "file_id": 1,                   # receiver.hpp
            "document_id": None,
            "source_id": 1,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 2,                   # sender.hpp
            "document_id": None,
            "source_id": 2,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 3,                   # subnet.hpp
            "document_id": None,
            "source_id": 3,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 4,                   # message.cpp
            "document_id": None,
            "source_id": 1,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 4,                   # message.cpp
            "document_id": None,
            "source_id": 2,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 4,                   # message.cpp
            "document_id": None,
            "source_id": 3,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 4,                   # message.cpp
            "document_id": None,
            "source_id": 4,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 4,                   # message.cpp
            "document_id": None,
            "source_id": 6,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 5,                   # message.hpp
            "document_id": None,
            "source_id": 1,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 5,                   # message.hpp
            "document_id": None,
            "source_id": 2,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 5,                   # message.hpp
            "document_id": None,
            "source_id": 3,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 5,                   # message.hpp
            "document_id": None,
            "source_id": 4,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 5,                   # message.hpp
            "document_id": None,
            "source_id": 6,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 6,                   # input_abp_0.txt
            "document_id": None,
            "source_id": 7,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 7,                   # input_abp_1.txt
            "document_id": None,
            "source_id": 7,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 8,                   # ABP_output_messages.txt
            "document_id": None,
            "source_id": None,
            "experiment_id": None,
            "raw_result_id": 1,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 9,                   # ABP_output_state.txt
            "document_id": None,
            "source_id": None,
            "experiment_id": None,
            "raw_result_id": 1,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 10,                  # messages.log
            "document_id": None,
            "source_id": None,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": 1,
            "visualization_id": None
           }, {
            "file_id": 11,                  # structure.json
            "document_id": None,
            "source_id": None,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": 1,
            "visualization_id": None
           }, {
            "file_id": 12,                  # main.cpp
            "document_id": None,
            "source_id": 7,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 13,                  # alternatebitprot.doc
            "document_id": 1,
            "source_id": None,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 14,                  # makefile
            "document_id": None,
            "source_id": 7,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 15,                  # README.txt
            "document_id": 1,
            "source_id": None,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 16,                  # hospital.hpp
            "document_id": None,
            "source_id": 9,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 17,                  # emergency_area.hpp
            "document_id": None,
            "source_id": 8,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 18,                  # emergency_areas.geojson
            "document_id": None,
            "source_id": 10,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": 3
           }, {
            "file_id": 19,                  # hospitals.geojson
            "document_id": None,
            "source_id": 10,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": 3
           }, {
            "file_id": 20,                  # network.geojson
            "document_id": None,
            "source_id": None,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": 3
           }, {
            "file_id": 21,                  # simulation_area.geojson
            "document_id": None,
            "source_id": None,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": 3
           }, {
            "file_id": 22,                  # emergency.hpp
            "document_id": None,
            "source_id": 9,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 22,                  # emergency.hpp
            "document_id": None,
            "source_id": 10,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 23,                  # output_messages.txt
            "document_id": None,
            "source_id": None,
            "experiment_id": None,
            "raw_result_id": 3,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 24,                  # state_messages.txt
            "document_id": None,
            "source_id": None,
            "experiment_id": None,
            "raw_result_id": 3,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 25,                  # messages.log
            "document_id": None,
            "source_id": None,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": 3,
            "visualization_id": None
           }, {
            "file_id": 26,                  # structure.json
            "document_id": None,
            "source_id": None,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": 3,
            "visualization_id": None
           }, {
            "file_id": 27,                  # main.cpp
            "document_id": None,
            "source_id": 10,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 28,                  # makefile
            "document_id": None,
            "source_id": 10,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 29,                  # abp_experiment_1.json
            "document_id": None,
            "source_id": None,
            "experiment_id": 1,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 30,                  # gis_emergencies_experiment_1.json
            "document_id": None,
            "source_id": None,
            "experiment_id": 3,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 31,                  # diagram.drawio
            "document_id": 1,
            "source_id": None,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": None
           }, {
            "file_id": 32,                  # diagram.svg
            "document_id": 1,
            "source_id": None,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": 1
           }, {
            "file_id": 33,                  # visualization.json
            "document_id": None,
            "source_id": None,
            "experiment_id": None,
            "raw_result_id": None,
            "converted_result_id": None,
            "visualization_id": 3
           }]

r = requests.post('http://localhost:8080/api/nn_files_v_all', json=payload)

print(r.status_code)

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
    payload = [insert(files["receiver.hpp"], source_id=model_types["receiver"]),
               insert(files["receiver.json"], metadata_id=model_types["receiver"]),
               insert(files["message.hpp"], source_id=model_types["receiver"]),
               insert(files["sender.hpp"], source_id=model_types["sender"]),
               insert(files["sender.json"], metadata_id=model_types["sender"]),
               insert(files["message.hpp"], source_id=model_types["sender"]),
               insert(files["subnet.hpp"], source_id=model_types["subnet"]),
               insert(files["subnet.json"], metadata_id=model_types["subnet"]),
               insert(files["message.hpp"], source_id=model_types["subnet"]),
               insert(files["network.json"], metadata_id=model_types["network"]),
               insert(files["input_reader.json"], metadata_id=model_types["input reader"]),
               insert(files["ABP.json"], metadata_id=model_types["ABP"]),
               insert(files["receiver.hpp"], source_id=model_types["ABP Top"]),
               insert(files["ABP Top.json"], metadata_id=model_types["ABP Top"]),
               insert(files["sender.hpp"], source_id=model_types["ABP Top"]),
               insert(files["subnet.hpp"], source_id=model_types["ABP Top"]),
               insert(files["message.hpp"], source_id=model_types["ABP Top"]),
               insert(files["main.cpp"], source_id=model_types["ABP Top"]),
               insert(files["makefile"], source_id=model_types["ABP Top"]),
               insert(files["input_abp_0.txt"], source_id=model_types["ABP Top"]),
               insert(files["input_abp_1.txt"], source_id=model_types["ABP Top"]),
               insert(files["output_messages.txt"], raw_result_id=experiments["1st experiment for Alternate Bit Protocol (ABP)"]),
               insert(files["state_messages.txt"], raw_result_id=experiments["1st experiment for Alternate Bit Protocol (ABP)"]),
               insert(files["messages.log"], converted_result_id=experiments["1st experiment for Alternate Bit Protocol (ABP)"]),
               insert(files["structure.json"], converted_result_id=experiments["1st experiment for Alternate Bit Protocol (ABP)"]),
               insert(files["alternatebitprot.doc"], document_id=experiments["1st experiment for Alternate Bit Protocol (ABP)"]),
               insert(files["README.txt"], document_id=experiments["1st experiment for Alternate Bit Protocol (ABP)"]),
               insert(files["abp_experiment_1.json"], document_id=experiments["1st experiment for Alternate Bit Protocol (ABP)"]),
               insert(files["diagram.drawio"], visualization_id=experiments["1st experiment for Alternate Bit Protocol (ABP)"]),
               insert(files["diagram.svg"], visualization_id=experiments["1st experiment for Alternate Bit Protocol (ABP)"])]

    r = requests.post('http://localhost:8080/api/nn_files_v_all', json=payload)
    ids = json.loads(r.content)

    print("G_insert_into_nn_files_v_all.py -> status " + str(r.status_code))

    return ids

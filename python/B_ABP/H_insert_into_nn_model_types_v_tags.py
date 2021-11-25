import requests
import json


def load(model_types, tags):
    payload = [{
            "model_type_id": model_types["receiver"],
            "tag_id": tags["Network"]
           }, {
            "model_type_id": model_types["receiver"],
            "tag_id": tags["Computer systems"]
           }, {
            "model_type_id": model_types["sender"],
            "tag_id": tags["Network"]
           }, {
            "model_type_id": model_types["sender"],
            "tag_id": tags["Computer systems"]
           }, {
            "model_type_id": model_types["subnet"],
            "tag_id": tags["Network"]
           }, {
            "model_type_id": model_types["subnet"],
            "tag_id": tags["Computer systems"]
           }, {
            "model_type_id": model_types["network"],
            "tag_id": tags["Network"]
           }, {
            "model_type_id": model_types["network"],
            "tag_id": tags["Computer systems"]
           }, {
            "model_type_id": model_types["ABP"],
            "tag_id": tags["Network"]
           }, {
            "model_type_id": model_types["ABP"],
            "tag_id": tags["Computer systems"]
           }, {
            "model_type_id": model_types["ABP"],
            "tag_id": tags["Simple"]
           }, {
            "model_type_id": model_types["ABP Top"],
            "tag_id": tags["Network"]
           }, {
            "model_type_id": model_types["ABP Top"],
            "tag_id": tags["Computer systems"]
           }, {
            "model_type_id": model_types["ABP Top"],
            "tag_id": tags["Simple"]
           }]

    r = requests.post('http://localhost:8080/api/nn_model_types_v_tags', json=payload)
    ids = json.loads(r.content)

    print("H_insert_into_nn_model_types_v_tags.py -> status " + str(r.status_code))

    return ids

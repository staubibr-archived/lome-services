import requests
import json


def load(model_types, tags):
    payload = [{
                "model_type_id": model_types["Emergency Area"],
                "tag_id": tags["Geospatial"]
               }, {
                "model_type_id": model_types["Emergency Area"],
                "tag_id": tags["Health"]
               }, {
                "model_type_id": model_types["Hospital"],
                "tag_id": tags["Geospatial"]
               }, {
                "model_type_id": model_types["Hospital"],
                "tag_id": tags["Health"]
               }, {
                "model_type_id": model_types["GIS Emergencies"],
                "tag_id": tags["Geospatial"]
               }, {
                "model_type_id": model_types["GIS Emergencies"],
                "tag_id": tags["Health"]
               }]

    r = requests.post('http://localhost:8080/api/nn_model_types_v_tags', json=payload)
    ids = json.loads(r.content)

    print("N_insert_into_nn_model_types_v_tags.py -> status " + str(r.status_code))

    return ids
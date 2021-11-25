import requests
import json


def load(contributors):
    payload = [{
                'project_name': 'GIS_Hospital',
                'name': 'Emergency 100-100-100',
                'description': '100% capacity, 100% population & 100% emergency rate',
                'author_id': 2,
                'date_created': '2021-07-08',
                'top_model_type_id': 10
               }, {
                'project_name': 'GIS_Hospital',
                'name': 'Emergency 90-110-100',
                'description': '90% capacity, 110% population & 100% emergency rate',
                'author_id': 2,
                'date_created': '2021-07-08',
                'top_model_type_id': 10
               }]

    r = requests.post('http://localhost:8080/api/experiments', json=payload)
    ids = json.loads(r.content)

    print("K_insert_into_experiments.py -> status " + str(r.status_code))

    return {payload[i]["name"]: ids[i] for i in range(0, len(payload))}

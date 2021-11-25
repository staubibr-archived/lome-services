import requests
import json


def load(contributors):
    payload = [{
                'project_name': 'ABP',
                'name': '1st experiment for Alternate Bit Protocol (ABP)',
                'description': '1st ABP Model Experiment',
                'author_id': contributors["Gabriel Wainer"],
                'date_created': '2010-01-01',
                'top_model_type_id': 7
               }, {
                'project_name': 'ABP',
                'name': 'Base 2',
                'description': '2nd experiment for Alternate Bit Protocol (ABP)',
                'author_id': contributors["Bruno St-Aubin"],
                'date_created': '2010-01-01',
                'top_model_type_id': 7
               }]

    r = requests.post('http://localhost:8080/api/experiments', json=payload)
    ids = json.loads(r.content)

    print("E_insert_into_experiments.py -> status " + str(r.status_code))

    return {payload[i]["name"]: ids[i] for i in range(0, len(payload))}

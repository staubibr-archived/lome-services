import requests
import json


def load():
    payload = [{
                'value': 'Network'
            }, {
                'value': 'Computer systems'
            }, {
                'value': 'Simple'
            }, {
                'value': 'Geospatial'
            }, {
                'value': 'Health'
            }]

    r = requests.post('http://localhost:8080/api/tags', json=payload)
    ids = json.loads(r.content)

    print("C_insert_into_tags.py -> status " + str(r.status_code))

    return {payload[i]["value"]: ids[i] for i in range(0, len(payload))}


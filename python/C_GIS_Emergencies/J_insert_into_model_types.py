import requests
import json


def load(contributors):
    payload = [{
                'name': 'Emergency Area',
                'type': 'Atomic',
                'formalism': 'GIS-DEVS',
                'simulator': 'Cadmium',
                'description': 'A geospatial area that emits a number of emergency based on population on a daily basis',
                'date_created': '2021-07-08',
                'author_id': 2
                }, {
                'name': 'Hospital',
                'type': 'Atomic',
                'formalism': 'GIS-DEVS',
                'simulator': 'Cadmium',
                'description': 'A hospital that receives emergencies, processes or rejects them if past capacity',
                'date_created': '2021-07-08',
                'author_id': 2
                }, {
                'name': 'GIS Emergencies',
                'type': 'Top',
                'formalism': 'GIS-DEVS',
                'simulator': 'Cadmium',
                'description': 'A spatial model that links emergency areas with hospitals',
                'date_created': '2021-07-08',
                'author_id': 2
            }]

    r = requests.post('http://localhost:8080/api/modeltypes', json=payload)
    ids = json.loads(r.content)

    print("J_insert_into_model_types.py -> status " + str(r.status_code))

    return {payload[i]["name"]: ids[i] for i in range(0, len(payload))}

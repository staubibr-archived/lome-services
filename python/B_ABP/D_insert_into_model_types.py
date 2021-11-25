import requests
import json


def load(contributors):
    payload = [{
                'name': 'receiver',
                'type': 'Atomic',
                'formalism': 'DEVS',
                'simulator': 'Cadmium',
                'description': 'A model that receives a packet of data and sends it back',
                'date_created': '2010-01-01',
                'author_id': contributors["Bruno St-Aubin"]
               }, {
                'name': 'sender',
                'type': 'Atomic',
                'formalism': 'DEVS',
                'simulator': 'Cadmium',
                'description': 'A model that periodically sends a packet of data',
                'date_created': '2010-01-01',
                'author_id': contributors["Bruno St-Aubin"]
               }, {
                'name': 'subnet',
                'type': 'Atomic',
                'formalism': 'DEVS',
                'simulator': 'Cadmium',
                'description': 'A subnet model through which packets of data travel',
                'date_created': '2010-01-01',
                'author_id': contributors["Hamza Qassoud"]
               }, {
                'name': 'network',
                'type': 'Coupled',
                'formalism': 'DEVS',
                'simulator': 'Cadmium',
                'description': 'A network coupled model composed of a number of subnets',
                'date_created': '2010-01-01',
                'author_id': contributors["Hamza Qassoud"]
               }, {
                'name': 'input reader',
                'type': 'Atomic',
                'formalism': 'DEVS',
                'simulator': 'Cadmium',
                'description': 'An input reader model that reads from a text file and outputs data line by line',
                'date_created': '2010-01-01',
                'author_id': contributors["Tao Zheng"]
               }, {
                'name': 'ABP',
                'type': 'Coupled',
                'formalism': 'DEVS',
                'simulator': 'Cadmium',
                'description': 'The full ABP network model',
                'date_created': '2010-01-01',
                'author_id': contributors["Tao Zheng"]
               }, {
                'name': 'ABP Top',
                'type': 'Top',
                'formalism': 'DEVS',
                'simulator': 'Cadmium',
                'description': 'A wrapper model that links the input reader with the ABP model',
                'date_created': '2010-01-01',
                'author_id': contributors["Prakhar Shukla"]
                }]

    r = requests.post('http://localhost:8080/api/modeltypes', json=payload)
    ids = json.loads(r.content)

    print("D_insert_into_model_types.py -> status " + str(r.status_code))

    return {payload[i]["name"]: ids[i] for i in range(0, len(payload))}

import requests

payload = [{
            'name': 'receiver',
            'type': 'Atomic',
            'formalism': 'DEVS',
            'simulator': 'Cadmium',
            'description': 'A model that receives a packet of data and sends it back',
            'date_created': '2010-01-01',
            'author_id': 1
           }, {
            'name': 'sender',
            'type': 'Atomic',
            'formalism': 'DEVS',
            'simulator': 'Cadmium',
            'description': 'A model that periodically sends a packet of data',
            'date_created': '2010-01-01',
            'author_id': 1
           }, {
            'name': 'subnet',
            'type': 'Atomic',
            'formalism': 'DEVS',
            'simulator': 'Cadmium',
            'description': 'A subnet model through which packets of data travel',
            'date_created': '2010-01-01',
            'author_id': 1
           }, {
            'name': 'network',
            'type': 'Coupled',
            'formalism': 'DEVS',
            'simulator': 'Cadmium',
            'description': 'A network coupled model composed of a number of subnets',
            'date_created': '2010-01-01',
            'author_id': 1
            }, {
            'name': 'input reader',
            'type': 'Atomic',
            'formalism': 'DEVS',
            'simulator': 'Cadmium',
            'description': 'An input reader model that reads from a text file and outputs data line by line',
            'date_created': '2010-01-01',
            'author_id': 1
            }, {
            'name': 'ABP',
            'type': 'Coupled',
            'formalism': 'DEVS',
            'simulator': 'Cadmium',
            'description': 'The full ABP network model',
            'date_created': '2010-01-01',
            'author_id': 1
            }, {
            'name': 'ABP Top',
            'type': 'Top',
            'formalism': 'DEVS',
            'simulator': 'Cadmium',
            'description': 'A wrapper model that links the input reader with the ABP model',
            'date_created': '2010-01-01',
            'author_id': 1
            }, {
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

print(r.status_code)

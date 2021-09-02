import requests

payload = [{
            'name': 'receiver.json',
            'file_type_id': 4,
            'last_modifications': '2021-08-21',
            'last_author': 4,
            'path': 'Path To Receiver File',
            'model_type_id': 1
           }, {
            'name': 'receiver_exp.json',
            'file_type_id': 4,
            'last_modifications': '2021-08-21',
            'last_author': 4,
            'path': 'Path To Receiver Experiment File',
            'experiment_id': 1
           }, {
            'name': 'sender.json',
            'file_type_id': 4,
            'last_modifications': '2021-08-21',
            'last_author': 4,
            'path': 'Path To Sender File',
            'model_type_id': 2
           }, {
            'name': 'subnet.json',
            'file_type_id': 4,
            'last_modifications': '2021-08-21',
            'last_author': 4,
            'path': 'Path To Subnet File',
            'model_type_id': 3
           }]

r = requests.post('http://localhost:8080/api/files', json=payload)

print(r.status_code)

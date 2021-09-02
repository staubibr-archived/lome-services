import requests

payload = [{
            'project_name': 'ABP',
            'name': '1st experiment for Alternate Bit Protocol (ABP) ',
            'description': '1st ABP Model Experiment',
            'author': 1,
            'date_created': '2010-01-01',
            'top_model_type': 7
           }, {
            'project_name': 'ABP',
            'name': 'Base 2',
            'description': '2nd experiment for Alternate Bit Protocol (ABP) ',
            'author': 1,
            'date_created': '2010-01-01',
            'top_model_type': 7
           }, {
            'project_name': 'GIS_Hospital',
            'name': 'Normal',
            'description': 'Normal capacity, population & emergency rate',
            'author': 2,
            'date_created': '2021-07-08',
            'top_model_type': 10
           }, {
            'project_name': 'GIS_Hospital',
            'name': 'Normal',
            'description': '90% capacity, 110% population & normal emergency rate',
            'author': 2,
            'date_created': '2021-07-08',
            'top_model_type': 10
           }]

r = requests.post('http://localhost:8080/api/experiments', json=payload)

print(r.status_code)

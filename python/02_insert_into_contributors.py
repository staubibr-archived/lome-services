import requests

payload = [{
            'first_name': 'Gabriel',
            'last_name': 'Wainer',
            'middle_name': None,
            'email': 'gwainer@sce.carleton.ca',
            'affiliation': 'Carleton',
            'creation_date': '2021-08-18'
           }, {
            'first_name': 'Bruno',
            'last_name': 'Aubin',
            'middle_name': 'St',
            'email': 'staubin.bruno@gmail.com',
            'affiliation': 'Carleton',
            'creation_date': '2021-08-05'
           }, {
            'first_name': 'Hamza',
            'last_name': 'Qassoud',
            'middle_name': None,
            'email': 'hamza.qassoud@gmail.com',
            'affiliation': 'Carleton',
            'creation_date': '2021-08-18'
           }, {
            'first_name': 'Tao ',
            'last_name': 'Zheng',
            'middle_name': None,
            'email': 'taozhenggmail.com',
            'affiliation': 'Carleton',
            'creation_date': '2021-08-18'
           }, {
            'first_name': 'Prakhar',
            'last_name': 'Shukla',
            'middle_name': None,
            'email': 'prakhar3109@gmail.com',
            'affiliation': 'SRM IST',
            'creation_date': '2021-08-18'
           }]

r = requests.post('http://localhost:8080/api/contributors', json=payload)

print(r.status_code)
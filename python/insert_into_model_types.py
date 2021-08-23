import requests

payload={'id' : 1,
        'name' : 'receiver', 
        'type' : 'Atomic',  
        'formalism' : 'DEVS', 
        'simulator' : 'Cadmium' ,
        'description' : 'Receiver Model Type', 
        'date_created' : '2021-08-10',
        'author' : 4}

r= requests.post('http://localhost:8080/api/modeltypes', json=payload)

print(r.status_code)

payload={'id' : 2,
        'name' : 'sender', 
        'type' : 'Atomic',  
        'formalism' : 'DEVS', 
        'simulator' : 'Cadmium' ,
        'description' : 'Sender Model Type', 
        'date_created' : '2021-08-10',
        'author' : 4}

r= requests.post('http://localhost:8080/api/modeltypes', json=payload)

print(r.status_code)

payload={'id' : 3,
        'name' : 'subnet', 
        'type' : 'Atomic',  
        'formalism' : 'DEVS', 
        'simulator' : 'Cadmium' ,
        'description' : 'Subnet Model Type', 
        'date_created' : '2021-08-10',
        'author' : 4}

r= requests.post('http://localhost:8080/api/modeltypes', json=payload)

print(r.status_code)

payload={'id' : 4,
        'name' : 'main', 
        'type' : 'Coupled',  
        'formalism' : 'DEVS', 
        'simulator' : 'Cadmium' ,
        'description' : 'Coupled Model Type', 
        'date_created' : '2021-08-10',
        'author' : 4}

r= requests.post('http://localhost:8080/api/modeltypes', json=payload)

print(r.status_code)
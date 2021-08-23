import requests

payload={
    'id' : 1,
    'project_name' : 'ABP Project',
    'name' : 'Rate 10',
    'description' : 'ABP model with input rate of 10 packets per minute',
    'author' : 1,
    'date_created' : '2021-08-21',
    'top_model_type' : 4
}

r= requests.post('http://localhost:8080/api/experiments', json=payload)

print(r.status_code)


payload={
    'id' : 2,
    'project_name' : 'ABP Project',
    'name' : 'Rate 20',
    'description' : 'ABP model with input rate of 20 packets per minute',
    'author' : 1,
    'date_created' : '2021-08-21',
    'top_model_type' : 4
}

r= requests.post('http://localhost:8080/api/experiments', json=payload)

print(r.status_code)

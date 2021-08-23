import requests

payload ={
    'id' : 1,
    'value' : 'Health'
}

r= requests.post('http://localhost:8080/api/tags', json=payload)

print(r.status_code)

payload ={
    'id' : 2,
    'value' : 'Environment'
}

r= requests.post('http://localhost:8080/api/tags', json=payload)

print(r.status_code)

payload ={
    'id' : 3,
    'value' : 'Data'
}

r= requests.post('http://localhost:8080/api/tags', json=payload)

print(r.status_code)

payload ={
    'id' : 4,
    'value' : 'Climate Change'
}

r= requests.post('http://localhost:8080/api/tags', json=payload)

print(r.status_code)

payload ={
    'id' : 5,
    'value' : 'Networks'
}

r= requests.post('http://localhost:8080/api/tags', json=payload)

print(r.status_code)
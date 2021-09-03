import requests

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

r = requests.post('http://localhost:8080/api/tables/tags', json=payload)

print(r.status_code)

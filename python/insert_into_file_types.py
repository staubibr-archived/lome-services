import requests

payload={'id' : 1,
        'description' : 'A Header file', 
        'extension' : '.hpp',
        }  
r= requests.post('http://localhost:8080/api/fileTypes', json=payload)

print(r.status_code)

payload={'id' : 2,
        'description' : 'A C++ source code file', 
        'extension' : '.cpp',
        }  
r= requests.post('http://localhost:8080/api/fileTypes', json=payload)

print(r.status_code)

payload={'id' : 3,
        'description' : 'A text file', 
        'extension' : '.txt',
        }  
r= requests.post('http://localhost:8080/api/fileTypes', json=payload)

print(r.status_code)

payload={'id' : 4,
        'description' : 'A JSON file', 
        'extension' : '.json',
        }  
r= requests.post('http://localhost:8080/api/fileTypes', json=payload)

print(r.status_code)

payload={'id' : 5,
        'description' : 'A Word Document', 
        'extension' : '.doc',
        }  
r= requests.post('http://localhost:8080/api/fileTypes', json=payload)

print(r.status_code)
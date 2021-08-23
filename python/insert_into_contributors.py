import requests

payload={'id' : 1,
        'first_name' : 'Gabriel', 
        'last_name' : 'Wainer',  
        'email' : 'gwainer@sce.carleton.ca', 
        'affiliation' : 'Carleton' , 
        'creation_date' : '2021-08-18'}

r= requests.post('http://localhost:8080/api/contributors', json=payload)

print(r.status_code)

payload={'id' : 2,
        'first_name' : 'Bruno', 
        'last_name' : 'Aubin', 
        'middle_name' : 'St',  
        'email' : 'staubin.bruno@gmail.com', 
        'affiliation' : 'Carleton' , 
        'creation_date' : '2021-08-05'}


r= requests.post('http://localhost:8080/api/contributors', json=payload)

print(r.status_code)

payload={'id' : 3,
         'first_name' : 'Hamza', 
         'last_name' : 'Qassoud',  
         'email' : 'hamza.qassoud@gmail.com ', 
         'affiliation' : 'Carleton' , 
         'creation_date' : '2021-08-18'}

r= requests.post('http://localhost:8080/api/contributors', json=payload)

print(r.status_code)

payload={'id' : 4,
         'first_name' : 'Tao ', 
         'last_name' : 'Zheng',  
         'email' : 'taozhenggmail.com', 
         'affiliation' : 'Carleton' , 
         'creation_date' : '2021-08-18'}

r= requests.post('http://localhost:8080/api/contributors', json=payload)

print(r.status_code)

payload={'id' : 5,
         'first_name' : 'Prakhar', 
         'last_name' : 'Shukla',  
         'email' : 'prakhar3109@gmail.com', 
         'affiliation' : 'SRM IST' , 
         'creation_date' : '2021-08-18'}

r= requests.post('http://localhost:8080/api/contributors', json=payload)

print(r.status_code)
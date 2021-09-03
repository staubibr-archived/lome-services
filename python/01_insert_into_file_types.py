import requests

payload = [{
           'description': 'Atomic model header file (C++)',
           'extension': '.hpp',
           }, {
           'description': 'Coupled model source code file (C++)',
           'extension': '.cpp',
           }, {
           'description': 'Top model source code file (C++)',
           'extension': '.cpp',
           }, {
           'description': 'Output message data structure header file (C++)',
           'extension': '.hpp',
           }, {
           'description': 'Output message data structure source code file (C++)',
           'extension': '.cpp',
           }, {
           'description': 'makefile to compile a model',
           'extension': '',
           }, {
           'description': 'Spatial data file for visualization and automated modeling workflows.',
           'extension': '.geojson',
           }, {
           'description': 'Model input data file',
           'extension': '.txt',
           }, {
           'description': 'Raw state messages results file',
           'extension': '.txt',
           }, {
           'description': 'Raw output messages results file',
           'extension': '.txt',
           }, {
           'description': 'Simulation structure file for the web viewer',
           'extension': '.json',
           }, {
           'description': 'Simulation results file for the web viewer',
           'extension': '.log',
           }, {
           'description': 'Visualization configuration file for the web viewer',
           'extension': '.json',
           }, {
           'description': 'Style file for the web viewer',
           'extension': '.json',
           }, {
           'description': 'Diagram file for the web viewer',
           'extension': '.svg',
           }, {
           'description': 'Experiment configuration file',
           'extension': '.json',
           }, {
           'description': 'Other file type',
           'extension': '.*',
           }]

r = requests.post('http://localhost:8080/api/fileTypes', json=payload)

print(r.status_code)

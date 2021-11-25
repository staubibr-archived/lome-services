import requests
import json


def load(authors):
    payload = [{
                'name': 'hospital.hpp',
                'file_type_id': 1,
                'last_modification': '2021-07-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'atomics'
               }, {
                'name': 'emergency_area.hpp',
                'file_type_id': 1,
                'last_modification': '2021-07-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'atomics'
               }, {
                'name': 'emergency_areas.geojson',
                'file_type_id': 7,
                'last_modification': '2021-07-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'data/Ottawa-Gatineau'
               }, {
                'name': 'hospitals.geojson',
                'file_type_id': 7,
                'last_modification': '2021-07-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'data/Ottawa-Gatineau'
               }, {
                'name': 'network.geojson',
                'file_type_id': 7,
                'last_modification': '2021-07-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'data/Ottawa-Gatineau'
               }, {
                'name': 'simulation_area.geojson',
                'file_type_id': 7,
                'last_modification': '2021-07-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'data/Ottawa-Gatineau'
               }, {
                'name': 'emergency.hpp',
                'file_type_id': 4,
                'last_modification': '2021-07-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'data_structures'
               }, {
                'name': 'output_messages.txt',
                'file_type_id': 10,
                'last_modification': '2021-07-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'simulation_results'
               }, {
                'name': 'state_messages.txt',
                'file_type_id': 9,
                'last_modification': '2021-07-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'simulation_results'
               }, {
                'name': 'messages.log',
                'file_type_id': 12,
                'last_modification': '2021-07-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'simulation_results'
               }, {
                'name': 'structure.json',
                'file_type_id': 11,
                'last_modification': '2021-07-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'simulation_results'
               }, {
                'name': 'main.cpp',
                'file_type_id': 3,
                'last_modification': '2021-07-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'top_model'
               }, {
                'name': 'makefile',
                'file_type_id': 6,
                'last_modification': '2021-07-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': ''
               }, {
                'name': 'gis_emergencies_experiment_1.json',
                'file_type_id': 16,
                'last_modification': '2021-07-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'experiments'
               }, {
                'name': 'visualization.json',
                'file_type_id': 13,
                'last_modification': '2021-07-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'visualization'
               }, {
                'name': 'emergency_area.json',
                'file_type_id': 17,
                'last_modification': '2021-09-28',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'metadata'
               }, {
                'name': 'hospital.json',
                'file_type_id': 17,
                'last_modification': '2021-09-28',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'metadata'
               }, {
                'name': 'gis_emergencies.json',
                'file_type_id': 17,
                'last_modification': '2021-09-28',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'metadata'
               }]

    r = requests.post('http://localhost:8080/api/files', json=payload)
    ids = json.loads(r.content)

    print("L_insert_into_files.py -> status " + str(r.status_code))

    return {payload[i]["name"]: ids[i] for i in range(0, len(payload))}

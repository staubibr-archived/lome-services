import requests
import json


def load(authors):
    payload = [{
                'name': 'receiver.hpp',
                'file_type_id': 1,
                'last_modification': '2010-01-01',
                'last_author_id': authors["Tao Zheng"],
                'path': 'atomics'
               }, {
                'name': 'sender.hpp',
                'file_type_id': 1,
                'last_modification': '2010-01-01',
                'last_author_id': authors["Tao Zheng"],
                'path': 'atomics'
               }, {
                'name': 'subnet.hpp',
                'file_type_id': 1,
                'last_modification': '2010-01-01',
                'last_author_id': authors["Tao Zheng"],
                'path': 'atomics'
               }, {
                'name': 'message.hpp',
                'file_type_id': 4,
                'last_modification': '2010-01-01',
                'last_author_id': authors["Tao Zheng"],
                'path': 'data_structures'
               }, {
                'name': 'input_abp_0.txt',
                'file_type_id': 8,
                'last_modification': '2010-01-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'input_data'
               }, {
                'name': 'input_abp_1.txt',
                'file_type_id': 8,
                'last_modification': '2010-01-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'input_data'
               }, {
                'name': 'output_messages.txt',
                'file_type_id': 10,
                'last_modification': '2010-01-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'simulation_results'
               }, {
                'name': 'state_messages.txt',
                'file_type_id': 9,
                'last_modification': '2010-01-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'simulation_results'
               }, {
                'name': 'messages.log',
                'file_type_id': 12,
                'last_modification': '2010-01-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'simulation_results'
               }, {
                'name': 'structure.json',
                'file_type_id': 11,
                'last_modification': '2010-01-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'simulation_results'
               }, {
                'name': 'main.cpp',
                'file_type_id': 3,
                'last_modification': '2010-01-01',
                'last_author_id': authors["Tao Zheng"],
                'path': 'top_model'
               }, {
                'name': 'alternatebitprot.doc',
                'file_type_id': 18,
                'last_modification': '2010-01-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': ''
               }, {
                'name': 'makefile',
                'file_type_id': 6,
                'last_modification': '2010-01-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': ''
               }, {
                'name': 'README.txt',
                'file_type_id': 18,
                'last_modification': '2010-01-01',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': ''
               }, {
                'name': 'abp_experiment_1.json',
                'file_type_id': 16,
                'last_modification': '2010-01-01',
                'last_author_id': authors["Tao Zheng"],
                'path': 'experiments'
               }, {
                'name': 'diagram.drawio',
                'file_type_id': 18,
                'last_modification': '2021-03-04',
                'last_author_id': authors["Tao Zheng"],
                'path': 'visualization'
               }, {
                'name': 'diagram.svg',
                'file_type_id': 15,
                'last_modification': '2021-03-04',
                'last_author_id': authors["Tao Zheng"],
                'path': 'visualization'
               }, {
                'name': 'receiver.json',
                'file_type_id': 17,
                'last_modification': '2021-09-28',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'metadata'
               }, {
                'name': 'sender.json',
                'file_type_id': 17,
                'last_modification': '2021-09-28',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'metadata'
               }, {
                'name': 'subnet.json',
                'file_type_id': 17,
                'last_modification': '2021-09-28',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'metadata'
               }, {
                'name': 'network.json',
                'file_type_id': 17,
                'last_modification': '2021-09-28',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'metadata'
               }, {
                'name': 'input_reader.json',
                'file_type_id': 17,
                'last_modification': '2021-09-28',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'metadata'
               }, {
                'name': 'ABP.json',
                'file_type_id': 17,
                'last_modification': '2021-09-28',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'metadata'
               }, {
                'name': 'ABP Top.json',
                'file_type_id': 17,
                'last_modification': '2021-09-28',
                'last_author_id': authors["Bruno St-Aubin"],
                'path': 'metadata'
               }]

    r = requests.post('http://localhost:8080/api/files', json=payload)
    ids = json.loads(r.content)

    print("F_insert_into_files.py -> status " + str(r.status_code))

    return {payload[i]["name"]: ids[i] for i in range(0, len(payload))}

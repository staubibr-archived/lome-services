import requests

payload = [{
            "model_type_id": 1,  # receiver
            "tag_id": 1
           }, {
            "model_type_id": 1,  # receiver
            "tag_id": 2
           }, {
            "model_type_id": 2,  # sender
            "tag_id": 1
           }, {
            "model_type_id": 2,  # sender
            "tag_id": 2
           }, {
            "model_type_id": 3,  # subnet
            "tag_id": 1
           }, {
            "model_type_id": 3,  # subnet
            "tag_id": 2
           }, {
            "model_type_id": 4,  # network
            "tag_id": 1
           }, {
            "model_type_id": 4,  # network
            "tag_id": 2
           }, {
            "model_type_id": 6,  # ABP
            "tag_id": 1
           }, {
            "model_type_id": 6,  # ABP
            "tag_id": 2
           }, {
            "model_type_id": 6,  # ABP
            "tag_id": 3
           }, {
            "model_type_id": 7,  # ABP Top
            "tag_id": 1
           }, {
            "model_type_id": 7,  # ABP Top
            "tag_id": 2
           }, {
            "model_type_id": 7,  # ABP Top
            "tag_id": 3
           }, {
            "model_type_id": 8,  # Emergency Area
            "tag_id": 4
           }, {
            "model_type_id": 8,  # Emergency Area
            "tag_id": 5
           }, {
            "model_type_id": 9,  # Hospital
            "tag_id": 4
           }, {
            "model_type_id": 9,  # Hospital
            "tag_id": 5
           }, {
            "model_type_id": 10,  # GIS Emergencies
            "tag_id": 4
           }, {
            "model_type_id": 10,  # GIS Emergencies
            "tag_id": 5
           }]

r = requests.post('http://localhost:8080/api/tables/nn_model_types_v_tags', json=payload)

print(r.status_code)

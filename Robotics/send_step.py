import json
import requests

data = {
    "instruction"         : "Add salt to pot",
    "destinationPosition" : 0,
    "ingredient"          : "salt",
    "amount"              : 5,
    "unit"                : "grams",
    "ifActionable"        : True
}

data_json = json.dumps(data, indent=4)
r = requests.post('http://127.0.0.1:5000/post_json', data=data_json)
print(r)


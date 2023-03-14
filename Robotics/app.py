# app.py
# Runs Flask server and forwards request to handler

from flask import Flask, jsonify, json, request
from flask_cors import CORS, cross_origin
from handler import *
import socket

app = Flask(__name__)
cors = CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'

@app.route("/")
def hello_world():
    return 'hello world'

@app.route("/post_json", methods=['POST'])
@cross_origin()
def process_json():
    data = json.loads(request.data)
    request_handler(data)
    data_json = json.dumps(data, indent=4)
    #print(data_json) #Prints JSON body
    return '200'

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5000)

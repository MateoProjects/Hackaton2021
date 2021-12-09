from flask import Flask, request
from dbManager import *

app = Flask(__name__)

@app.route('/')
def index():
    return "This is project developed for help patients with ALS"


@app.route('/post', methods=['POST', 'GET'])
def post():
    if request.method == "POST":
        print(request.form['fullName'])
        return "This is a post"

    elif request.method == "GET":
        return "This is a get"


@app.route('/post/user', methods=['POST'])
def postUser():
    if request.method == "POST":
        values = (request.form["fullName"], request.form["name"], request.form["lastName"], request.form["age"])
        if not checkUser(request.form["fullName"]):
            createUser(values)
            return "User Created"
        else:
            return "User exist"

@app.route('/post/eat', methods=['POST'])
def addEat():
    if request.method == "POST":
        values = (request.form[""])
    return False

if __name__ == '__main__':
    app.run(debug=True)

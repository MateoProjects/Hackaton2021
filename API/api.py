
from flask import Flask, request
app = Flask(__name__)
from dbmanager import *

def listToDictionary(list):
    dictionary = {}
    for i in range(len(list)):
        dictionary[i] = list[i]

    return dictionary

def getElements(dict):
    elements = []
    for key in dict.keys():
        elements.append(dict[key])
    return elements


@app.route('/')
def index():
    return "HELLO WORLD"

@app.route('/createUser', methods=['POST'])
def insertUser():
    args = request.get_json()
    args = getElements(args)
    createUser(args)
    return "User created"

@app.route('/deleteUser', methods=['POST'])
def deleteUser():
    args = request.get_json()
    print(args)
    
    fullName = args['FullName']
    deleteUserDb(fullName)
    return "User deleted"

@app.route('/addSentiment', methods=['POST'])
def addSentiment():
    args = request.get_json()
    addSentimentDb(args['FullName'], args['Text'], 0.0, "")
    return "Sentiment Added"

@app.route('/deleteSentiment', methods=['POST'])
def deleteSentiment():
    args = request.get_data()
    print(args)
    return "Sentiment deleted!"

@app.route('/getSentiments', methods=['GET'])
def getSentiments():
    args = request.get_json()
    values = getSentimentsForUser(args['psychologist'], args['fullName'])
    values = listToDictionary(tuple(values))
    print(values)
    return values
     


if __name__ == '__main__':
    app.run(debug=True)

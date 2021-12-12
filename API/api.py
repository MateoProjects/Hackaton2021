from flask import Flask, request
from dbmanager import *
import sys
sys.path.append('..\\NLP')
from NLP.model_interface import *

app = Flask(__name__)

def listToDictionary(list):
    """
    Transform list to dictionary
    @param list: list of elements
    @return: dictionary
    """
    dictionary = {}
    for i in range(len(list)):
        dictionary[i] = list[i]

    return dictionary

def getElements(dict):
    """
    Get elements from dictionary
    @param dict: dictionary
    @return: list of elements
    """
    elements = []
    for key in dict.keys():
        elements.append(dict[key])
    return elements


@app.route('/')
def index():
    """
    Return index page
    """
    return "HELLO WORLD"

@app.route('/createUser', methods=['POST'])
def insertUser():
    """
    Insert User on DB that receive from client POST request
    Example of post Parameters: {"Name": "Ramon", "LastName":"Mateo","FullName": "RamonMateo", 
                "age":25, "psw":123, "psychologist":"Marta"}

    """
    args = request.get_json()
    args = getElements(args)
    createUser(args)
    return "User created"

@app.route('/deleteUser', methods=['POST'])
def deleteUser():
    """
    Delete User on DB that receive from client POST request
    Example of post Parameters: {"FullName": "RamonMateo"}
    """
    args = request.get_json()
    args = args['FullName']
    deleteUserDb(args)
    return "User deleted"
   

@app.route('/addSentiment', methods=['POST'])
def addSentiment():
    """
    Add Sentiment on DB that receive from client POST request
    Example of post Parameters: {"Text": text, "FullName": fullName}
    """
    args = request.get_json()
    # TODO: Use model NLTK
    prediction = predict(args['Text'])
    print(prediction)
    addSentimentDb(args['FullName'], args['Text'], prediction, "")
    return "Sentiment Added"

@app.route('/getSentiments', methods=['GET'])
def getSentiments():
    """
    Get Sentiments from DB that receive from client GET request
    Example of get Parameters: {"FullName": fullName}
    """
    args = request.get_json()
    values = getSentimentsForUser(args['psychologist'], args['fullName'])
    values = listToDictionary(tuple(values))
    print(values)
    return values
     


if __name__ == '__main__':
    app.run(debug=True)

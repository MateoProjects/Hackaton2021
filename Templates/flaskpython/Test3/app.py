from flask import Flask, request

app = Flask(__name__)


@app.route("/")
def index():
    return "Hello world"


@app.route("/post", methods=['GET', 'POST'])
def post():
    if request.method == "POST":
        return "IS POST"

    elif request.method == "GET":
        return "IS GET"
    return "Hello"


@app.route('/hello')
def hello():
    return 'Hello, World'


if __name__ == "__main__":
    app.run()

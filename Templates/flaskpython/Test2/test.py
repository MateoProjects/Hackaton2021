from flask import Flask, redirect, url_for
app = Flask(__name__)

posts = []
@app.route("/")
def index():
    return "{} posts".format(len(posts))

@app.route("/p/<string:slug>/")
def show_post(slug):
    return "Mostrando el post {}".format(slug)

@app.route("/admin/post/")
@app.route("/admin/post/<int:post_id>/")
def post_form(post_id=None):
    return "post_form {}".format(post_id)


#Tot parametra que posi <> sera agafat per la url i passat a la funció

@app.route("/<string:fullname>")
def get_nameUser(fullname):
    return fullname



@app.route("/admin")
def admin():
    # redirigeix a la pàgina indicada en url_for en aquest cas cap a la funcio index
    return redirect(url_for("index"))

if __name__ == '__main__':
    app.run()


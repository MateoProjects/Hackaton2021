import sqlite3

PATH = 'C:\\Users\\fraud\\Desktop\\db\\mydb.db'

def createUser(user)-> None:
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    cur.execute('INSERT INTO users (FullName, Name, LastName, Age) '
                'VALUES (?, ?, ?, ?)', user)
    con.commit()
    con.close()

def checkUser(user) -> bool:
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    cur.execute('SELECT FullName FROM users where FullName = ?', [user])
    results = con.commit()
    if not cur.fetchall():
        con.close()
        return False
    con.close()
    return True

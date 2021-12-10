import sqlite3

PATH = 'BD\db\hackatonDB.db'


def createUser(user)-> None:
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    if not checkUser(user[2]):
        cur.execute('INSERT INTO Users (Name, LastName, FullName, Age, psw) '
                'VALUES (?, ?, ?, ?, ?)', user)
        con.commit()
        con.close()
        createTableSentiment(user[2])


def checkUser(user) -> bool:
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    cur.execute('SELECT Name FROM Users where FullName = ?', [user])
    con.commit()
    if not cur.fetchall():
        con.close()
        return False
    con.close()
    return True


def getUser(user) -> list:
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    cur.execute('SELECT * FROM Users where FullName = ?', user)
    con.commit()
    user = cur.fetchall()
    con.close()
    return user

def checkPassword(user, password) -> bool:
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    cur.execute('SELECT psw FROM Users where FullName = ?', user)
    con.commit()
    if not cur.fetchall():
        con.close()
        return False
    con.close()
    return True

def createTableSentiment(fullname):
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    cur.execute('CREATE TABLE IF NOT EXISTS ' + fullname +
                '_sentiment (id INTEGER PRIMARY KEY AUTOINCREMENT, '
                'tweet TEXT, sentiment REAL , label TEXT)')
    con.commit()
    con.close()
    print("Table created")

if __name__ == '__main__':
    createUser(['Juan', 'Perez', 'JuanPerez', '20', '123'])


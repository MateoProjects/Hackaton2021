import sqlite3
import logging

PATH = 'BD\db\hackatonDB.db'


def createUser(user)-> None:
    """
    Create a new user
    @param user: List with user information
    @action: create a new user in the database and create a table for the sentiment

    """
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    if not checkUser(user[2]):
        cur.execute('INSERT INTO Users (Name, LastName, FullName, Age, psw, psychologist) '
                'VALUES (?, ?, ?, ?, ?, ?)', user)
        con.commit()
        con.close()
        createTableSentiment(user[2])
        logging.info("User created")
    return 0

def checkUser(user) -> bool:
    """
    Check if the user exists
    @param user: user fullname
    @return: True if the user exists, False otherwise
    """
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
    """
    Get user information
    @param user: user fullname
    @return: list with user information
    """
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    cur.execute('SELECT * FROM Users where FullName = ?', user)
    con.commit()
    user = cur.fetchall()
    con.close()
    return user

def checkPassword(user, password) -> bool:
    """
    Check if the password is correct
    @param user: user fullname
    @param password: password to be checked
    @return: True if the password is correct, False otherwise
    """
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
    """
    Create a table for the sentiment of the user
    @param fullname: user fullname
    @action: create table if this not exists
    """
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    cur.execute('CREATE TABLE IF NOT EXISTS ' + fullname +
                '_sentiment (id INTEGER PRIMARY KEY AUTOINCREMENT, '
                'text TEXT, sentiment REAL , label TEXT)')
    con.commit()
    con.close()
    logging.info("Table created")

def addSentimentDb(fullname, text, sentiment, label):
    """
    Add sentiment to the database
    @param fullname: user fullname
    @param text: text to be analyzed
    @param sentiment: sentiment value
    @param label: label of the sentiment
    """
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    cur.execute('INSERT INTO ' + fullname + '_sentiment (text, sentiment, label) VALUES (?, ?, ?)', [text, sentiment, label])
    con.commit()
    con.close()
    logging.info("Sentiment added")
    return 0


def deleteUserDb(fullname):
    """
    Delete user from the database
    @param fullname: user fullname
    @action: delete user from the database and delete the user sentiment table
    """
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    cur.execute('DELETE FROM Users WHERE FullName = ?', [fullname])
    cur.execute('DROP TABLE IF EXISTS ' + fullname + '_sentiment')
    con.commit()
    con.close()
    logging.info("User deleted")
    return 0

def restartDB():
    """
    Delete all users from the database and their respective sentiment tables
    """
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    cur.execute('SELECT * from Users')
    users = cur.fetchall()
    for user in users:
        deleteUserDb(user[len(user)-1])
    con.commit()
    con.close()
    print("Database Restarted")

def getSentimentsForUser(psychologist, user):
    """
    Get the sentiment for the user
    @param psychologist: psychologist fullname
    @param user: user fullname
    @return: list with the sentiment
    """
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    cur.execute('SELECT psychologist FROM Users' +' where FullName = ?', [user])
    name = cur.fetchall()
    print(name[0])
    if psychologist in name[0]:
        print('im here')
        cur.execute('SELECT * FROM ' + user + '_sentiment')
        con.commit()
        sentiments = cur.fetchall()
        con.close()
        return sentiments
    else:
        con.close()
        print("User not admited")
        return None  

def insertPsychology(fullname, psychologist):
    """
    Insert psychologist to the database
    @param fullname: user fullname
    @param psychologist: psychologist fullname
    @action: insert psychologist to the database
    """
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    cur.execute('UPDATE Users SET psychologist = ? WHERE FullName = ?', [psychologist, fullname])
    con.commit()
    con.close()
    logging.info("Psychologist added")

def getPsychologists():
    """
    Get all psychologists
    @return: list with all psychologists
    """
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    cur.execute('SELECT psychologist FROM Users')
    psychologists = cur.fetchall()
    con.commit()
    con.close()
    return psychologists



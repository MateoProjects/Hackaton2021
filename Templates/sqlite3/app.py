import sqlite3


# cur.execute('INSERT INTO users (FullName, Name, LastName, Age)
# VALUES ("RamonMateoNavarro", "Ramon", "Mateo", 26)')

PATH = 'C:\\Users\\fraud\\Desktop\\db\\mydb.db'

def insertFiles(values):
    con = sqlite3.connect(PATH)
    cur = con.cursor()
    cur.execute('INSERT INTO dataAudio (NameFile, PathFile, DateFile, NumSentence, FullName) '
                'VALUES (?, ?, ?, ?, ?)', values)

    con.commit()
    con.close()


if __name__ == '__main__':
    insertFiles(("Test1", "C:\\Users\\fraud\\Desktop\\csv.pdf", "01/01/98 23:59:59.999", 3,
                 "RamonMateoNavarro"))

import PySimpleGUI as sg
import os
import json
import requests

def getFullName():
    #get full name from register.txt
    with open('register.txt', 'r') as f:
        data = json.load(f)
        return data['FullName']

# STEP 1 define the layout
layout = [ 
            [sg.Text('Diari', size=(30, 1), )],
            [sg.Multiline(key='Text', size=(40, 20))],
            [sg.Button('Enviar'), sg.Button('Sortir')]
         ]
layoutRegister = [
            [sg.Text('RegisterLayout')],
            [sg.Text('Nom')],
            [sg.Input(key='nom')],
            [sg.Text('Cognom')],
            [sg.Input(key='cognom')],
            [sg.Text('Edat')],
            [sg.Input(key='edat')],
            [sg.Text('Contrasenya')],
            [sg.Input(key='contrasenya', password_char='*')],
            [sg.Text('Psicoleg')],
            [sg.Input(key='psicoleg')],
            [sg.Button('Submit'), sg.Button('Cancel')]
        ]

#STEP 2 - create the window
window = [sg.Window('El meu diari', layout), sg.Window('MyDiari', layoutRegister)]



def checkRegister():
    #check if file exists
    if os.path.isfile('register.txt'):
        return True
    else:
        return False

# STEP3 - the event loop
if __name__ == '__main__':
    if not os.path.exists('register.txt'):
            event, values = window[1].read()
            with open('register.txt', 'w') as file:
                #write file as json file
                values['FullName'] = values['nom'] + values['cognom']
                val = json.dumps(values)
                file.write(val)
                file.close()
                print(values)
                res = requests.post('http://localhost:5000//createUser', json={"Name": values['nom'], "LastName":values['cognom'],"FullName": values['FullName'], "age":values['edat'], "psw":values['contrasenya'], "psychologist": values['FullName']})

            window[1].close()
    while True:
        #find file register.txt if not exists open layout register
    
        event, values = window[0].read()   # Read the event that happened and the values dictionary

        if event == sg.WIN_CLOSED or event == 'Exit':     # If user closed window with X or if user clicked "Exit" button then exit
            break
        if event == 'Enviar':
            fullName = getFullName()
            text = values['Text']
            res = requests.post('http://localhost:5000/addSentiment', json={"Text": text, "FullName": fullName})
            if res.status_code == 200:
                sg.popup_ok("El sentiment s'ha guardat correctament")  # Shows OK button
                # clear window
                window[0].Element('Text').Update('') 
            


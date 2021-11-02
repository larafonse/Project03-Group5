from django.shortcuts import render
import pyrebase

config = {
    "apiKey": "AIzaSyBpw_nZZ3v63cCKiSFfNU7VK3FoNWNjSHM",
    "authDomain": "projectshare-9f7d8.firebaseapp.com",
    "databaseURL": "https://projectshare-9f7d8-default-rtdb.firebaseio.com",
    "projectId": "projectshare-9f7d8",
    "storageBucket": "projectshare-9f7d8.appspot.com",
    "messagingSenderId": "296830037349",
    "appId": "1:296830037349:web:b693808b7d46ef90eb458c",
    "measurementId": "G-FKJ2SBQGQG"
}

firebase = pyrebase.initialize_app(config)
storage = firebase.storage()
authe = firebase.auth()
database = firebase.database()



def home(request):
    day = database.child('Data').child('Day').get().val()
    id = database.child('Data').child('Id').get().val()
    projectname = database.child('Data').child('Projectname').get().val()
    return render(request,"index.html",{"day":day,"id":id,"projectname":projectname })

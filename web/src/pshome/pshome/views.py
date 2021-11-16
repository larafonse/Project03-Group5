from django.shortcuts import render
from pyrebase import pyrebase

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



def index(request):
    # new_user = database.child('users').push({
    #     'username' : 'monte',
    #     'password' : 'csumb'
    # })
    # print (new_user)
    users = database.child('users').get()
    user_list = users.val()
    for user in users.each():
        list=user.val()
        print(list['username'])  
        return render(request,'index.html', {"user": list['username']})

    return render(request,'index.html', {"user": user_list})

def home(request):
    return render(request,'home.html')

def login(request):
    return render(request, 'login.html')

def signup(request):
    return render(request, 'signup.html')
    

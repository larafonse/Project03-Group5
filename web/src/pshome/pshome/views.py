import requests
from django.shortcuts import redirect, render
from pyrebase import pyrebase
from django.contrib.auth.decorators import login_required
from django.views.decorators.cache import cache_control
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
auth = firebase.auth()
database = firebase.database()

def index(request):
    # new_user = database.child('users').push({
    #     'username' : 'monte',
    #     'password' : 'csumb'
    # })
    # print (new_user)
    request.session['user'] = 0
    print(request.session['user'])
    if 'login_fail' not in request.session:
        request.session['login_fail'] = 0 
    if request.session['login_fail'] == 0:  
        return render(request,'index.html', {"message" : ""})
    elif request.session['login_fail'] == 1:
        request.session['login_fail'] = 0 
        return render(request,'login.html', {"message" : "Username/Password is wrong."})
    else:
        request.session['login_fail'] = 0 
              
    users = database.child('users').get()
    user_list = users.val()
    for user in users.each():
        list=user.val()
        print(list['username'])  
        #return render(request,'index.html', {"user": list['username']})

    return render(request,'index.html', {"message" : "Similar user created, please sign up again"})


def home(request):
    if request.session['user'] > 0:
        print("h")
    else:
        return redirect('/')
    return render(request,'home.html')

@cache_control(no_cache=True, must_revalidate=True, no_store=True)
def login(request):
    if request.method == 'POST':
        usernamee = request.POST.get("user") 
        password = request.POST.get("password") 
        users = database.child('users').get()
        user_list = users.val()
        found = False
        for user in users.each():
            list=user.val()
            print(list['username']) 
            if(list['username'] == usernamee and list['password'] == password):
                request.session['user'] = 1
                request.session['login_fail'] = 0
                found = True
                print(request.session['user'])
                return redirect('/home/')
            
        request.session['login_fail'] = 1
        return redirect('/')
    else:
        print('not pass')
        return render(request, 'login.html')

def signup(request):
    print("hello")
    return render(request, 'signup.html')

def logout(request):
    request.session['user_id'] = 0
    request.session['login_fail'] = 0
    return redirect('/')
    

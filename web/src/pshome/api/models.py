from django.db import models

# Create your models here.


class User(models.Model):
    username = models.CharField(max_length=100)
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    user_description = models.CharField(max_length=1000)
    password = models.CharField(max_length=100)
    picture_url= models.CharField(max_length=10000)
    role = models.CharField(max_length=100)
    @classmethod
    def create(cls, username, first_name,last_name,user_description,password,picture_url,role):
        user = cls(username=username, first_name=first_name,last_name=last_name,user_description=user_description,password=password,picture_url=picture_url,role=role)
        # do something with the book
        return user

    def __str__(self):
        return "Username: "+self.username +" - ID: "+ str(self.id) 


class Post(models.Model):
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)
    project_name = models.CharField(max_length=100)
    project_description = models.CharField(max_length=1000)
    project_img = models.CharField(max_length=10000)
    tech_stack = models.CharField(max_length=10000)
    external_link = models.CharField(max_length=10000)
    @classmethod
    def create(cls, user_id,project_name,project_description,project_img,tech_stack,external_link):
        post = cls(user_id=user_id, project_name=project_name, project_description=project_description, project_img=project_img, tech_stack=tech_stack, external_link=external_link)
        # do something with the book
        return post

class Comment(models.Model):
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)
    post_id = models.ForeignKey(Post, on_delete=models.CASCADE)
    text = models.CharField(max_length=10000)
    @classmethod
    def create(cls, user_id,post_id,text):
        comment = cls(user_id=user_id, post_id=post_id,text=text)
        # do something with the book
        return comment
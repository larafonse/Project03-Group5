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
    def __str__(self):
        return "Username: "+self.username +" - ID: "+ str(self.id) 


class Post(models.Model):
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)
    project_name = models.CharField(max_length=100)
    project_description = models.CharField(max_length=1000)
    project_img = models.CharField(max_length=10000)
    tech_stack = models.CharField(max_length=10000)
    external_link = models.CharField(max_length=10000)

class Comment(models.Model):
    user_id = models.ForeignKey(User, on_delete=models.CASCADE)
    post_id = models.ForeignKey(Post, on_delete=models.CASCADE)
    text = models.CharField(max_length=10000)
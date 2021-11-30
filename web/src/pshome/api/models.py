from django.db import models

# Create your models here.


class User(models.Model):
    name = models.CharField(max_length=100)
    password = models.CharField(max_length=100)
    picture_url= models.CharField(max_length=10000)
    def __str__(self):
        return self.name


class Post(models.Model):
    userID = models.ForeignKey(User, on_delete=models.CASCADE)
    description = models.CharField(max_length=1000)
    picture_url= models.CharField(max_length=10000)
    tech_stack = models.CharField(max_length=10000)
    external_link = models.CharField(max_length=10000)

class Comment(models.Model):
    userID = models.ForeignKey(User, on_delete=models.CASCADE)
    postID = models.ForeignKey(Post, on_delete=models.CASCADE)
    text = models.CharField(max_length=10000)
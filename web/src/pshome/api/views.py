from django.shortcuts import render
from rest_framework import viewsets
from api.serializers import UserSerializer, PostSerializer, CommentSerializer
from api.models import User, Post, Comment
# Create your views here.


class UserViewSet(viewsets.ModelViewSet):
   queryset = User.objects.all()
   serializer_class = UserSerializer

class PostViewSet(viewsets.ModelViewSet):
   queryset = Post.objects.all()
   serializer_class = PostSerializer

class CommentViewSet(viewsets.ModelViewSet):
   queryset = Comment.objects.all()
   serializer_class = CommentSerializer
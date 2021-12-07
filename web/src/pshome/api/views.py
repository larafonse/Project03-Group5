from django.shortcuts import render
from rest_framework.response import Response
from rest_framework import viewsets
from api.serializers import UserSerializer, PostSerializer, CommentSerializer
from api.models import User, Post, Comment
# Create your views here.


class UserViewSet(viewsets.ModelViewSet):
   queryset = User.objects.all()
   serializer_class = UserSerializer
   def get_queryset(self):
      username = self.request.query_params.get('username')
      password = self.request.query_params.get('password')
      queryset = User.objects.all()
      if username is not None and password is not None:
         queryset = queryset.filter(username=username,password=password)
         return queryset
      return []

class PostViewSet(viewsets.ModelViewSet):
   queryset = Post.objects.all()
   serializer_class = PostSerializer

class CommentViewSet(viewsets.ModelViewSet):
   queryset = Comment.objects.all()
   serializer_class = CommentSerializer
from django.shortcuts import render
from rest_framework.response import Response
from django.http import JsonResponse
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
      id = self.request.query_params.get('id')
      queryset = User.objects.all()
      if username is not None and password is not None:
         queryset = queryset.filter(username=username,password=password)
      if id is not None:
         queryset = queryset.filter(id=int(id))
      return queryset

   def create(self, validated_data):
      username = self.request.query_params.get('username')
      password = self.request.query_params.get('password')
      first_name = self.request.query_params.get('first_name')
      last_name = self.request.query_params.get('last_name')
      user_description = self.request.query_params.get('user_description')
      picture_url = self.request.query_params.get('picture_url')
      role = self.request.query_params.get('role')
      user = User.create(username, first_name,last_name,user_description,password,picture_url,role)
      user.save()
      return JsonResponse({"message":username + " was successfully created"})

class PostViewSet(viewsets.ModelViewSet):
   queryset = Post.objects.all()
   serializer_class = PostSerializer
   def create(self, validated_data):
      user_id = self.request.query_params.get('user_id')
      project_name = self.request.query_params.get('project_name')
      project_description = self.request.query_params.get('project_description')
      project_img = self.request.query_params.get('project_img')
      tech_stack = self.request.query_params.get('tech_stack')
      external_link = self.request.query_params.get('external_link')
      user = User.objects.get(id=user_id)
      post = Post.create(user,project_name,project_description,project_img,tech_stack,external_link)
      post.save()
      return JsonResponse({"message": user.username +"'s "+project_name+" project was successfully posted"})

class CommentViewSet(viewsets.ModelViewSet):
   queryset = Comment.objects.all()
   serializer_class = CommentSerializer
   def create(self,validated_data):
      user_id = self.request.query_params.get('user_id')
      post_id = self.request.query_params.get('post_id')
      text =  self.request.query_params.get('text')

      user = User.objects.get(id=user_id)
      post = Post.objects.get(id=post_id)

      comment = Comment.create(user,post,text)
      return JsonResponse({"message":user.username+"'s comment was successfully posted"})
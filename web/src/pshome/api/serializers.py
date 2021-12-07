from rest_framework import serializers

from api.models import User, Post, Comment

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('id','username','first_name','last_name','user_description','password','picture_url','role')

class PostSerializer(serializers.ModelSerializer):
    class Meta:
        model = Post
        fields = ('id','project_name','user_id','project_description','project_img','tech_stack','external_link')

class CommentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Comment
        fields = ('id','user_id','post_id','text')
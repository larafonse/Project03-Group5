from rest_framework import serializers

from api.models import User, Post, Comment

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('name','password','picture_url')

class PostSerializer(serializers.ModelSerializer):
    class Meta:
        model = Post
        fields = ('userID','description','picture_url','tech_stack','external_link')

class CommentSerializer(serializers.ModelSerializer):
    class Meta:
        model = Comment
        fields = ('userID','postID','text')
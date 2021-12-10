package com.example.cst438_project_share;

import java.util.List;

import kotlin.reflect.KCallable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/posts/?format=json")
    Call<List<Posts>> getUserPosts();

    @GET("api/posts/?format=json")
    Call<List<Posts>> getPosts();

    @GET("api/users/?format=json")
    Call<List<Users>> getUserById(@Query("id") int id);

    @POST("api/users/?")
    @FormUrlEncoded
    Call<Users> insertUser(
            @Field("username") String username,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("user_description") String user_description,
            @Field("password") String password,
            @Field("picture_url") String picture_url,
            @Field("role") String role
    );
}

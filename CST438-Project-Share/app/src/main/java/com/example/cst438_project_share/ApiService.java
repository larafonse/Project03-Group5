package com.example.cst438_project_share;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/posts/?format=json")
    Call<List<Posts>> getPosts();

    @GET("api/users/?format=json")
    Call<List<Users>> getUserById(@Query("id") int id);

}

package com.example.cst438_project_share;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApiService {

    @GET("api/posts/?format=json")
    Call<List<Posts>> getPosts();

}

package com.example.cst438_project_share.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cst438_project_share.ApiService;
import com.example.cst438_project_share.PostAdapter;
import com.example.cst438_project_share.Posts;
import com.example.cst438_project_share.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    public static final String TAG = "FeedFragment";

    private List<Posts> posts = new ArrayList<>();
    private RecyclerView recyclerView;
    public PostAdapter postsAdapter;
    private static Retrofit retrofit = null;
    public static final String BASE_URL = "https://project-share-g5.herokuapp.com/";

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvProfile);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Posts>> call = apiService.getPosts();
        call.enqueue(new retrofit2.Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                posts = new ArrayList<>();
                posts = response.body();
                recyclerView.setAdapter(new PostAdapter(getContext(), posts));
                recyclerView.smoothScrollToPosition(0);
                Log.i("RetroFitDatabase", "Num of posts: " + posts);
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Log.e("RetroFitDatabase", "Error: " + t.toString());
            }
        });
    }
}
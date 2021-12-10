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
import android.widget.TextView;

import com.example.cst438_project_share.ApiService;
import com.example.cst438_project_share.PostAdapter;
import com.example.cst438_project_share.Posts;
import com.example.cst438_project_share.R;
import com.example.cst438_project_share.Users;

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

    TextView username, role;

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

        int userID = getArguments().getInt("userID");

        recyclerView = view.findViewById(R.id.rvProfile);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        username = view.findViewById(R.id.user_username);
        role = view.findViewById(R.id.user_role);

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
                List<Posts> nPosts = new ArrayList<>();
                for (int i = 0; i < posts.size(); i++) {
                    if (posts.get(i).getUserId() == userID){
                        nPosts.add(posts.get(i));
                    }
                }
                recyclerView.setAdapter(new PostAdapter(getContext(), nPosts));
                recyclerView.smoothScrollToPosition(0);
                Log.i("RetroFitDatabase", "Num of posts: " + nPosts);
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Log.e("RetroFitDatabase", "Error: " + t.toString());
            }
        });

        Call<List<Users>> call1 = apiService.getUserById(userID);
        call1.enqueue(new retrofit2.Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                username.setText(response.body().get(0).getUsername());
                role.setText(response.body().get(0).getRole());
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

            }
        });
    }
}
package com.example.cst438_project_share.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cst438_project_share.ApiService;
import com.example.cst438_project_share.LoginActivity;
import com.example.cst438_project_share.Posts;
import com.example.cst438_project_share.R;
import com.example.cst438_project_share.RegisterActivity;
import com.example.cst438_project_share.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComposeFragment extends Fragment {

    private static Retrofit retrofit = null;
    public static final String BASE_URL = "https://project-share-g5.herokuapp.com/";
    public static final String TAG = "ComposeFragment";

    EditText pname, pstack, pdes, purl;
    Button button;

    public ComposeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compose, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        pname = view.findViewById(R.id.p_name);
        pstack = view.findViewById(R.id.p_techS);
        pdes = view.findViewById(R.id.p_des);
        purl = view.findViewById(R.id.p_url);
        button = view.findViewById(R.id.addPost);

        int userID = getArguments().getInt("userID");
        Log.i(TAG, String.valueOf(userID));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = pname.getText().toString();
                String tStack = pstack.getText().toString();
                String description = pdes.getText().toString();
                String url = purl.getText().toString();

                ApiService apiService = retrofit.create(ApiService.class);
                apiService.insertPost(name, tStack, description, url, "none", userID).enqueue(new Callback<Posts>() {
                    @Override
                    public void onResponse(Call<Posts> call, Response<Posts> response) {
                        Log.i(TAG, response.toString());
                        if (response.message().equals("Created")) {
                            Toast.makeText(getContext(), "Post Created!", Toast.LENGTH_SHORT).show();
                            pname.setText("");
                            pstack.setText("");
                            pdes.setText("");
                            purl.setText("");
                        } else {
                            Toast.makeText(getContext(), "Post NOT Created!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Posts> call, Throwable t) {
                        Toast.makeText(getContext(), "Post not Created!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });




    }
}
package com.example.cst438_project_share;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    List<Posts> postsList;
    Context context;
    private static Retrofit retrofit = null;
    public static final String BASE_URL = "https://project-share-g5.herokuapp.com/";

    public PostAdapter(Context context, List<Posts> postsList) {
        this.postsList = postsList;
        this.context = context;
    }


    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.feedprojectitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        holder.projectName.setText(postsList.get(position).getProjectName());
        holder.techStack.setText(postsList.get(position).getTechStack());
        holder.projectDes.setText(postsList.get(position).getDescription());

        Picasso.with(context).load(postsList.get(position).getImgURL()).into(holder.projectImg);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        Log.i("Adapter", postsList.get(position).getUserId() + " ");

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Users>> call = apiService.getUserById(postsList.get(position).getUserId());
        call.enqueue(new retrofit2.Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                Log.i("Adapter", postsList.get(position).getUserId() + " " + response.body().size() + " " + response.body().toString());
                Users users = response.body().get(0);
                holder.projectOwner.setText(users.username);
                holder.projectRole.setText(users.role);
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.e("Adapter", t.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView projectImg;
        TextView projectName;
        TextView techStack;
        TextView projectDes;
        TextView projectOwner;
        TextView projectRole;

        public ViewHolder(View view) {
            super(view);

            projectImg = view.findViewById(R.id.project_img);
            projectName = view.findViewById(R.id.project_name);
            techStack = view.findViewById(R.id.tech_stack);
            projectDes = view.findViewById(R.id.description);
            projectOwner = view.findViewById(R.id.project_owner);
            projectRole = view.findViewById(R.id.project_role);
        }

    }
}

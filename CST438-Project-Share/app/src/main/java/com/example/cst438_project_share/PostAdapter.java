package com.example.cst438_project_share;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    List<Posts> postsList;
    Context context;

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

package com.example.livewell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BlogRecyclerAdapter extends RecyclerView.Adapter<BlogRecyclerAdapter.ViewHolder>{
    public List<BlogPost> blog_list;

    public Context context;

    public BlogRecyclerAdapter(List<BlogPost> blog_list) {

        this.blog_list = blog_list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_post_item, parent, false);

        context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String username_data = blog_list.get(position).getUsername();
        holder.setUsernameText(username_data + ":");

        String title_data = blog_list.get(position).getTitle();
        holder.setTitleText(title_data);

        String desc_data = blog_list.get(position).getDesc();
        holder.setDescText(desc_data);

        String image_url = blog_list.get(position).getImageUrl();
        holder.setBlogImage(image_url);

    }

    @Override
    public int getItemCount() {
        return blog_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private TextView usernameView;
        private TextView titleView;
        private TextView descView;
        private ImageView blogImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setUsernameText(String usernameText) {
            usernameView = mView.findViewById(R.id.post_username);
            usernameView.setText(usernameText);
        }

        public void setTitleText(String titleText) {
            titleView = mView.findViewById(R.id.post_title);
            titleView.setText(titleText);
        }

        public void setDescText(String descText) {
            descView = mView.findViewById(R.id.post_description);
            descView.setText(descText);
        }

        public void setBlogImage(String downloadUri) {
            blogImageView = mView.findViewById(R.id.post_image);

            Glide.with(context).load(downloadUri).into(blogImageView);
        }
    }
}

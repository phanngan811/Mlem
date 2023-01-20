package com.example.mlem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.BlogDetail;
import com.example.mlem.Model.Blog;
import com.example.mlem.databinding.BlogListItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BlogRVAdapter extends RecyclerView.Adapter<BlogRVAdapter.BlogViewHolder> {

    private final Context mContext;
    private List<Blog> blogs;

    public BlogRVAdapter(Context context) {
        blogs = new ArrayList<>();
        mContext = context;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BlogListItemBinding blogListItemBinding = BlogListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BlogViewHolder(blogListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        Blog blog = blogs.get(position);
        if (blog == null) {
            return;
        }
        holder.binding.txtTitle.setText(blog.getTitle());
        holder.binding.txtAuthor.setText(blog.getAuthor());
        holder.binding.txtPreview.setText(blog.getContent());
        if (blog.getImageUrl() != null) {
            Picasso.get().load(blog.getImageUrl()).into(holder.binding.ingredientImage);
        }
        holder.binding.parent.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, BlogDetail.class);
            intent.putExtra("blogId", blog.getId());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (this.blogs == null) {
            return 0;
        }
        return blogs.size();
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        private final BlogListItemBinding binding;

        public BlogViewHolder(@NonNull BlogListItemBinding blogListItemBinding) {
            super(blogListItemBinding.getRoot());
            this.binding = blogListItemBinding;
        }
    }
}
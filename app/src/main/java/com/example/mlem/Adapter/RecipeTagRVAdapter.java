package com.example.mlem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.Model.Tag;
import com.example.mlem.R;
import com.example.mlem.databinding.ItemRecipeStepBinding;
import com.example.mlem.databinding.ItemTagBinding;

import java.util.ArrayList;
import java.util.List;

public class RecipeTagRVAdapter extends RecyclerView.Adapter<RecipeTagRVAdapter.TagViewHolder>{

    private List<String> tags;

    public RecipeTagRVAdapter() {
        tags = new ArrayList<>();
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTagBinding itemTagBinding = ItemTagBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TagViewHolder(itemTagBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
        String tag = tags.get(position);
        if (tag == null) {
            return;
        }
        holder.binding.txtTag.setText("#"+tag);
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public static class TagViewHolder extends RecyclerView.ViewHolder {
        private final ItemTagBinding binding;

        public TagViewHolder(@NonNull ItemTagBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
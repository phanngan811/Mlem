package com.example.mlem.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.databinding.ItemBlogParagraphBinding;

import java.util.ArrayList;
import java.util.List;

public class BlogParagraphRVAdapter extends RecyclerView.Adapter<BlogParagraphRVAdapter.ParagraphViewHolder> {

    private List<String> paragraphs;

    public BlogParagraphRVAdapter() {
        paragraphs = new ArrayList<>();
    }

    public void setParagraphs(List<String> paragraphs) {
        this.paragraphs = paragraphs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ParagraphViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBlogParagraphBinding binding = ItemBlogParagraphBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ParagraphViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ParagraphViewHolder holder, int position) {
        String paragraph = paragraphs.get(position);
        if (paragraph == null) {
            return;
        }
        holder.binding.txt.setText(paragraph);
    }

    @Override
    public int getItemCount() {
        return paragraphs.size();
    }

    public static class ParagraphViewHolder extends RecyclerView.ViewHolder {
        private final ItemBlogParagraphBinding binding;

        public ParagraphViewHolder(@NonNull ItemBlogParagraphBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

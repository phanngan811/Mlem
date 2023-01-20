package com.example.mlem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.Model.Recipe;
import com.example.mlem.RecipeDetail;
import com.example.mlem.databinding.ItemMmadRecipeBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MMADRecipeRVAdapter extends RecyclerView.Adapter<MMADRecipeRVAdapter.RecipeViewHolder> {

    private final Context context;
    private List<Recipe> recipes;

    public MMADRecipeRVAdapter(Context context) {
        this.context = context;
        recipes = new ArrayList<>();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMmadRecipeBinding binding = ItemMmadRecipeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        if (recipe == null) {
            return;
        }
        holder.mBinding.txtName.setText(recipe.getName());
        holder.mBinding.txtDescription.setText(recipe.getDescription());
        holder.mBinding.parent.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecipeDetail.class);
            intent.putExtra("recipeId", recipe.getId());
            context.startActivity(intent);
        });
        if (recipe.getImageUrl() != null) {
            Picasso.get().load(recipe.getImageUrl()).into(holder.mBinding.imgCover);
        }
    }

    @Override
    public int getItemCount() {
        if (this.recipes == null) {
            return 0;
        }
        return recipes.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        private final ItemMmadRecipeBinding mBinding;

        public RecipeViewHolder(@NonNull ItemMmadRecipeBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
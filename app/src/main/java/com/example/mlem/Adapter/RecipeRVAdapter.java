package com.example.mlem.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.Model.Recipe;
import com.example.mlem.databinding.RecipeListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class RecipeRVAdapter extends RecyclerView.Adapter<RecipeRVAdapter.RecipeViewHolder> {

    private List<Recipe> recipes;

    public RecipeRVAdapter() {
        recipes = new ArrayList<>();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecipeListItemBinding recipeListItemBinding = RecipeListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecipeViewHolder(recipeListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        if (recipe == null) {
            return;
        }
        holder.mBinding.txtName.setText(recipe.getName());
    }

    @Override
    public int getItemCount() {
        if (this.recipes == null) {
            return 0;
        }
        return recipes.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        private final RecipeListItemBinding mBinding;

        public RecipeViewHolder(@NonNull RecipeListItemBinding recipeListItemBinding) {
            super(recipeListItemBinding.getRoot());
            this.mBinding = recipeListItemBinding;
        }
    }
}
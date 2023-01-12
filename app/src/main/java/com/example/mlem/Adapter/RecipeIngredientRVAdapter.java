package com.example.mlem.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.Model.Ingredient;
import com.example.mlem.databinding.ItemRecipeIngredientBinding;

import java.util.ArrayList;
import java.util.List;

public class RecipeIngredientRVAdapter extends RecyclerView.Adapter<RecipeIngredientRVAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredients;

    public RecipeIngredientRVAdapter() {
        ingredients = new ArrayList<>();
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeIngredientRVAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecipeIngredientBinding itemRecipeIngredientBinding = ItemRecipeIngredientBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecipeIngredientRVAdapter.IngredientViewHolder(itemRecipeIngredientBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeIngredientRVAdapter.IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        if (ingredient == null) {
            return;
        }
        holder.binding.tvName.setText(ingredient.getName());
        holder.binding.tvAmount.setText(String.format("%s%s", String.valueOf(ingredient.getPrice()), ingredient.getUnit() != null ? " " + ingredient.getUnit() : ""));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        private final ItemRecipeIngredientBinding binding;

        public IngredientViewHolder(@NonNull ItemRecipeIngredientBinding itemRecipeIngredientBinding) {
            super(itemRecipeIngredientBinding.getRoot());
            this.binding = itemRecipeIngredientBinding;
        }
    }
}

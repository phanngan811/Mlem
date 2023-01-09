package com.example.mlem.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.Model.Ingredient;
import com.example.mlem.databinding.CardLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class IngredientRVAdapter extends RecyclerView.Adapter<IngredientRVAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredients;

    public IngredientRVAdapter() {
        ingredients = new ArrayList<>();
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardLayoutBinding cardLayoutBinding = CardLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new IngredientViewHolder(cardLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        if (ingredient == null) {
            return;
        }
        holder.binding.ingredientName.setText(ingredient.getName());
        holder.binding.ingredientPrice.setText(String.valueOf(ingredient.getPrice()));
    }

    @Override
    public int getItemCount() {
        if (this.ingredients == null) {
            return 0;
        }
        return ingredients.size();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        private final CardLayoutBinding binding;

        public IngredientViewHolder(@NonNull CardLayoutBinding cardLayoutBinding) {
            super(cardLayoutBinding.getRoot());
            this.binding = cardLayoutBinding;
        }
    }
}
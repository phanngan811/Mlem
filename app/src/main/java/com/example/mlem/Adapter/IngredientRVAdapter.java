package com.example.mlem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.IngredientDetail;
import com.example.mlem.Model.Ingredient;
import com.example.mlem.databinding.CardLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class IngredientRVAdapter extends RecyclerView.Adapter<IngredientRVAdapter.IngredientViewHolder> {

    private List<Ingredient> ingredients;
    private final Context mContext;

    public IngredientRVAdapter(Context context) {
        ingredients = new ArrayList<>();
        mContext = context;
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
        if (ingredient.getImageUrl() != null) {
            Picasso.get().load(ingredient.getImageUrl()).into(holder.binding.ingredientImage);
        }
        holder.binding.parent.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, IngredientDetail.class);
            intent.putExtra("ingredientId", ingredient.getId());
            mContext.startActivity(intent);
        });
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
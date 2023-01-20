package com.example.mlem.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.Model.CartItem;
import com.example.mlem.databinding.ItemRecipeIngredientBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeIngredientRVAdapter extends RecyclerView.Adapter<RecipeIngredientRVAdapter.IngredientViewHolder> {

    private List<CartItem> cartItems;

    public RecipeIngredientRVAdapter() {
        cartItems = new ArrayList<>();
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
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
        CartItem cartItem = cartItems.get(position);
        if (cartItem == null) {
            return;
        }
        holder.binding.tvName.setText(cartItem.getIngredient().getName());
        holder.binding.tvAmount.setText(String.format("%s %s", cartItem.getAmount(), "units"));
        if (cartItem.getIngredient().getImageUrl() != null) {
            Picasso.get().load(cartItem.getIngredient().getImageUrl()).into(holder.binding.image);
        }
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        private final ItemRecipeIngredientBinding binding;

        public IngredientViewHolder(@NonNull ItemRecipeIngredientBinding itemRecipeIngredientBinding) {
            super(itemRecipeIngredientBinding.getRoot());
            this.binding = itemRecipeIngredientBinding;
        }
    }
}

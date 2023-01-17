package com.example.mlem.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlem.Model.CartItem;
import com.example.mlem.databinding.CartItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CartRVAdapter extends RecyclerView.Adapter<CartRVAdapter.CartViewHolder> {

    private List<CartItem> cart;

    public CartRVAdapter() {
        cart = new ArrayList<>();
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemBinding cartItemBinding = CartItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(cartItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cart.get(position);
        if (cartItem == null) {
            return;
        }
        holder.binding.txtIngredientName.setText(cartItem.getIngredient().getName());
        holder.binding.txtAmount.setText(String.valueOf(cartItem.getAmount()));
        holder.binding.txtPrice.setText(String.valueOf(cartItem.getIngredient().getPrice() * cartItem.getAmount()));
        holder.binding.txtUnit.setText(cartItem.getIngredient().getUnit());
    }

    @Override
    public int getItemCount() {
        return cart.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        private final CartItemBinding binding;

        public CartViewHolder(@NonNull CartItemBinding blogListItemBinding) {
            super(blogListItemBinding.getRoot());
            this.binding = blogListItemBinding;
        }
    }
}
package com.example.mlem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Model.CartItem;
import com.example.mlem.Model.Ingredient;
import com.example.mlem.Repository.CartRepository;
import com.example.mlem.Repository.IngredientRepository;
import com.example.mlem.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class IngredientVM extends AndroidViewModel {
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final MutableLiveData<String> id;
    private final MutableLiveData<Ingredient> ingredient;

    public IngredientVM(@NonNull Application application) {
        super(application);
        ingredientRepository = new IngredientRepository();
        userRepository = new UserRepository();
        cartRepository = new CartRepository();
        id = new MutableLiveData<>("");
        ingredient = new MutableLiveData<>(new Ingredient());
    }

    public void getOne() {
        if (id.getValue() == null) return;
        ingredientRepository.getOne(id.getValue()).addOnSuccessListener(innerInnerSnapshot -> {
            Ingredient ingredient = innerInnerSnapshot.toObject(Ingredient.class);
            if (ingredient == null) return;
            ingredient.setId(innerInnerSnapshot.getId());
            this.ingredient.setValue(ingredient);
            List<CartItem> cart = new ArrayList<>();

            // get ingredient for each ingredient id
            if (ingredient.getCartItemIds() == null) return;
            for (String cartItemId : ingredient.getCartItemIds()) {
                ingredientRepository.getOne(cartItemId).addOnSuccessListener(innerSnapshot -> {
                    CartItem cartItem = innerSnapshot.toObject(CartItem.class);
                    if (cartItem == null) return;
                    cartItem.setId(innerSnapshot.getId());

                    if (ingredient == null) return;
                    ingredient.setId(innerInnerSnapshot.getId());

                    cartItem.setIngredient(ingredient);
                    cart.add(cartItem);
                    ingredient.setCartItems(cart);

                    this.ingredient.setValue(ingredient);
                });
            }
        });
    }

    public void addCart(int amount) {
        if (ingredient.getValue() == null) return;
        String userId = userRepository.getUser().getUid();
        CartItem cartItem = new CartItem();
        cartItem.setUserId(userId);
        cartItem.setIngredientId(ingredient.getValue().getId());
        cartItem.setAmount((double) amount);
        cartRepository.insert(cartItem);
    }

    public LiveData<String> getId() {
        return id;
    }

    public void setId(String id) {
        this.id.setValue(id);
    }

    public LiveData<Ingredient> getIngredient() {
        return ingredient;
    }

}
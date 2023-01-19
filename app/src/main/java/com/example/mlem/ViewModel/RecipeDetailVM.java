package com.example.mlem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Model.CartItem;
import com.example.mlem.Model.Ingredient;
import com.example.mlem.Model.Recipe;
import com.example.mlem.Repository.CartRepository;
import com.example.mlem.Repository.IngredientRepository;
import com.example.mlem.Repository.RecipeRepository;
import com.example.mlem.Repository.TagRepository;
import com.example.mlem.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecipeDetailVM extends AndroidViewModel {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final CartRepository cartRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    private final MutableLiveData<String> id;
    private final MutableLiveData<Recipe> recipe;

    public RecipeDetailVM(@NonNull Application application) {
        super(application);
        recipeRepository = new RecipeRepository();
        ingredientRepository = new IngredientRepository();
        cartRepository = new CartRepository();
        tagRepository = new TagRepository();
        id = new MutableLiveData<>("");
        recipe = new MutableLiveData<>(new Recipe());
        userRepository = new UserRepository();
    }

    public void addCart() {
        String userId = userRepository.getUser().getUid();
        List<CartItem> cartItems = Objects.requireNonNull(recipe.getValue()).getCartItems();
        if (cartItems == null) return;
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem tmp = cartItems.get(i);
            tmp.setId(null);
            tmp.setUserId(userId);
            cartRepository.insert(tmp);
        }
    }

    public void getOne() {
        if (id.getValue() == null) return;
        recipeRepository.getOne(id.getValue()).addOnSuccessListener(snapshot -> {
            Recipe recipe = snapshot.toObject(Recipe.class);
            if (recipe == null) return;
            recipe.setId(snapshot.getId());

            List<CartItem> cart = new ArrayList<>();

            // get ingredient for each ingredient id
            if (recipe.getCartItemIds() == null) return;
            for (String cartItemId : recipe.getCartItemIds()) {
                cartRepository.getOne(cartItemId).addOnSuccessListener(innerSnapshot -> {
                    CartItem cartItem = innerSnapshot.toObject(CartItem.class);
                    if (cartItem == null) return;
                    cartItem.setId(innerSnapshot.getId());

                    ingredientRepository.getOne(cartItem.getIngredientId()).addOnSuccessListener(innerInnerSnapshot -> {
                        Ingredient ingredient = innerInnerSnapshot.toObject(Ingredient.class);
                        if (ingredient == null) return;
                        ingredient.setId(innerInnerSnapshot.getId());

                        cartItem.setIngredient(ingredient);
                        cart.add(cartItem);
                        recipe.setCartItems(cart);

                        this.recipe.setValue(recipe);
                    });

                });
            }
        });
    }

    public LiveData<Recipe> getRecipe() {
        return recipe;
    }

    public LiveData<String> getId() {
        return id;
    }

    public void setId(String id) {
        this.id.setValue(id);
    }
}

package com.example.mlem.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Model.CartItem;
import com.example.mlem.Model.Ingredient;
import com.example.mlem.Repository.CartRepository;
import com.example.mlem.Repository.IngredientRepository;
import com.example.mlem.Repository.UserRepository;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class OrderSummaryVM extends AndroidViewModel {
    private static final String TAG = OrderSummaryVM.class.getName();

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final MutableLiveData<List<CartItem>> result;

    public OrderSummaryVM(@NonNull Application application) {
        super(application);
        cartRepository = new CartRepository();
        userRepository = new UserRepository();
        ingredientRepository = new IngredientRepository();
        result = new MutableLiveData<>(new ArrayList<>());
    }

    public void getCart() {
        // get all cart items of the current user
        cartRepository.getCartByUser(userRepository.getUser().getUid()).addSnapshotListener((snapshots, e) -> {
            if (e != null) {
                Log.w(TAG, "listen:error", e);
                return;
            }

            if (snapshots == null) return;
            List<CartItem> cartItems = new ArrayList<>();

            for (DocumentSnapshot snapshot : snapshots) {
                CartItem cartItem = snapshot.toObject(CartItem.class);
                if (cartItem == null) return;
                cartItem.setId(snapshot.getId());

                // get ingredient detail for each cart item
                ingredientRepository.getOne(cartItem.getIngredientId()).addOnSuccessListener(innerSnapshot -> {
                    Ingredient ingredient = innerSnapshot.toObject(Ingredient.class);
                    if (ingredient == null) return;
                    ingredient.setId(snapshot.getId());

                    cartItem.setIngredient(ingredient);
                    cartItems.add(cartItem);

                    result.setValue(cartItems);
                });
            }
        });
    }

    public LiveData<List<CartItem>> getResult() {
        return result;
    }
}

package com.example.mlem.Repository;

import android.util.Log;

import com.example.mlem.Model.CartItem;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class CartRepository {
    private static final String TAG = CartRepository.class.getName();
    private static final String collectionPath = "carts";

    private final FirebaseFirestore firestore;
    private final CollectionReference collectionReference;
    private final IngredientRepository ingredientRepository;

    public CartRepository() {
        firestore = FirebaseFirestore.getInstance();
        collectionReference = firestore.collection(collectionPath);
        ingredientRepository = new IngredientRepository();
    }

    public void insert(CartItem cartItem) {
        collectionReference.add(cartItem)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "Added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding", e));
    }

    public void update(CartItem cartItem) {
        collectionReference.document(cartItem.getId()).set(cartItem)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Updated successfully"))
                .addOnFailureListener(e -> Log.w(TAG, "Error updating", e));
    }

    public void delete(CartItem cartItem) {
        collectionReference.document(cartItem.getId()).delete()
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Deleted successfully"))
                .addOnFailureListener(e -> Log.w(TAG, "Error deleting", e));
    }

    public Task<DocumentSnapshot> getOne(String id) {
        return collectionReference.document(id).get();
    }

    public Query getCartByUser(String userId) {
        return collectionReference.whereEqualTo("userId", userId);
    }
}

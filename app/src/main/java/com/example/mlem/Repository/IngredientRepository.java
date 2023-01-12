package com.example.mlem.Repository;

import android.util.Log;

import com.example.mlem.Model.Ingredient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class IngredientRepository {
    private static final String TAG = IngredientRepository.class.getName();
    private static final String collectionPath = "ingredients";

    private final FirebaseFirestore firestore;
    private final CollectionReference collectionReference;

    public IngredientRepository() {
        firestore = FirebaseFirestore.getInstance();
        collectionReference = firestore.collection(collectionPath);
    }

    public void insert(Ingredient ingredient) {
        collectionReference.add(ingredient)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "Added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding", e));
    }

    public void update(Ingredient ingredient) {
        collectionReference.document(ingredient.getId()).set(ingredient)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Updated successfully"))
                .addOnFailureListener(e -> Log.w(TAG, "Error updating", e));
    }

    public void delete(Ingredient ingredient) {
        collectionReference.document(ingredient.getId()).delete()
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Deleted successfully"))
                .addOnFailureListener(e -> Log.w(TAG, "Error deleting", e));
    }

    public Ingredient getOne(String id) {
        final Ingredient[] ingredient = {new Ingredient()};
        collectionReference.document(id).get()
                .addOnSuccessListener(documentSnapshot -> {
                    Ingredient item = documentSnapshot.toObject(Ingredient.class);
                    if (item == null) return;
                    item.setId(documentSnapshot.getId());
                    ingredient[0] = item;
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error getting " + id, e);
                });
        return ingredient[0];
    }

    public Task<QuerySnapshot> getAll() {
        return collectionReference.get();
    }

    public Task<QuerySnapshot> searchByName(String queryString) {
        return collectionReference.whereEqualTo("name", queryString).get();
    }

    public Task<QuerySnapshot> searchByTag(String queryString) {
        String[] queryList = queryString.split("\\s+");
        return collectionReference.whereArrayContainsAny("tagNames", Arrays.asList(queryList)).get();
    }
}

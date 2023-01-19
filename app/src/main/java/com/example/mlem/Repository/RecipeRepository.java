package com.example.mlem.Repository;

import android.util.Log;

import com.example.mlem.Model.Recipe;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class RecipeRepository {
    private static final String TAG = RecipeRepository.class.getName();
    private static final String collectionPath = "recipes";

    private final FirebaseFirestore firestore;
    private final CollectionReference collectionReference;
    private final IngredientRepository ingredientRepository;

    public RecipeRepository() {
        firestore = FirebaseFirestore.getInstance();
        collectionReference = firestore.collection(collectionPath);
        ingredientRepository = new IngredientRepository();
    }

    public void insert(Recipe recipe) {
        collectionReference.add(recipe)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "Added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding", e));
    }

    public void update(Recipe recipe) {
        collectionReference.document(recipe.getId()).set(recipe)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Updated successfully"))
                .addOnFailureListener(e -> Log.w(TAG, "Error updating", e));
    }

    public void delete(Recipe recipe) {
        collectionReference.document(recipe.getId()).delete()
                .addOnSuccessListener(aVoid -> Log.d(TAG, "Deleted successfully"))
                .addOnFailureListener(e -> Log.w(TAG, "Error deleting", e));
    }

    public Task<DocumentSnapshot> getOne(String id) {
        return collectionReference.document(id).get();
    }

    public Task<QuerySnapshot> getAll() {
        return collectionReference.get();
    }

    public Task<QuerySnapshot> searchByName(String queryString) {
        return collectionReference.whereEqualTo("name", queryString).get();
    }

    public Task<QuerySnapshot> searchByTag(List<String> queryList) {
        return collectionReference.whereArrayContainsAny("tagNames", queryList).get();
    }
}

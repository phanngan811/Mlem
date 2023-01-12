package com.example.mlem.Repository;

import android.util.Log;

import com.example.mlem.Model.Ingredient;
import com.example.mlem.Model.Recipe;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Recipe getOne(String id) {
        final Recipe[] recipe = {new Recipe()};

        collectionReference.document(id).get()
                .addOnSuccessListener(document -> {
                    Recipe item = document.toObject(Recipe.class);
                    if (item == null) return;
                    item.setId(document.getId());

                    List<Ingredient> list = new ArrayList<>();

                    // get ingredient for each ingredient id
                    for (String ingredientId : item.getIngredientIds()) {
                        ingredientRepository.getOne(ingredientId).addOnSuccessListener(document1 -> {
                            Ingredient item1 = document.toObject(Ingredient.class);
                            if (item1 == null) return;
                            item1.setId(document.getId());
                            list.add(item1);
                        });
                    }

                    item.setIngredients(list);
                    recipe[0] = item;
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Get one", e);
                });

        return recipe[0];
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

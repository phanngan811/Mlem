package com.example.mlem.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Model.Ingredient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class IngredientRepository {
    private static final String collectionPath = "ingredients";
    private static final String TAG = "Ingredient Repository";

    private final FirebaseFirestore firestore;

    public IngredientRepository() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void insert(Ingredient ingredient) {
        firestore.collection(collectionPath).add(ingredient).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "Added with ID: " + documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding", e);
            }
        });
    }

    public void update(Ingredient ingredient) {
        firestore.collection(collectionPath).document(ingredient.getId()).set(ingredient).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Updated successfully");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error updating", e);
            }
        });
    }

    public void delete(Ingredient ingredient) {
        firestore.collection(collectionPath).document(ingredient.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Deleted successfully");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error deleting", e);
            }
        });
    }

    public Ingredient getOne(String id) {
        final Ingredient[] ingredient = {null};

        firestore.collection(collectionPath).document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Ingredient ingredientTemp = document.toObject(Ingredient.class);
                        assert ingredientTemp != null;
                        ingredientTemp.setId(document.getId());
                        ingredient[0] = ingredientTemp;
                    } else {
                        Log.d(TAG, "Document does not exist");
                    }
                } else {
                    Log.d(TAG, "Error getting document: ", task.getException());
                }
            }
        });

        return ingredient[0];
    }

    public LiveData<List<Ingredient>> getAll() {
        final MutableLiveData<List<Ingredient>> liveData = new MutableLiveData<>();

        firestore.collection(collectionPath).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Ingredient> ingredients = new ArrayList<>();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Ingredient ingredient = documentSnapshot.toObject(Ingredient.class);
                    ingredient.setId(documentSnapshot.getId());
                    ingredients.add(ingredient);
                }
                liveData.setValue(ingredients);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error getting all", e);
            }
        });

        return liveData;
    }
}
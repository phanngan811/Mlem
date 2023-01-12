package com.example.mlem.ViewModel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Model.Ingredient;
import com.example.mlem.Repository.IngredientRepository;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class DashboardVM extends AndroidViewModel {
    private static final String TAG = IngredientSearchResultVM.class.getName();
    private IngredientRepository ingredientRepository;

    private final MutableLiveData<List<Ingredient>> result;

    public DashboardVM(@NonNull Application application) {
        super(application);
        ingredientRepository = new IngredientRepository();
        result = new MutableLiveData<>();
    }

    public void getIngredients() {
        ingredientRepository.getAll().addOnSuccessListener(task -> {
            List<Ingredient> list = new ArrayList<>();
            for (DocumentSnapshot document: task.getDocuments()) {
                Ingredient item = document.toObject(Ingredient.class);
                item.setId(document.getId());
                list.add(item);
            }
            result.setValue(list);
        });
    }

    public LiveData<List<Ingredient>> getResult() {return result;}
}



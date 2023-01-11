package com.example.mlem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Enum.SearchByType;
import com.example.mlem.Model.Ingredient;
import com.example.mlem.Repository.IngredientRepository;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class IngredientSearchResultVM extends AndroidViewModel {
    private static final String TAG = IngredientSearchResultVM.class.getName();

    private final IngredientRepository ingredientRepository;
    private final MutableLiveData<List<Ingredient>> result;

    public IngredientSearchResultVM(@NonNull Application application) {
        super(application);
        ingredientRepository = new IngredientRepository();
        result = new MutableLiveData<>(new ArrayList<>());
    }

    public void search(String query, SearchByType type) {
        if (type == SearchByType.NAME) {
            ingredientRepository.searchByName(query).addOnSuccessListener(queryDocumentSnapshots -> {
                List<Ingredient> list = new ArrayList<>();
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    Ingredient item = document.toObject(Ingredient.class);
                    item.setId(document.getId());
                    list.add(item);
                }
                this.result.setValue(list);
            });
        } else {
            ingredientRepository.searchByTag(query).addOnSuccessListener(queryDocumentSnapshots -> {
                List<Ingredient> list = new ArrayList<>();
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    Ingredient item = document.toObject(Ingredient.class);
                    item.setId(document.getId());
                    list.add(item);
                }
                this.result.setValue(list);
            });
        }
    }

    public LiveData<List<Ingredient>> getResult() {
        return result;
    }
}

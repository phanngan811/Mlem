package com.example.mlem.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Enum.SearchByType;
import com.example.mlem.Model.Ingredient;
import com.example.mlem.Model.Recipe;
import com.example.mlem.Repository.RecipeRepository;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class RecipeSearchResultVM extends AndroidViewModel {
    private static final String TAG = RecipeSearchResultVM.class.getName();

    private final RecipeRepository recipeRepository;
    private final MutableLiveData<List<Recipe>> result;

    public RecipeSearchResultVM(@NonNull Application application) {
        super(application);
        recipeRepository = new RecipeRepository();
        result = new MutableLiveData<>();
    }

    public void search(String query, SearchByType type) {
        if (type == SearchByType.NAME) {
            recipeRepository.searchByName(query).addOnSuccessListener(queryDocumentSnapshots -> {
                List<Recipe> list = new ArrayList<>();
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    Recipe item = document.toObject(Recipe.class);
                    item.setId(document.getId());
                    list.add(item);
                }
                this.result.setValue(list);
            });
        } else {
            recipeRepository.searchByTag(query).addOnSuccessListener(queryDocumentSnapshots -> {
                List<Recipe> list = new ArrayList<>();
                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    Recipe item = document.toObject(Recipe.class);
                    item.setId(document.getId());
                    list.add(item);
                }
                this.result.setValue(list);
            });
        }
    }

    public LiveData<List<Recipe>> getResult() {
        return result;
    }
}

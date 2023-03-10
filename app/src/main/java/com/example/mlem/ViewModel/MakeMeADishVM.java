package com.example.mlem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.MakeMeADishActivity;
import com.example.mlem.Model.Recipe;
import com.example.mlem.Repository.RecipeRepository;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MakeMeADishVM extends AndroidViewModel {
    private static final String TAG = MakeMeADishActivity.class.getName();

    private final MutableLiveData<List<Recipe>> recipes;
    private final RecipeRepository recipeRepository;

    public MakeMeADishVM(@NonNull Application application) {
        super(application);
        recipes = new MutableLiveData<>(new ArrayList<>());
        recipeRepository = new RecipeRepository();
    }

    public void searchRecipes(List<String> chips) {
        if (chips.size() == 0) return;
        recipeRepository.searchByTag(chips).addOnSuccessListener(queryDocumentSnapshots -> {
            List<Recipe> list = new ArrayList<>();
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Recipe item = document.toObject(Recipe.class);
                item.setId(document.getId());
                list.add(item);
            }
            for (Recipe c : list) {
                System.out.println(c);
            }
            this.recipes.setValue(list);
        });
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }
}

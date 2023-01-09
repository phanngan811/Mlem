package com.example.mlem.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    public void search(String query) {
        recipeRepository.search(query).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Recipe> list = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Recipe item = document.toObject(Recipe.class);
                    item.setId(document.getId());
                    list.add(item);
                }
                result.setValue(list);
            } else {
                Log.e(TAG, "Error searching", task.getException());
            }
        });
    }

    public LiveData<List<Recipe>> getResult() {
        return result;
    }
}

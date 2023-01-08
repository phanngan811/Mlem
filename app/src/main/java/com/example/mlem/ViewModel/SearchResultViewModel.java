package com.example.mlem.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Enum.SearchType;
import com.example.mlem.Model.Ingredient;
import com.example.mlem.Repository.BlogRepository;
import com.example.mlem.Repository.IngredientRepository;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchResultViewModel extends AndroidViewModel {
    private static final String TAG = SearchResultViewModel.class.getName();

    private final IngredientRepository ingredientRepository;
    private final BlogRepository blogRepository;
    private final MutableLiveData<List<Ingredient>> result;
    private MutableLiveData<SearchType> searchType;

    public SearchResultViewModel(@NonNull Application application) {
        super(application);
        ingredientRepository = new IngredientRepository();
        blogRepository = new BlogRepository();
        result = new MutableLiveData<>();
        searchType = new MutableLiveData<>();
        searchType.setValue(SearchType.INGREDIENT);
    }

    public void search(String query) {
        if (searchType.getValue() == SearchType.INGREDIENT) {
            searchIngredients(query);
        } else {
            searchBlogs(query);
        }
    }

    public void searchIngredients(String query) {
        ingredientRepository.search(query).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Ingredient> list = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Ingredient ingredient = document.toObject(Ingredient.class);
                    ingredient.setId(document.getId());
                    list.add(ingredient);
                }
                result.setValue(list);
            } else {
                Log.w(TAG, "Error searching", task.getException());
            }
        });
    }

    public void searchBlogs(String query) {
        blogRepository.search(query).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Ingredient> list = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Ingredient ingredient = document.toObject(Ingredient.class);
                    ingredient.setId(document.getId());
                    list.add(ingredient);
                }
                result.setValue(list);
            } else {
                Log.w(TAG, "Error searching", task.getException());
            }
        });
    }

    public void changeSearchType(SearchType searchType) {
        this.searchType.setValue(searchType);
    }

    public LiveData<SearchType> getSearchType() {
        return searchType;
    }

    public LiveData<List<Ingredient>> getResult() {
        return result;
    }
}

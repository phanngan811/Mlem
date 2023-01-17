package com.example.mlem.ViewModel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Model.Blog;
import com.example.mlem.Model.Ingredient;
import com.example.mlem.Repository.BlogRepository;
import com.example.mlem.Repository.IngredientRepository;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class DashboardVM extends AndroidViewModel {
    private static final String TAG = IngredientSearchResultVM.class.getName();
    private IngredientRepository ingredientRepository;
    private BlogRepository blogRepository;

    private final MutableLiveData<List<Ingredient>> ingredientResults;
    private final MutableLiveData<List<Blog>> blogResults;

    public DashboardVM(@NonNull Application application) {
        super(application);
        ingredientRepository = new IngredientRepository();
        blogRepository = new BlogRepository();
        ingredientResults = new MutableLiveData<>();
        blogResults = new MutableLiveData<>();
    }

    public void getIngredients() {
        ingredientRepository.getAll().addOnSuccessListener(task -> {
            List<Ingredient> list = new ArrayList<>();
            for (DocumentSnapshot document: task.getDocuments()) {
                Ingredient item = document.toObject(Ingredient.class);
                item.setId(document.getId());
                list.add(item);
            }
            ingredientResults.setValue(list);
        });
    }

    public void getBlogs() {
        blogRepository.getAll().addOnSuccessListener(task -> {
            List<Blog> list = new ArrayList<>();
            for (DocumentSnapshot document: task.getDocuments()) {
                Blog item = document.toObject(Blog.class);
                item.setId(document.getId());
                list.add(item);
            }
            blogResults.setValue(list);
        });
    }

    public LiveData<List<Ingredient>> getIngredientResults() {return ingredientResults;}
    public LiveData<List<Blog>> getBlogResults() {return blogResults;}
}



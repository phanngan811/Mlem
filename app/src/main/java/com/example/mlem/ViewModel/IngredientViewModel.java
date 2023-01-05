package com.example.mlem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mlem.Model.Ingredient;
import com.example.mlem.Repository.IngredientRepository;

import java.util.List;

public class IngredientViewModel extends AndroidViewModel {
    private IngredientRepository repository;
    private LiveData<List<Ingredient>> allIngredients;

    public IngredientViewModel(@NonNull Application application) {
        super(application);
        repository = new IngredientRepository();
        allIngredients = repository.getAll();
    }

    public void insert(Ingredient ingredient) {
        repository.insert(ingredient);
    }

    public void update(Ingredient ingredient) {
        repository.update(ingredient);
    }

    public void delete(Ingredient ingredient) {
        repository.delete(ingredient);
    }

    public LiveData<List<Ingredient>> getAll() {
        return allIngredients;
    }
}
package com.example.mlem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Model.Ingredient;
import com.example.mlem.Model.Recipe;
import com.example.mlem.Repository.IngredientRepository;
import com.example.mlem.Repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailVM extends AndroidViewModel {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final MutableLiveData<String> id;
    private final MutableLiveData<Recipe> recipe;

    public RecipeDetailVM(@NonNull Application application) {
        super(application);
        recipeRepository = new RecipeRepository();
        ingredientRepository = new IngredientRepository();
        id = new MutableLiveData<>("");
        recipe = new MutableLiveData<>(new Recipe());
    }

    public void getOne() {
        recipeRepository.getOne(id.getValue()).addOnSuccessListener(snapshot -> {
            Recipe recipe = snapshot.toObject(Recipe.class);
            if (recipe == null) return;
            recipe.setId(snapshot.getId());

            List<Ingredient> ingredients = new ArrayList<>();

            // get ingredient for each ingredient id
            for (String ingredientId : recipe.getIngredientIds()) {
                ingredientRepository.getOne(ingredientId).addOnSuccessListener(innerSnapshot -> {
                    Ingredient ingredient = innerSnapshot.toObject(Ingredient.class);
                    if (ingredient == null) return;
                    ingredient.setId(innerSnapshot.getId());
                    ingredients.add(ingredient);
                    recipe.setIngredients(ingredients);

                    this.recipe.setValue(recipe);
                });
            }
        });
    }

    public LiveData<Recipe> getRecipe() {
        return recipe;
    }

    public LiveData<String> getId() {
        return id;
    }

    public void setId(String id) {
        this.id.setValue(id);
    }
}

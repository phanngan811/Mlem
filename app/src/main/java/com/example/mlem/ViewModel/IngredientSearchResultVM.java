package com.example.mlem.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
        result = new MutableLiveData<>();
    }

    public void search(String query) {
        ingredientRepository.search(query).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Ingredient> list = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Ingredient item = document.toObject(Ingredient.class);
                    item.setId(document.getId());
                    list.add(item);
                }
                result.setValue(list);
            } else {
                Log.e(TAG, "Error searching", task.getException());
            }
        });
    }

    public LiveData<List<Ingredient>> getResult() {
        return result;
    }
}

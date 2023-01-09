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
    private MutableLiveData<SearchType> searchType;

    public SearchResultViewModel(@NonNull Application application) {
        super(application);
        searchType = new MutableLiveData<>();
        searchType.setValue(SearchType.INGREDIENT);
    }

    public void changeSearchType(SearchType searchType) {
        this.searchType.setValue(searchType);
    }

    public LiveData<SearchType> getSearchType() {
        return searchType;
    }
}

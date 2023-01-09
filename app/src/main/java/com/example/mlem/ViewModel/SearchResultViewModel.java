package com.example.mlem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Enum.SearchType;

public class SearchResultViewModel extends AndroidViewModel {
    private MutableLiveData<SearchType> searchType;
    private MutableLiveData<String> searchQuery;

    public SearchResultViewModel(@NonNull Application application) {
        super(application);
        searchQuery = new MutableLiveData<>();
        searchQuery.setValue("");
        searchType = new MutableLiveData<>();
        searchType.setValue(SearchType.INGREDIENT);
    }

    public void changeSearchType(SearchType searchType) {
        this.searchType.setValue(searchType);
    }

    public LiveData<String> getSearchQuery() {
        return searchQuery;
    }

    public LiveData<SearchType> getSearchType() {
        return searchType;
    }
}

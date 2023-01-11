package com.example.mlem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Enum.SearchType;

public class SearchResultViewModel extends AndroidViewModel {
    private final MutableLiveData<SearchType> searchType;

    public SearchResultViewModel(@NonNull Application application) {
        super(application);
        searchType = new MutableLiveData<>(SearchType.INGREDIENT);
    }

    public void changeSearchType(SearchType searchType) {
        this.searchType.setValue(searchType);
    }

    public LiveData<SearchType> getSearchType() {
        return searchType;
    }
}

package com.example.mlem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Model.Tag;
import com.example.mlem.Repository.TagRepository;

import java.util.List;

public class TagViewModel extends AndroidViewModel {
    private TagRepository repository;
    private LiveData<List<Tag>> allTags;

    public TagViewModel(@NonNull Application application) {
        super(application);
        repository = new TagRepository();
        allTags = new MutableLiveData<>();
    }

    public void insert(Tag tag) {
        repository.insert(tag);
    }

    public void update(Tag tag) {
        repository.update(tag);
    }

    public void delete(Tag tag) {
        repository.delete(tag);
    }

    public LiveData<List<Tag>> getAll() {
        return allTags;
    }
}
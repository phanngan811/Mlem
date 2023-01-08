package com.example.mlem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Model.Blog;
import com.example.mlem.Repository.BlogRepository;

import java.util.List;

public class BlogViewModel extends AndroidViewModel {
    private BlogRepository repository;
    private LiveData<List<Blog>> allBlogs;

    public BlogViewModel(@NonNull Application application) {
        super(application);
        repository = new BlogRepository();
        allBlogs = new MutableLiveData<>();
    }

    public void insert(Blog blog) {
        repository.insert(blog);
    }

    public void update(Blog blog) {
        repository.update(blog);
    }

    public void delete(Blog blog) {
        repository.delete(blog);
    }

    public LiveData<List<Blog>> getAll() {
        return allBlogs;
    }
}

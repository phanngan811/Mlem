package com.example.mlem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Model.Blog;
import com.example.mlem.Model.CartItem;
import com.example.mlem.Model.Blog;
import com.example.mlem.Repository.BlogRepository;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.cache.DiskLruCache;

public class BlogVM extends AndroidViewModel {
    private final BlogRepository blogRepository;
    private final MutableLiveData<String> id;
    private final MutableLiveData<Blog> blog;

    public BlogVM(@NonNull Application application) {
        super(application);
        blogRepository = new BlogRepository();
        id = new MutableLiveData<>("");
        blog = new MutableLiveData<>(new Blog());
    }

    public void getOne() {
        if (id.getValue() == null) return;
        blogRepository.getOne(id.getValue()).addOnSuccessListener(innerInnerSnapshot -> {
            Blog blog = innerInnerSnapshot.toObject(Blog.class);
            if (blog == null) return;
            blog.setId(innerInnerSnapshot.getId());
            this.blog.setValue(blog);
        });
    }

    public void setId(String id) {
        this.id.setValue(id);
    }
    public LiveData<String> getId() {
        return id;
    }
    public LiveData<Blog> getBlog() {
        return blog;
    }

}

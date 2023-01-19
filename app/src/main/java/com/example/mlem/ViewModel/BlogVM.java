package com.example.mlem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Model.Blog;
import com.example.mlem.Model.CartItem;
import com.example.mlem.Model.Blog;
import com.example.mlem.Model.Recipe;
import com.example.mlem.Repository.BlogRepository;
import com.example.mlem.Repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.cache.DiskLruCache;

public class BlogVM extends AndroidViewModel {
    private final BlogRepository blogRepository;
    private final RecipeRepository recipeRepository;
    private final MutableLiveData<String> id;
    private final MutableLiveData<Blog> blog;
    private final MutableLiveData<Recipe> recipe;

    public BlogVM(@NonNull Application application) {
        super(application);
        blogRepository = new BlogRepository();
        recipeRepository = new RecipeRepository();
        id = new MutableLiveData<>("");
        blog = new MutableLiveData<>(new Blog());
        recipe = new MutableLiveData<>(new Recipe());
    }

    public void getOne() {
        if (id.getValue() == null) return;
        blogRepository.getOne(id.getValue()).addOnSuccessListener(snapshot -> {
            Blog blog = snapshot.toObject(Blog.class);
            if (blog == null) return;
            blog.setId(snapshot.getId());
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

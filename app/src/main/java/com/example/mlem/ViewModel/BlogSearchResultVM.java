package com.example.mlem.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Model.Blog;
import com.example.mlem.Repository.BlogRepository;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class BlogSearchResultVM extends AndroidViewModel {
    private static final String TAG = BlogSearchResultVM.class.getName();

    private final BlogRepository blogRepository;
    private final MutableLiveData<List<Blog>> result;

    public BlogSearchResultVM(@NonNull Application application) {
        super(application);
        blogRepository = new BlogRepository();
        result = new MutableLiveData<>();
    }

    public void search(String query) {
        blogRepository.search(query).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Blog> list = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Blog item = document.toObject(Blog.class);
                    item.setId(document.getId());
                    list.add(item);
                }
                result.setValue(list);
            } else {
                Log.e(TAG, "Error searching", task.getException());
            }
        });
    }

    public LiveData<List<Blog>> getResult() {
        return result;
    }
}

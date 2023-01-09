package com.example.mlem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.mlem.Repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class MainViewModel extends AndroidViewModel {
    private UserRepository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository();
    }

    public FirebaseUser getUser() {
        return repository.getUser();
    }
}

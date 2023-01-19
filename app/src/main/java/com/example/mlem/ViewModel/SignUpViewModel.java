package com.example.mlem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mlem.Repository.UserRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

public class SignUpViewModel extends AndroidViewModel {
    private final UserRepository repository;
    private final MutableLiveData<Boolean> registerSuccess;
    private final MutableLiveData<String> errorMessage;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository();
        registerSuccess = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
    }

    public void login(String email, String password) {
        registerSuccess.setValue(false);
        errorMessage.setValue("");

        repository.register(email, password).addOnSuccessListener(authResult -> registerSuccess.setValue(true)).addOnFailureListener(e -> errorMessage.setValue(e.getMessage()));
    }

    public LiveData<Boolean> getRegisterSuccess() {
        return registerSuccess;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}

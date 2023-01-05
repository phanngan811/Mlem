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

public class RegisterViewModel extends AndroidViewModel {
    private final UserRepository repository;
    private final MutableLiveData<Boolean> registrationSuccess;
    private final MutableLiveData<String> errorMessage;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository();
        registrationSuccess = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
    }

    public void register(String email, String password) {
        registrationSuccess.setValue(false);
        errorMessage.setValue("");

        repository.register(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                registrationSuccess.setValue(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                errorMessage.setValue(e.getMessage());
            }
        });
    }

    public LiveData<Boolean> getRegistrationSuccess() {
        return registrationSuccess;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
}

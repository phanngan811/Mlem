package com.example.mlem.Repository;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserRepository {
    FirebaseAuth firebaseAuth;

    public UserRepository() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public FirebaseUser getUser() {
        return firebaseAuth.getCurrentUser();
    }

    public Task<AuthResult> login(String email, String password) {
        return firebaseAuth.signInWithEmailAndPassword(email, password);
    }

    public Task<AuthResult> register(String email, String password) {
        return firebaseAuth.createUserWithEmailAndPassword(email, password);
    }
}

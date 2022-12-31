package com.example.mlem.Service;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class AuthService {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public AuthService() {
        mAuth = FirebaseAuth.getInstance();
    }

    public FirebaseUser getUser() {
        return mAuth.getCurrentUser();
    }

    public void login(String email, String password, OnCompleteListener<AuthResult> listener) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(listener);
    }

    public void register(String email, String password, OnCompleteListener<AuthResult> listener) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(listener);
    }

    public void changePassword(String currentPassword, String newPassword, OnCompleteListener<Void> listener) {
        FirebaseUser user = this.getUser();

        // Check if the user is null or not first
        if (user == null || user.getEmail() == null) {
            return;
        }

        // Get the user's current credentials
        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), currentPassword);

        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Password is correct
                    user.updatePassword(newPassword).addOnCompleteListener(listener);
                } else {
                    // Password is not correct
                    listener.onComplete(task);
                }
            }
        });
    }
}

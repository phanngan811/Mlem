package com.example.mlem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mlem.ViewModel.LoginViewModel;

public class SignIn extends AppCompatActivity {
    LoginViewModel loginViewModel;
    EditText editTextUsername;
    EditText editTextPassword;
    TextView txtError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        editTextUsername = findViewById(R.id.editUsername);
        editTextPassword = findViewById(R.id.editPassword);
        txtError = findViewById(R.id.txtError);

        loginViewModel.getErrorMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txtError.setText(s);
            }
        });

        loginViewModel.getLoginSuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    goToDashboard();
                }
            }
        });
    }

    public void onClickConfirm(View view) {
        loginViewModel.login(editTextUsername.getText().toString(), editTextPassword.getText().toString());
    }

    private void goToDashboard() {
        Intent intent = new Intent(SignIn.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
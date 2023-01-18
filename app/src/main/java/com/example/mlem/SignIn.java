package com.example.mlem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mlem.ViewModel.LoginViewModel;
import com.example.mlem.databinding.ActivitySignInBinding;

public class SignIn extends AppCompatActivity {

    LoginViewModel mViewModel;
    ActivitySignInBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySignInBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        initListeners();
        initObservers();
    }

    private void initObservers() {
        mViewModel.getErrorMessage().observe(this, s -> mBinding.txtError.setText(s));

        mViewModel.getLoginSuccess().observe(this, aBoolean -> {
            if (aBoolean) {
                goToDashboard();
            }
        });
    }

    private void initListeners() {
        mBinding.btnConfirm.setOnClickListener(v -> {
            mViewModel.login(mBinding.editUsername.getText().toString(), mBinding.editPassword.getText().toString());
        });
    }

    private void goToDashboard() {
        Intent intent = new Intent(SignIn.this, DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
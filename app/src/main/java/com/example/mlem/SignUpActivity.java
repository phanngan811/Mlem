package com.example.mlem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mlem.ViewModel.SignUpViewModel;
import com.example.mlem.databinding.ActivitySignUpBinding;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding mBinding;
    SignUpViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        mViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        initListeners();
        initObservers();
    }

    public void initListeners() {
        mBinding.btnConfirm.setOnClickListener(v -> {
            mViewModel.login(Objects.requireNonNull(mBinding.etEmail.getText()).toString(), Objects.requireNonNull(mBinding.etPassword.getText()).toString());
        });
    }

    public void initObservers() {
        mViewModel.getErrorMessage().observe(this, e -> {
            mBinding.txtError.setText(e);
        });
        mViewModel.getRegisterSuccess().observe(this, boo -> {
            if (boo) {
                goToDashboard();
            }
        });
    }

    private void goToDashboard() {
        Intent intent = new Intent(SignUpActivity.this, DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
package com.example.mlem;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
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
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Sign Up");

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
        int errorBg = getColor(R.color.error);
        int textColor = getColor(R.color.neutral_100);

        mViewModel.getErrorMessage().observe(this, s -> {
            if (s == null || s.trim().equals("")) return;
            mBinding.txtError.setText(s);
            mBinding.txtError.setTextColor(textColor);
            mBinding.errorParent.setBackgroundColor(errorBg);
        });

        mViewModel.getRegisterSuccess().observe(this, boo -> {
            if (boo) {
                goToDashboard();
            }
        });
    }

    private void goToDashboard() {
        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
     
    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}
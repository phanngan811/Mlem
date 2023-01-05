package com.example.mlem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mlem.ViewModel.UserViewModel;

public class MainActivity extends AppCompatActivity {
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        if (userViewModel.getUser() != null) {
            goToDashboard();
        }

        // uncomment this to test dashboard
//        Intent intent = new Intent(MainActivity.this, DashBoard.class);
//        startActivity(intent);

    }

    public void onClickSignIn(View view) {
        Intent i = new Intent(MainActivity.this, SignIn.class);
        startActivity(i);
    }

    public void onClickSignUp(View view) {
        Intent i = new Intent(MainActivity.this, SignIn.class);
        startActivity(i);
    }

    private void goToDashboard() {
        Intent intent = new Intent(MainActivity.this, DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
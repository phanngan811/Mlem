package com.example.mlem;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mlem.ViewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        if (mViewModel.getUser() != null) {
            System.out.println("----GOT here------");
            Log.d(TAG, "----GOT here------");
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
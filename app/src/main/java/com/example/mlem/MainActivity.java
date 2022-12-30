package com.example.mlem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // uncomment this to test dashboard
//        Intent intent = new Intent(MainActivity.this, DashBoard.class);
//        startActivity(intent);

    }
    public void onClickSignIn(View view){
            Intent i = new Intent(MainActivity.this, SignIn.class);
            startActivity(i);

    }
    public void onClickSignUp(View view){
        Intent i = new Intent(MainActivity.this, SignIn.class);
        startActivity(i);

    }
}
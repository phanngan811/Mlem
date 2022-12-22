package com.example.mlem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }
    public void onClickConfirm(View view){
        EditText editTextUsername = (EditText) findViewById(R.id.editUsername);
        EditText editTextPassword = (EditText) findViewById(R.id.editPassword);
        TextView txtError = (TextView) findViewById(R.id.txtError);

        if(editTextUsername.getText().toString().equals("") || editTextPassword.getText().toString().equals("")){
            txtError.setText("Username or password is wrong");
        }
    }
}
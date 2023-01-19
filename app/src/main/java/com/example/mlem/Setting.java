package com.example.mlem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mlem.Model.Tag;
import com.example.mlem.ViewModel.SettingVM;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Setting extends AppCompatActivity {
    SettingVM settingVM;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        settingVM = new ViewModelProvider(this).get(SettingVM.class);

        Button btn = (Button) findViewById(R.id.btnSave);
        EditText editTextTag = (EditText) findViewById(R.id.editTag);
        Button btnLogout = (Button) findViewById(R.id.btnLogout);
        EditText txtFullname = (EditText) findViewById(R.id.txtFullname);

        settingVM.getName();
        settingVM.getUser().observe(this, user -> {
            txtFullname.setText(user.getFirstName() +" "+ user.getLastName());
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingVM.insertTag(new Tag("0", editTextTag.getText().toString()));
                Toast.makeText(getApplicationContext(),"Successfully add Preferences",Toast.LENGTH_SHORT).show();

            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingVM.logout();
                goToDashboard();
            }
        });

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

    private void goToDashboard() {
        Intent intent = new Intent(Setting.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
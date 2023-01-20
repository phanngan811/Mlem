package com.example.mlem;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mlem.Model.Tag;
import com.example.mlem.ViewModel.SettingVM;
import com.example.mlem.databinding.FragmentSettingsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;


public class SettingsFragment extends Fragment {

    SettingVM settingVM;
    private View mView;
    private FragmentSettingsBinding mBinding;
    private FirebaseUser user;


    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentSettingsBinding.inflate(inflater, container, false);
        mView = mBinding.getRoot();

        settingVM = new ViewModelProvider(this).get(SettingVM.class);
        user = FirebaseAuth.getInstance().getCurrentUser();

        initObservers();

        return mView;
    }



    private void initObservers() {

        Button btnLogout = (Button) mView.findViewById(R.id.btnLogout);
        EditText editTextName = (EditText) mView.findViewById(R.id.edit_text_name);


        String displayName = user.getDisplayName();
        if(displayName.equals("")) {
            Log.d("-----------USER NAME NOT EXIST-----------", user.getDisplayName());
        }else {
            Log.d("-----------USER NAME EXIST-----------", user.getDisplayName());
            editTextName.setText(user.getDisplayName());
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingVM.logout();
                goToDashboard();
            }
        });
    }

    private void updateName() {
        EditText nameEditText = mView.findViewById(R.id.edit_text_name);
        String updateName = nameEditText.getText().toString();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(updateName)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });

    }

    private void goToDashboard() {
        Intent intent = new Intent(mView.getContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }


}
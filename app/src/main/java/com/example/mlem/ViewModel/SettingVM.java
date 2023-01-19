package com.example.mlem.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.mlem.Model.Ingredient;
import com.example.mlem.Model.Tag;
import com.example.mlem.Model.User;
import com.example.mlem.Repository.TagRepository;
import com.example.mlem.Repository.UserRepository;

import java.util.Objects;

public class SettingVM extends AndroidViewModel {

    private final MutableLiveData<Tag> tag;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final MutableLiveData<User> user;
    private final MutableLiveData<String> id;


    public SettingVM(@NonNull Application application) {
        super(application);
        tagRepository = new TagRepository();
        userRepository = new UserRepository();
        tag = new MutableLiveData<>(new Tag());
        user = new MutableLiveData<>(new User());
        id = new MutableLiveData<>("");

    }

    public void getName(){
        if (id.getValue() == null) return;
        userRepository.getUser();
        User users = new User();
        users.setFirstName(Objects.requireNonNull(user.getValue()).getFirstName());
        users.setLastName(Objects.requireNonNull(user.getValue()).getLastName());

        this.user.setValue(users);
    }

    public void insertTag(Tag tagName){
        tagRepository.insert(tagName);
    }

    public void logout(){
        userRepository.logout();
    }
    public LiveData<Tag> getTag() {
        return tag;
    }
    public LiveData<User> getUser() {
        return user;
    }
}

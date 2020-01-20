package com.example.petbookbeta.ui.myprofile;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyProfileViewModelo extends ViewModel {

    private MutableLiveData<String> mText;

    public MyProfileViewModelo() {
        mText = new MutableLiveData<>();
        mText.setValue("Perfil de usuario");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
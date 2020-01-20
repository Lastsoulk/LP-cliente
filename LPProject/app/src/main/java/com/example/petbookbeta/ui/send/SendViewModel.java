package com.example.petbookbeta.ui.send;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SendViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SendViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aquí se muestra las vacunas guardadas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
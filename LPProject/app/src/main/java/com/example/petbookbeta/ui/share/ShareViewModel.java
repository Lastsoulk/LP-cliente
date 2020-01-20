package com.example.petbookbeta.ui.share;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShareViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aquí se muestra las mascotas guardadas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
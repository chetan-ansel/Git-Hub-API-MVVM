package com.demo.gamechangesns.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DetailViewModel extends ViewModel {

    private final MutableLiveData<String> mId = new MutableLiveData<>();

    public void setId(String id) {
        mId.setValue(id);
    }

    public LiveData<String> getId() {
        return mId;
    }
}

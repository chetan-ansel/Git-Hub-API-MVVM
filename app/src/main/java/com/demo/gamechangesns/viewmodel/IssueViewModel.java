package com.demo.gamechangesns.viewmodel;

import android.app.Application;

import com.demo.gamechangesns.model.IssueModel;
import com.demo.gamechangesns.repository.CacheData;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class IssueViewModel extends AndroidViewModel {


    private final CacheData mCacheData;

    public IssueViewModel(Application application) {
        super(application);
        mCacheData = new CacheData(application);
    }

    public LiveData<List<IssueModel>> getUserDetails() {
        return mCacheData.getIssuesData();
    }

}

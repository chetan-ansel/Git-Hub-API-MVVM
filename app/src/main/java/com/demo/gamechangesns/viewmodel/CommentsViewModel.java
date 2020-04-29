package com.demo.gamechangesns.viewmodel;

import com.demo.gamechangesns.model.CommentsModel;
import com.demo.gamechangesns.networkservice.RetrofitService;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CommentsViewModel extends ViewModel {

    public LiveData<List<CommentsModel>> getCommentsDetails(String id) {
        return RetrofitService.getInstance().getCommentsList(id);
    }

}

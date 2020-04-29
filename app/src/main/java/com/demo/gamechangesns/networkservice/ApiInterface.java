package com.demo.gamechangesns.networkservice;

import com.demo.gamechangesns.model.CommentsModel;
import com.demo.gamechangesns.model.IssueModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    /**
     * get IssueModel list from cloud API
     *
     * @return {@link } IssueModel
     */
    @GET("issues")
    Call<List<IssueModel>> getIssuesList();

    /**
     * get CommentsModel list from cloud API
     *
     * @return {@link } CommentsModel
     */
    @GET("issues/{id}/comments")
    Call<List<CommentsModel>> getCommentsList(@Path("id") String id);

}

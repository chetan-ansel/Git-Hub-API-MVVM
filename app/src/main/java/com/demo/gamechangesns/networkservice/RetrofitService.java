package com.demo.gamechangesns.networkservice;

import android.util.Log;

import com.demo.gamechangesns.model.CommentsModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static final String API_HOST_URL =
            "https://api.github.com/repos/firebase/firebase-ios-sdk/";
    private static RetrofitService mRetrofitService;

    public synchronized static RetrofitService getInstance() {
        if (mRetrofitService == null) {
            mRetrofitService = new RetrofitService();
        }
        return mRetrofitService;
    }

    public Retrofit getHttpClient() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .build();
        return new Retrofit.Builder()
                .baseUrl(API_HOST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Nullable
    public LiveData<List<CommentsModel>> getCommentsList(String id) {
        final MutableLiveData<List<CommentsModel>> commentsList = new MutableLiveData<>();
        ApiInterface networkInterface = getHttpClient().create(ApiInterface.class);
        networkInterface.getCommentsList(id).enqueue(new Callback<List<CommentsModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<CommentsModel>> call,
                                   @NonNull Response<List<CommentsModel>> response) {
                List<CommentsModel> body = response.body();
                if (body == null) {
                    Log.e("failure in response", "something wrong in response");
                    commentsList.setValue(null);
                    return;
                }
                commentsList.setValue(body);
            }

            @Override
            public void onFailure(@NonNull Call<List<CommentsModel>> call,
                                  @NonNull Throwable t) {
                Log.e("failure in response", "" + t.getMessage());
                commentsList.setValue(null);
            }
        });

        return commentsList;
    }

}
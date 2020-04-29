package com.demo.gamechangesns.repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.demo.gamechangesns.model.IssueModel;
import com.demo.gamechangesns.networkservice.ApiInterface;
import com.demo.gamechangesns.networkservice.RetrofitService;
import com.demo.gamechangesns.utils.Utils;
import com.google.gson.Gson;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CacheData {

    private static final String MyPREFERENCES = "MyPrefs";
    private final SharedPreferences sharedPreferences;

    public CacheData(Application activity) {
        sharedPreferences = activity.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }


    public LiveData<List<IssueModel>> getIssuesData() {
        long time = Utils.getCacheTime(sharedPreferences);
        if (time == 0) {
            return getIssuesList();
        } else {
            Date currentDate = Utils.getCurrentDate();
            Date cacheDate = new Date(time);
            if (currentDate.before(cacheDate)) {
                return Utils.getCacheIssueData(sharedPreferences);
            } else {
                return getIssuesList();
            }

        }
    }

    private LiveData<List<IssueModel>> getIssuesList() {
        final MutableLiveData<List<IssueModel>> issueList = new MutableLiveData<>();
        ApiInterface networkInterface = RetrofitService.getInstance().getHttpClient().
                create(ApiInterface.class);

        networkInterface.getIssuesList().enqueue(new Callback<List<IssueModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<IssueModel>> call,
                                   @NonNull Response<List<IssueModel>> response) {
                List<IssueModel> body = response.body();
                if (body == null) {
                    Log.e("failure in response", "something wrong in response");
                    issueList.setValue(null);
                } else {
                    Utils.saveDateAndData(sharedPreferences, new Gson().toJson(body));
                    issueList.setValue(body);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<IssueModel>> call,
                                  @NonNull Throwable t) {
                Log.e("failure in response", "" + t.getMessage());
                issueList.setValue(null);
            }
        });


        return issueList;
    }
}

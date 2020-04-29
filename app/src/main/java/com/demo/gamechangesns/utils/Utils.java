package com.demo.gamechangesns.utils;

import android.content.SharedPreferences;

import com.demo.gamechangesns.model.IssueModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class Utils {

    private static final int MAX_CHAR = 140;
    private static final String TIME_CACHE = "TIME_CACHE";
    private static final String DATA_CACHE = "DATA_CACHE";


    public static String getChar140(final String issueName) {
        return issueName.length() < MAX_CHAR ? issueName : issueName.substring(0, MAX_CHAR);
    }

    private static Date saveDateAfter24Hours() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return cal.getTime();

    }

    public static Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }

    public static void saveDateAndData(SharedPreferences sharedPreferences, String data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(TIME_CACHE, Utils.saveDateAfter24Hours().getTime());
        editor.putString(DATA_CACHE, data);
        editor.apply();
    }

    public static long getCacheTime(SharedPreferences sharedPreferences) {
        return sharedPreferences.getLong(TIME_CACHE, 0L);
    }

    public static MutableLiveData<List<IssueModel>> getCacheIssueData(
            SharedPreferences sharedPreferences) {
        String issueData = sharedPreferences.getString(DATA_CACHE, null);
        Type type = new TypeToken<List<IssueModel>>() {
        }.getType();
        List<IssueModel> connections = new Gson().fromJson(issueData, type);
        final MutableLiveData<List<IssueModel>> issueList = new MutableLiveData<>();
        issueList.setValue(connections);
        return issueList;
    }

}

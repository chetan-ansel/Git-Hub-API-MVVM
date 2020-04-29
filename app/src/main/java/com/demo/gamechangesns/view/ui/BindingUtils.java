package com.demo.gamechangesns.view.ui;

import android.view.View;
import android.widget.TextView;

import com.demo.gamechangesns.utils.Utils;

import androidx.databinding.BindingAdapter;

public class BindingUtils {

    @BindingAdapter("bodydata")
    public static void setName(TextView view, String text) {
        view.setText(Utils.getChar140(text));
    }

    @BindingAdapter("visibility")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}

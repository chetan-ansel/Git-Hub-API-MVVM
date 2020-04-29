package com.demo.gamechangesns.view.ui;

import android.os.Bundle;

import com.demo.gamechangesns.R;
import com.demo.gamechangesns.viewmodel.DetailViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, IssueFragment.newInstance())
                    .commit();
        }
        DetailViewModel detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailViewModel.getId().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, CommentsFragment.newInstance(s))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}

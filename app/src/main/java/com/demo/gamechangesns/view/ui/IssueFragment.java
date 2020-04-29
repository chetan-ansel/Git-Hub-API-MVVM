package com.demo.gamechangesns.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.gamechangesns.R;
import com.demo.gamechangesns.adapter.IssueListAdapter;
import com.demo.gamechangesns.databinding.IssueListBinding;
import com.demo.gamechangesns.model.IssueModel;
import com.demo.gamechangesns.viewmodel.DetailViewModel;
import com.demo.gamechangesns.viewmodel.IssueViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class IssueFragment extends Fragment {
    private IssueListBinding mIssueListBinding;
    private IssueListAdapter mIssueListAdapter;

    static IssueFragment newInstance() {
        return new IssueFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        IssueViewModel issueViewModel =
                new ViewModelProvider(this).get(IssueViewModel.class);
        issueViewModel.getUserDetails().observe(getViewLifecycleOwner(), new Observer<List<IssueModel>>() {
            @Override
            public void onChanged(@Nullable List<IssueModel> issueModels) {
                if (issueModels == null) {
                    mIssueListBinding.loadIssue.setText(R.string.error);
                } else {
                    setIssueAdapter(issueModels);
                }
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mIssueListBinding = DataBindingUtil.inflate(inflater, R.layout.issue_list,
                container, false);
        initAdapter();
        mIssueListBinding.issueListview.setAdapter(mIssueListAdapter);
        mIssueListBinding.setIsDataLoad(true);
        return mIssueListBinding.getRoot();
    }

    private void setIssueAdapter(final List<IssueModel> issueModels) {
        mIssueListBinding.setIsDataLoad(false);
        mIssueListAdapter.setIssuesData(issueModels);
    }


    private void initAdapter() {
        mIssueListAdapter = new IssueListAdapter(new ArrayList<IssueModel>(), new IssueItemClick() {
                    @Override
                    public void onIssueItemClick(String id) {
                        new ViewModelProvider(requireActivity()).get(DetailViewModel.class).
                                setId(id);
                    }
                });
    }
}

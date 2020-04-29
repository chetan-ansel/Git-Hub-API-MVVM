package com.demo.gamechangesns.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.gamechangesns.R;
import com.demo.gamechangesns.adapter.CommentsListAdapter;
import com.demo.gamechangesns.databinding.IssueListBinding;
import com.demo.gamechangesns.model.CommentsModel;
import com.demo.gamechangesns.viewmodel.CommentsViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class CommentsFragment extends Fragment {

    private static final String COMMENT_ID = "COMMENT_ID";
    private IssueListBinding mIssueListBinding;
    private CommentsListAdapter mIssueListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mIssueListBinding = DataBindingUtil.inflate(inflater, R.layout.issue_list,
                container, false);
        mIssueListAdapter = new CommentsListAdapter(new ArrayList<CommentsModel>());
        mIssueListBinding.issueListview.setAdapter(mIssueListAdapter);
        mIssueListBinding.setIsDataLoad(true);
        if (getArguments() != null) {
            getCommentsData(getArguments().getString("COMMENT_ID"));
        }
        return mIssueListBinding.getRoot();
    }


    private void setIssueAdapter(final List<CommentsModel> issueModels) {
        mIssueListBinding.setIsDataLoad(false);
        mIssueListAdapter.setIssuesData(issueModels);
    }

    private void getCommentsData(String id) {
        CommentsViewModel commentsViewModel = new ViewModelProvider(this).
                get(CommentsViewModel.class);
        commentsViewModel.getCommentsDetails(id).observe(getViewLifecycleOwner(),
                new Observer<List<CommentsModel>>() {
                    @Override
                    public void onChanged(@Nullable List<CommentsModel> commentsModels) {
                        if (commentsModels == null) {
                            mIssueListBinding.loadIssue.setText(R.string.error);
                        } else {
                            if (commentsModels.isEmpty()) {
                                mIssueListBinding.loadIssue.setText(R.string.
                                        comment_is_not_available);
                            } else {
                                setIssueAdapter(commentsModels);
                            }
                        }
                    }
                });
    }

    static CommentsFragment newInstance(String s) {
        CommentsFragment commentsFragment = new CommentsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(COMMENT_ID, s);
        commentsFragment.setArguments(bundle);
        return commentsFragment;
    }
}
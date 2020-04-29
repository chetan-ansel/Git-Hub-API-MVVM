package com.demo.gamechangesns.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.gamechangesns.R;
import com.demo.gamechangesns.databinding.IssueItemBinding;
import com.demo.gamechangesns.model.IssueModel;
import com.demo.gamechangesns.view.ui.IssueItemClick;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class IssueListAdapter extends RecyclerView.Adapter<IssueListAdapter.IssueListViewHolder> {

    private final List<IssueModel> issueModelArrayList = new ArrayList<>();
    private final IssueItemClick mSearchClick;

    /**
     * @param issueModels List of {@link IssueModel}
     * @param searchClick search button click
     */
    public IssueListAdapter(final List<IssueModel> issueModels,
                            final IssueItemClick searchClick) {
        issueModelArrayList.addAll(issueModels);
        mSearchClick = searchClick;
    }

    public void setIssuesData(final List<IssueModel> issueModelList) {

        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return issueModelArrayList.size();
            }

            @Override
            public int getNewListSize() {
                return issueModelList.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return issueModelArrayList.get(oldItemPosition).getId().equals
                        (issueModelList.get(newItemPosition).getId());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return false;
            }
        });

        issueModelArrayList.clear();
        issueModelArrayList.addAll(issueModelList);
        result.dispatchUpdatesTo(this);

    }

    @NonNull
    @Override
    public IssueListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IssueItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.issue_item,
                        parent, false);
        return new IssueListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IssueListViewHolder holder, int position) {
        holder.binding.setIssueData(issueModelArrayList.get(position));
        holder.bindListener(issueModelArrayList.get(position).getNumber(), mSearchClick);
        holder.binding.executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return issueModelArrayList.size();
    }

    static class IssueListViewHolder extends RecyclerView.ViewHolder {

        final IssueItemBinding binding;
        IssueListViewHolder(IssueItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bindListener(final String id, final IssueItemClick itemClickListener) {
            this.binding.executePendingBindings();
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null) {
                        itemClickListener.onIssueItemClick(id);
                    }
                }
            });
        }
    }

}

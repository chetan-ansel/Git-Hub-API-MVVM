package com.demo.gamechangesns.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.demo.gamechangesns.R;
import com.demo.gamechangesns.databinding.CommentItemBinding;
import com.demo.gamechangesns.model.CommentsModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class CommentsListAdapter extends RecyclerView.Adapter
        <CommentsListAdapter.CommentsViewHolder> {

    private final List<CommentsModel> commentsModels = new ArrayList<>();

    /**
     * @param issueModels List of {@link CommentsModel}
     */
    public CommentsListAdapter(final List<CommentsModel> issueModels) {
        commentsModels.addAll(issueModels);
    }

    /**
     * @param commentsModels comments
     */
    public void setIssuesData(final List<CommentsModel> commentsModels) {

        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return CommentsListAdapter.this.commentsModels.size();
            }

            @Override
            public int getNewListSize() {
                return commentsModels.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return CommentsListAdapter.this.commentsModels.get(oldItemPosition).getId().equals
                        (commentsModels.get(newItemPosition).getId());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return false;
            }
        });

        this.commentsModels.clear();
        this.commentsModels.addAll(commentsModels);
        result.dispatchUpdatesTo(this);

    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommentItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.comment_item,
                        parent, false);
        return new CommentsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, int position) {
        holder.binding.setCommentData(commentsModels.get(position));
        holder.binding.executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return commentsModels.size();
    }

    static class CommentsViewHolder extends RecyclerView.ViewHolder {
        final CommentItemBinding binding;

        CommentsViewHolder(CommentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
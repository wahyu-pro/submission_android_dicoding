package com.wahyu.submission2githubapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wahyu.submission2githubapp.R;
import com.wahyu.submission2githubapp.models.OthersModel;

import java.util.ArrayList;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder> {

    private ArrayList<OthersModel> mData = new ArrayList<>();
    public void setData(ArrayList<OthersModel> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FollowingAdapter.FollowingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_items, viewGroup, false);
        return new FollowingAdapter.FollowingViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowingAdapter.FollowingViewHolder holder, int position) {
        OthersModel user = mData.get(position);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.notfound);
        Glide.with(holder.itemView.getContext()).load(user.getAvatar()).apply(options).into(holder.avatar);
        holder.tvUsername.setText(user.getUsername());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FollowingViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername;
        ImageView avatar;

        FollowingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username);
            avatar = itemView.findViewById(R.id.avatar);
        }
    }
}

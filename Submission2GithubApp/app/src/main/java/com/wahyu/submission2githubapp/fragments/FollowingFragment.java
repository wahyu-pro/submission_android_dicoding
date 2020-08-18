package com.wahyu.submission2githubapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wahyu.submission2githubapp.R;
import com.wahyu.submission2githubapp.adapters.FollowerAdapter;
import com.wahyu.submission2githubapp.models.OthersModel;
import com.wahyu.submission2githubapp.viewmodels.FollowerModelView;
import com.wahyu.submission2githubapp.viewmodels.FollowingModelView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FollowingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowingFragment extends Fragment {

    private FollowerAdapter followingAdapter;
    private ProgressBar progressBar;
    private FollowingModelView followingModelView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_USERNAME = "username";
    // TODO: Rename and change types of parameters

    public FollowingFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static FollowingFragment newInstance(String username) {
        FollowingFragment fragment = new FollowingFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_USERNAME, username);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String username = null;
        if (getArguments() != null) {
            username = getArguments().getString(ARG_USERNAME);
        }

        progressBar = view.findViewById(R.id.progressBar);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        followingAdapter = new FollowerAdapter();
        followingAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(followingAdapter);

        followingModelView = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(FollowingModelView.class);
        showLoading(true);
        followingModelView.setListFollower(username);
        followingModelView.getListFollower().observe(getActivity(), new Observer<ArrayList<OthersModel>>() {
            @Override
            public void onChanged(ArrayList<OthersModel> othersModels) {
                if (othersModels != null){
                    followingAdapter.setData(othersModels);
                    showLoading(false);
                }
            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
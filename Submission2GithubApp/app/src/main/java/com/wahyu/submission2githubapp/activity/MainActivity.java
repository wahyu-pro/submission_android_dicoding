package com.wahyu.submission2githubapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wahyu.submission2githubapp.viewmodels.MainViewModel;
import com.wahyu.submission2githubapp.R;
import com.wahyu.submission2githubapp.adapters.UserAdapter;
import com.wahyu.submission2githubapp.models.UserItems;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private UserAdapter userAdapter;
    private ProgressBar progressBar;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Search Item");
        }

        progressBar = findViewById(R.id.progressBar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter();
        userAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(userAdapter);

        userAdapter.setOnItemClickCallback(new UserAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(UserItems data) {
                showSelectedUser(data);
            }
        });

        modelView();
    }

    private void showSelectedUser(UserItems data) {
        Intent moveWithObjectIntent = new Intent(MainActivity.this, DetailUserActivity.class);
        moveWithObjectIntent.putExtra(DetailUserActivity.EXTRA_PERSON, data);
        startActivity(moveWithObjectIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    mainViewModel = new ViewModelProvider(MainActivity.this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
                    showLoading(true);
                    mainViewModel.setListUsers(query);
                    return true;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    mainViewModel = new ViewModelProvider(MainActivity.this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
                    mainViewModel.setListUsers(newText);
                    showLoading(true);
                    return true;
                }
            });
        }
        return true;
    }

    private void modelView(){
        mainViewModel = new ViewModelProvider(MainActivity.this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        mainViewModel.getListUsers().observe(MainActivity.this, new Observer<ArrayList<UserItems>>() {
            @Override
            public void onChanged(ArrayList<UserItems> userItems) {
                if (userItems != null){
                    userAdapter.setData(userItems);
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
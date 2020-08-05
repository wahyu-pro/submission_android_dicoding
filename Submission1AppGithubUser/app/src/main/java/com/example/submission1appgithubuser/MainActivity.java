package com.example.submission1appgithubuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvUser;
    private ArrayList<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvUser = findViewById(R.id.rv_user);
        rvUser.setHasFixedSize(true);

        list.addAll(UserData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList() {
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        ListUserAdapter listUserAdapter = new ListUserAdapter(list);
        rvUser.setAdapter(listUserAdapter);

        listUserAdapter.setOnItemClickCallback(new ListUserAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(User data) {
                showSelectedUser(data);
            }
        });
    }

    private void showSelectedUser(User user) {
//        Toast.makeText(this, "Kamu memilih " + user.getName(), Toast.LENGTH_SHORT).show();
        Intent moveWithObjectIntent = new Intent(MainActivity.this, DetailUser.class);
        moveWithObjectIntent.putExtra(DetailUser.EXTRA_PERSON, user);
        startActivity(moveWithObjectIntent);
    }
}

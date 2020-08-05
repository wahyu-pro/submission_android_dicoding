package com.example.submission1appgithubuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailUser extends AppCompatActivity {

    public static final String EXTRA_PERSON = "extra_person";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        TextView tvRepo = findViewById(R.id.tv_repository);
        TextView tvUsername = findViewById(R.id.tv_username);
        TextView tvCompany = findViewById(R.id.tv_company);
        TextView tvLocation = findViewById(R.id.tv_location);
        TextView tvFollower = findViewById(R.id.tv_follower);
        TextView tvFollowing = findViewById(R.id.tv_following);
        ImageView imageView = (ImageView) findViewById(R.id.photo_detail);

        User user = getIntent().getParcelableExtra(EXTRA_PERSON);
        String text = "Name : " + user.getName();
        imageView.setImageDrawable(getResources().getDrawable(user.getPhoto()));
        tvRepo.setText("Repository : " + user.getRepository());
        tvFollower.setText("Follower : " + user.getFollower());
        tvFollowing.setText("Following : " + user.getFollowing());
        tvUsername.setText("Username : " + user.getUsername());
        tvCompany.setText("Company : " + user.getCompany());
        tvLocation.setText("Location : " + user.getLocation());
    }
}

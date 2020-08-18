package com.wahyu.submission2githubapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.wahyu.submission2githubapp.R;
import com.wahyu.submission2githubapp.adapters.SectionsPagerAdapter;
import com.wahyu.submission2githubapp.adapters.UserAdapter;
import com.wahyu.submission2githubapp.models.DetailUserModel;
import com.wahyu.submission2githubapp.models.UserItems;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DetailUserActivity extends AppCompatActivity {

    public static final String EXTRA_PERSON = "extra_person";
    ImageView avatar;
    TextView name, username, about, company, repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Detail User");
        }

        // get username
        final UserItems user = getIntent().getParcelableExtra(EXTRA_PERSON);

        // Request for detail user
        if (user != null) {
            getDetailUser(user.getUsername());
        }

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        sectionsPagerAdapter.username = user.getUsername();
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        getSupportActionBar().setElevation(0);
    }

    public void getDetailUser(String username){
        String apiKey = "c83462f5908d12d210b40d1de34cb0d1e9ed544f";
        String url = "https://api.github.com/users/" + username;
        AsyncHttpClient client = new AsyncHttpClient();

        client.addHeader("Authorization", "token " + apiKey);
        client.addHeader("User-Agent", "request");
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                Log.d("Result", result);
                try {
                    //parsing json
                    JSONObject responseObject = new JSONObject(result);
                    String username = responseObject.getString("login");
                    Log.d("login", username);
                    String name = responseObject.getString("name");
                    String avatar = responseObject.getString("avatar_url");
                    String company = responseObject.getString("company");
                    String location = responseObject.getString("location");
                    String repository = responseObject.getString("public_repos");
                    DetailUserModel detail = new DetailUserModel();
                    detail.setName(name);
                    detail.setUsername(username);
                    detail.setAvatar(avatar);
                    detail.setCompany(company);
                    detail.setLocation(location);
                    detail.setRepository(repository);
                    setView(detail);

                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    private void setView(DetailUserModel detail) {
        avatar = findViewById(R.id.avatar);
        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        about = findViewById(R.id.about);

        username.setText(detail.getUsername());
        name.setText(detail.getName());

        String info = detail.getCompany()+" | "+detail.getLocation()+" | "+detail.getRepository();
        about.setText(info);
        // load image from url
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(this).load(detail.getAvatar()).apply(options).into(avatar);
    }
}
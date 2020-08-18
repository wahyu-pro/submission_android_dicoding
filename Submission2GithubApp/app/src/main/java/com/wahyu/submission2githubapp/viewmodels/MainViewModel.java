package com.wahyu.submission2githubapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.wahyu.submission2githubapp.models.UserItems;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {

    private MutableLiveData<ArrayList<UserItems>> listUsers = new MutableLiveData<>();

    public LiveData<ArrayList<UserItems>> getListUsers() {
        return listUsers;
    }

    public void setListUsers(final String username) {
        final ArrayList<UserItems> listItems = new ArrayList<>();

        String apiKey = "c83462f5908d12d210b40d1de34cb0d1e9ed544f";
        String url = "https://api.github.com/search/users?q="+username;

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "token "+apiKey);
        client.addHeader("User-Agent", "request");
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                Log.d("Result", result);
                try {
                    //parsing json
                    JSONObject responObject = new JSONObject(result);
                    JSONArray items = responObject.getJSONArray("items");

                    for (int i = 0; i < items.length(); i++){
                        JSONObject item = items.getJSONObject(i);
                        String username = item.getString("login");
                        String avatar = item.getString("avatar_url");
                        UserItems user = new UserItems();
                        user.setUsername(username);
                        user.setAvatar(avatar);
                        listItems.add(user);
                    }
                    // set adapter
                    listUsers.postValue(listItems);
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
}

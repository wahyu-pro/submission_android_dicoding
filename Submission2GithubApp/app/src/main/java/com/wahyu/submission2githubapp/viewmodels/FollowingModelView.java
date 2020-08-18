package com.wahyu.submission2githubapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.wahyu.submission2githubapp.models.OthersModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class FollowingModelView extends ViewModel {
    private MutableLiveData<ArrayList<OthersModel>> listFollowing = new MutableLiveData<>();

    public LiveData<ArrayList<OthersModel>> getListFollower() {
        return listFollowing;
    }

    public void setListFollower(final String username) {
        final ArrayList<OthersModel> listItems = new ArrayList<>();

        String apiKey = "c83462f5908d12d210b40d1de34cb0d1e9ed544f";
        String url = "https://api.github.com/users/"+ username +"/following";

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "token "+apiKey);
        client.addHeader("User-Agent", "request");
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                Log.d("Result", result);
                try {
                    JSONArray responseArray = new JSONArray(result);
                    for (int i = 0; i < responseArray.length(); i++){
                        JSONObject responObject = responseArray.getJSONObject(i);
                        String username = responObject.getString("login");
                        String avatar = responObject.getString("avatar_url");
                        OthersModel followers = new OthersModel();
                        followers.setUsername(username);
                        followers.setAvatar(avatar);
                        listItems.add(followers);
                    }
                    listFollowing.postValue(listItems);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                error.printStackTrace();
            }
        });
    }
}

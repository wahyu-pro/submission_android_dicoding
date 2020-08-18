package com.wahyu.submission2githubapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class UserItems implements Parcelable {
    private String username;
    private String avatar;

    public UserItems() {
    }

    protected UserItems(Parcel in) {
        username = in.readString();
        avatar = in.readString();
    }

    public static final Creator<UserItems> CREATOR = new Creator<UserItems>() {
        @Override
        public UserItems createFromParcel(Parcel in) {
            return new UserItems(in);
        }

        @Override
        public UserItems[] newArray(int size) {
            return new UserItems[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(avatar);
    }
}

package com.techyourchance.journeytodependencyinjection.networking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Stephen Gemin on 9/10/2019
 */
public class UserSchema {

    @SerializedName("display_name")
    private final String mUserDisplayName;

    @SerializedName("profile_image")
    private final String mUserAvatarUrl;

    public UserSchema(String UserDisplayName, String userAvatarUrl) {
        mUserDisplayName = UserDisplayName;
        mUserAvatarUrl = userAvatarUrl;
    }

    public String getmUserDisplayName() {
        return mUserDisplayName;
    }

    public String getmUserAvatarUrl() {
        return mUserAvatarUrl;
    }
}

package com.techyourchance.journeytodependencyinjection.questions;

import com.google.gson.annotations.SerializedName;

public class QuestionDetails {

    private final String mId;
    private final String mTitle;
    private final String mBody;
    private final String mUserDisplayName;
    private final String mUserAvatarUrl;

    public QuestionDetails(String title, String id, String body,
                           String userDisplayName, String userAvatarUrl) {
        mTitle = title;
        mId = id;
        mBody = body;
        mUserDisplayName = userDisplayName;
        mUserAvatarUrl = userAvatarUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getId() {
        return mId;
    }

    public String getBody() {
        return mBody;
    }

    public String getmUserDisplayName() {
        return mUserDisplayName;
    }

    public String getmUserAvatarUrl() {
        return mUserAvatarUrl;
    }
}

package com.example.diceandload.ui.login;

import com.facebook.AccessToken;

public class SmartFacebookUser extends SmartUser {
    private String profileName;
    private AccessToken accessToken;

    public SmartFacebookUser() {
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
}

package com.example.diceandload.ui.login;

public interface SmartLoginCallbacks {

    void onLoginSuccess(SmartUser user);

    void onLoginFailure(SmartLoginException e);

    SmartUser doCustomLogin();

    SmartUser doCustomSignup();
}

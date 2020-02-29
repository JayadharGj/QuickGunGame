package com.example.diceandload.ui.login;

public class SmartLoginFactory {
    public static SmartLogin build(LoginType loginType) {
        switch (loginType) {
            case Facebook:
                return new FacebookLogin();
            case Google:
                return new GoogleLogin();
            case CustomLogin:
                return new CustomLogin();
            default:
                // To avoid null pointers
                return new CustomLogin();
        }
    }
}

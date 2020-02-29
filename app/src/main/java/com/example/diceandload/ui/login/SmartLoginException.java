package com.example.diceandload.ui.login;

public class SmartLoginException extends Exception {
    private LoginType loginType;

    public SmartLoginException(String message, LoginType loginType) {
        super(message);
        this.loginType = loginType;
    }

    public SmartLoginException(String message, Throwable cause, LoginType loginType) {
        super(message, cause);
        this.loginType = loginType;
    }

    public LoginType getLoginType() {
        return loginType;
    }
}

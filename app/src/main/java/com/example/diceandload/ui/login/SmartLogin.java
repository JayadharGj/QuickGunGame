package com.example.diceandload.ui.login;

import android.content.Context;
import android.content.Intent;

public abstract class SmartLogin {

    public abstract void login(SmartLoginConfig config);

    public abstract void signup(SmartLoginConfig config);

    public abstract boolean logout(Context context);

    public abstract void onActivityResult(int requestCode, int resultCode, Intent data, SmartLoginConfig config);

}

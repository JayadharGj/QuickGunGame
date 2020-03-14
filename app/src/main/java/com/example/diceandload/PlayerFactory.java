package com.example.diceandload;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class PlayerFactory {
    private WeakReference<AppCompatActivity> mContext;
    private int mPlayers;
    private int mMode;

    GameContract getGame(AppCompatActivity context, @KeyConstants.GameMode int mode, int noOfPlayers, PlayerViewModel model, int gunManCount) {
        mContext = new WeakReference<>(context);
        mMode = mode;
        mPlayers = noOfPlayers;
        if (mode == KeyConstants.CLASSIC) {
            return new ClassicGame(noOfPlayers, model, gunManCount);
        } else if (mode == KeyConstants.STRATEGY) {
            return new StrategyGame();
        } else {
            return null;
        }
    }

    public void create() {

    }
}

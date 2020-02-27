package com.example.diceandload;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerFactory {
    AppCompatActivity mContext;
    int mPlayers;
    int mMode;

    public GameContract getGame(AppCompatActivity context, @KeyConstants.GameMode int mode, int noOfPlayers) {
        mContext = context;
        mMode = mode;
        mPlayers = noOfPlayers;
        if (mode == KeyConstants.CLASSIC) {
            return new ClassicGame();
        } else if (mode == KeyConstants.STRATEGY) {
            return new StrategyGame();
        } else {
            return null;
        }
    }

    public void create() {

    }
}

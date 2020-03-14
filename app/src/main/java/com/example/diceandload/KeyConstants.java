package com.example.diceandload;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class KeyConstants {
    public static final String REQUESTED_PLAYRES = "requestedPlayers";
    public static final int CLASSIC = 1;
    public static final int STRATEGY = 2;
//    public  enum GameMode {
//        CLASSIC("classic" , 0),
//        STRATEGY("strategy" , 1);
//    }
    @IntDef({CLASSIC , STRATEGY})
    @Retention(RetentionPolicy.RUNTIME)
@interface GameMode{}

    static final String GAME_MODE = "gamemode";

}

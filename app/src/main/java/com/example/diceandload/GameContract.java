package com.example.diceandload;

abstract class GameContract {

    abstract void attachViews();

    abstract void setCurrPlayer(int currPlayer);

    abstract void rollDice();

    abstract void play(int value);

    abstract void killPlayer(int playerId, int hisGunMan);

}

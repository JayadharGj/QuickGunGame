package com.example.diceandload;

abstract class GameContract {

    abstract void attachViews();

    abstract  void rollDice();

    abstract void killPlayer(int playerId , int hisGunMan);

}

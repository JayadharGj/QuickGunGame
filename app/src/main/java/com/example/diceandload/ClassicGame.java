package com.example.diceandload;

import java.util.Random;

class ClassicGame extends GameContract {

    private int noOfPlayers;
    private int currPlayer;
    private PlayerViewModel mViewModel;
    private int eachPlayerGunManCount;
    Random randomGen = new Random();

    ClassicGame(int noOfPlayers, PlayerViewModel model, int gunManCount) {
        if (model.getPlayers() != null) model.setPlayers(new Player[noOfPlayers] , gunManCount);
        currPlayer = 1;
        this.noOfPlayers = noOfPlayers;
        eachPlayerGunManCount = gunManCount;
        mViewModel = model;
    }

    @Override
    void attachViews() {

    }

    //need to be called from Ui when dice is rolled
    void play(int dice) {
        mViewModel.getPlayers()[currPlayer].levelUp(dice);
    }

    @Override
    void rollDice() {
        mViewModel.getDice().setValue(randomGen.nextInt() % 5 + 1);
    }

    @Override
    void killPlayer(int playerId , int hisGunMan) {

    }
}

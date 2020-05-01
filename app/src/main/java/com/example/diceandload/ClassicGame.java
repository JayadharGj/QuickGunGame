package com.example.diceandload;

import android.util.Pair;

import java.util.Random;

class ClassicGame extends GameContract {

    private int noOfPlayers;
    private int currPlayer;
    private PlayerViewModel mViewModel;
    private int eachPlayerGunManCount;
    private Random randomGen = new Random();

    ClassicGame(int noOfPlayers, PlayerViewModel model, int gunManCount) {
        if (model.getPlayers() == null) model.setPlayers(new Player[noOfPlayers], gunManCount);
        currPlayer = 0;
        this.noOfPlayers = noOfPlayers;
        eachPlayerGunManCount = gunManCount;
        mViewModel = model;
    }

    @Override
    void attachViews() {

    }

    @Override
    public void setCurrPlayer(int currPlayer) { // also implies that view was updated.
        this.currPlayer = currPlayer;
    }

    //need to be called from Ui when dice is rolled
    void play(int dice) {

        int val = mViewModel.getPlayer(currPlayer).getGunMans().get(dice);
        if (val == 0) {
            //dead (X__X) already, go to next.
            mViewModel.getCurrentPlayer().setValue(getNextPLayer());
        } else if (val < 3) {
            mViewModel.getPlayer(currPlayer).levelUp(dice);
            mViewModel.getUpdateUi().setValue(Pair.create(currPlayer, dice));
            mViewModel.getCurrentPlayer().setValue(getNextPLayer());
        } else {//bullet mode can select other player views not himself
           mViewModel.getkiller().setValue(currPlayer);
        }
        //  mViewModel.getPlayer(currPlayer).levelUp(dice);
    }

    private int getNextPLayer() {
        int x;
        do {
            x = (currPlayer + 1) % noOfPlayers;
        }
        while (mViewModel.getPlayer(x).isPlayerDead() && (x != currPlayer));
        if (x == currPlayer) mViewModel.signalEndGame();
        return x;
    }
    void levelTransition(int val) {
        switch (val) {
            case 1:

        }

    }


    @Override
    void rollDice() {
        mViewModel.getDice().setValue(randomGen.nextInt(6) + 1);
    }

    @Override
    void killPlayer(int playerId, int hisGunMan) {
        if (mViewModel.getPlayer(playerId).isGunManDead(playerId)) {//already dead

        } else {
            mViewModel.getPlayer(playerId).kill(hisGunMan);
            mViewModel.getUpdateUi().setValue(Pair.create(playerId, hisGunMan));
            if (currPlayer == getNextPLayer()) return;
            mViewModel.getCurrentPlayer().setValue(currPlayer);//can kill again hurray!!
        }
    }
}

package com.example.diceandload;

import android.util.Pair;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class PlayerViewModel extends ViewModel {
    private MutableLiveData<Integer> dice;
    private Player[] mPlayers;
    private MutableLiveData<Integer> currentPlayer;
    private MutableLiveData<Pair<Integer, Integer>> updateUi;
    private MutableLiveData<Integer> killer;
    private MutableLiveData<String> endTheGame;

    public PlayerViewModel() {

    }


    MutableLiveData<Integer> getDice() { //observe to set dice value
        if (dice == null) dice = new MutableLiveData<>(-1);
        return dice;
    }

    Player[] getPlayers() {
        return mPlayers;
    }

    Player getPlayer(int id) {
        return mPlayers[id];
    }

    MutableLiveData<Pair<Integer, Integer>> getUpdateUi() {
        if (updateUi == null) {
            updateUi = new MutableLiveData<>(Pair.create(0, 0));
        }
        return updateUi;
    }

    MutableLiveData<Integer> getkiller() {
        if (killer == null) {
            killer = new MutableLiveData<>(-1);
        }
        return killer;
    }

    void setPlayers(Player[] players, int gunManCount) {
        this.mPlayers = players;
        for (int i = 0; i < mPlayers.length; i++) {
            mPlayers[i] = new Player(i, gunManCount, new ArrayList<>(gunManCount));
            mPlayers[i].initGunMans(1);//set all gunMan states to 1
        }
    }

    void signalEndGame() {
        getEndTheGame().setValue("YES");
    }

    MutableLiveData<String> getEndTheGame() {
        if(endTheGame == null){
            endTheGame = new MutableLiveData<>("");
        }
        return endTheGame;
    }

    MutableLiveData<Integer> getCurrentPlayer() {
        if (currentPlayer == null) currentPlayer = new MutableLiveData<>(0);
        return currentPlayer;
    }


}

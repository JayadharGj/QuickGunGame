package com.example.diceandload;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

class PlayerViewModel extends ViewModel {
    private MutableLiveData<Integer> dice;
    private Player[] players;
    private MutableLiveData<Integer> currentPlayer;


    MutableLiveData<Integer> getDice() { //observe to set dice value
        if (dice == null) dice = new MutableLiveData<>(1);
        return dice;
    }

    Player[] getPlayers() {
        return players;
    }

    void setPlayers(Player[] players, int gunManCount) {
        this.players = players;
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(i + 1, gunManCount, new ArrayList<Integer>(gunManCount));
            players[i].initGunMans(1);//set all gunMan states to 1
        }
    }


    MutableLiveData<Integer> getCurrentPlayer() {
        if (currentPlayer == null) currentPlayer = new MutableLiveData<>(1);
        return currentPlayer;
    }
}

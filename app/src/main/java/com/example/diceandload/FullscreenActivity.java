package com.example.diceandload;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import static com.example.diceandload.KeyConstants.GAME_MODE;
import static com.example.diceandload.KeyConstants.REQUESTED_PLAYRES;

public class FullscreenActivity extends AppCompatActivity {

    player player;
    PlayerFactory playerFactory;
    GameContract gameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        Objects.requireNonNull(getSupportActionBar()).hide();
        int noOfPlayers = getIntent().getIntExtra(REQUESTED_PLAYRES, 2);
        int gameMode = getIntent().getIntExtra(GAME_MODE, 1);
        createPlayerField(noOfPlayers, gameMode);

    }

    void createPlayerField(int noOfplayers, @KeyConstants.GameMode int gameMode) {
        switch (noOfplayers) {
            case 2:
                findViewById(R.id.player3).setVisibility(View.GONE);

            case 3:
                findViewById(R.id.player4).setVisibility(View.GONE);
        }
        playerFactory = new PlayerFactory();
        gameController = playerFactory.getGame(this, gameMode, noOfplayers);


    }


}

package com.example.diceandload;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.diceandload.KeyConstants.GAME_MODE;
import static com.example.diceandload.KeyConstants.REQUESTED_PLAYRES;

public class FullscreenActivity extends AppCompatActivity {

    Player player;
    PlayerFactory playerFactory;
    GameContract gameController;
    PlayerViewModel playerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        int noOfPlayers = getIntent().getIntExtra(REQUESTED_PLAYRES, 2);
        int gameMode = getIntent().getIntExtra(GAME_MODE, 1);
        createPlayerField(noOfPlayers, gameMode);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    void createPlayerField(int noOfplayers, @KeyConstants.GameMode int gameMode) {
        switch (noOfplayers) {
            case 2:
                findViewById(R.id.player3).setVisibility(View.GONE);

            case 3:
                findViewById(R.id.player4).setVisibility(View.GONE);
        }
//        playerFactory = new PlayerFactory();
//        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
//        gameController = playerFactory.getGame(this, gameMode, noOfplayers, playerViewModel, 6);

        // add onclick listeners for dice
    }


}

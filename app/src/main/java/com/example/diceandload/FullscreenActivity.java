package com.example.diceandload;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import static com.example.diceandload.KeyConstants.REQUESTED_PLAYRES;

public class FullscreenActivity extends AppCompatActivity {

player player;
PlayerFactory playerFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        Objects.requireNonNull(getSupportActionBar()).hide();
        int noOfPlayers = getIntent().getIntExtra(REQUESTED_PLAYRES,2);
        createPlayerField(noOfPlayers);

    }
    void createPlayerField(int noOfplayers)
    {
        switch (noOfplayers) {
            case 2:
                findViewById(R.id.player3).setVisibility(View.GONE);

            case 3:
                findViewById(R.id.player4).setVisibility(View.GONE);
        }
       playerFactory =  new PlayerFactory(noOfplayers);



    }


}

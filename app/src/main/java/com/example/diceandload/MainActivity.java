package com.example.diceandload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import static com.example.diceandload.KeyConstants.REQUESTED_PLAYRES;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button mButtonOne;
    Button mButtonTwo;
    Button mButtonThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachViewListenrs();

    }

    public void attachViewListenrs() {
        mButtonOne = findViewById(R.id.one);
        mButtonOne.setOnClickListener(this);
        mButtonTwo = findViewById(R.id.two);
        mButtonTwo.setOnClickListener(this);
        mButtonThree = findViewById(R.id.three);
        mButtonThree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                lockAndstartGame(2);
                break;
            case R.id.two:
                lockAndstartGame(3);
                break;
            case R.id.three:
                lockAndstartGame(4);
                break;
        }
    }

    void lockAndstartGame(int requestedPlayers) {
        lockUi(true);
        Intent intent = new Intent(this, FullscreenActivity.class);
        intent.putExtra(REQUESTED_PLAYRES, requestedPlayers);
        startActivity(intent);
    }

    void lockUi(boolean key) {
        mButtonOne.setClickable(!key);
        mButtonTwo.setClickable(!key);
        mButtonThree.setClickable(!key);
    }

    @Override
    protected void onStop() {
        super.onStop();
        lockUi(false);
    }
}

package com.example.diceandload;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.spark.submitbutton.SubmitButton;

import static com.example.diceandload.KeyConstants.REQUESTED_PLAYRES;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SubmitButton mButtonOne;
    SubmitButton mButtonTwo;
//    Button mButtonThree;
    private static int gameLevel=-1;
    private static int pCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachViewListeners();

    }

    public void attachViewListeners() {
        mButtonOne = findViewById(R.id.classic);
        mButtonOne.setOnClickListener(this);
        mButtonTwo = findViewById(R.id.strategy);
        mButtonTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        int gameLevel=-1;
        switch (v.getId()) {
            case R.id.classic:
                gameLevel=0;
                break;
            case R.id.strategy:
                gameLevel=1;
                break;
        }

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        PlayerSelectionFragment playerSelectionFragment = PlayerSelectionFragment.newInstance();
        fragmentTransaction.replace(R.id.buttonPanel,playerSelectionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    void playerCount(int count){
        pCount = count;
        lockAndstartGame(gameLevel,pCount);
    }
    void lockAndstartGame(int gameLevel,int requestedPlayers) {
        lockUi(true);
        Intent intent = new Intent(this, FullscreenActivity.class);
        intent.putExtra(REQUESTED_PLAYRES, requestedPlayers);
        startActivity(intent);
    }

    void lockUi(boolean key) {
        mButtonOne.setClickable(!key);
        mButtonTwo.setClickable(!key);
    }

    @Override
    protected void onStop() {
        super.onStop();
        lockUi(false);
    }
}

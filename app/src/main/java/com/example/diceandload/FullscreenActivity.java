package com.example.diceandload;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.diceandload.ui.login.TouchEnableDisableLinearLayout;

import java.util.Locale;

import static com.example.diceandload.KeyConstants.GAME_MODE;
import static com.example.diceandload.KeyConstants.REQUESTED_PLAYRES;

public class FullscreenActivity extends AppCompatActivity {

    PlayerFactory playerFactory;
    GameContract gameController;
    PlayerViewModel playerViewModel;
    TextView mDiceView;
    TouchEnableDisableLinearLayout mPLayerField;
    ImageView[][] imageViews;
    TouchEnableDisableLinearLayout[] mPlayerRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        int noOfPlayers = getIntent().getIntExtra(REQUESTED_PLAYRES, 2);
        int gameMode = getIntent().getIntExtra(GAME_MODE, 1);
        createPlayerField(noOfPlayers, gameMode);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //called once from oncreate
    void createPlayerField(int noOfplayers, @KeyConstants.GameMode int gameMode) {

        mPlayerRows = new TouchEnableDisableLinearLayout[noOfplayers];
        mPlayerRows[0] = findViewById(R.id.player1);
        mPlayerRows[1] = findViewById(R.id.player2);
        switch (noOfplayers) {
            case 2:
                findViewById(R.id.player3).setVisibility(View.GONE);
                findViewById(R.id.player4).setVisibility(View.GONE);
                break;
            case 3:
                mPlayerRows[2] = findViewById(R.id.player3);
                findViewById(R.id.player4).setVisibility(View.GONE);
                break;
            case 4:
                mPlayerRows[2] = findViewById(R.id.player3);
                mPlayerRows[3] = findViewById(R.id.player4);
        }
        playerFactory = new PlayerFactory();
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        gameController = playerFactory.getGame(this, gameMode, noOfplayers, playerViewModel, 6);
        mDiceView = findViewById(R.id.dice_view);
        mPLayerField = findViewById(R.id.playerField);

        mDiceView.setOnClickListener(view -> {
            mDiceView.setClickable(false);
            gameController.rollDice();
        });
        getViewIds(noOfplayers, 6);
        playerViewModel.getDice().observe(this, value -> {
            if (value > 0) {
                mDiceView.setText(String.format(Locale.ENGLISH, "%d", value));
                gameController.play(value - 1);
                mDiceView.setTextColor(getColor(R.color.colorAccent));
            }
        });
        playerViewModel.getUpdateUi().observe(this, idRow -> {
            //update UI according to callbacks
            imageViews[idRow.first][idRow.second].setImageResource(getImageResource(playerViewModel.getPlayer(idRow.first).getGunMans().get(idRow.second)));
        });
        playerViewModel.getkiller().observe(this, killer -> {
            if (killer != -1) mPLayerField.setEnable(true);
            for (int i = 0; i < noOfplayers; i++) {
                if (i == killer) mPlayerRows[i].setEnable(false);
                else mPlayerRows[i].setEnable(true);
            }
        });

        playerViewModel.getCurrentPlayer().observe(this, currPlayer -> {
            //player changed
            if (currPlayer >= 0) {
                gameController.setCurrPlayer(currPlayer);
                mPLayerField.setEnable(false);
                //  mPLayerField.setClickable(false);
                new Handler(getMainLooper()).postDelayed(() -> {
                    for (int i = 0; i < noOfplayers; i++) {
                        if (currPlayer == i)
                            mPlayerRows[i].setBackgroundColor(getColor(R.color.accent_color));
                        else if (playerViewModel.getPlayer(i).isPlayerDead())
                            mPlayerRows[i].setBackgroundColor(getColor(R.color.dead_red));
                        else mPlayerRows[i].setBackgroundColor(getColor(R.color.black));
                    }
                    mDiceView.setClickable(true);
                    mDiceView.setTextColor(getColor(R.color.primary_color));
                }, 1500);
            }
        });

        playerViewModel.getEndTheGame().observe(this, shouldWeEndTheGame -> {
            if (shouldWeEndTheGame.equals("YES")) {
                mDiceView.setText("W\\^O^/N");
                mDiceView.setClickable(false);
                mPLayerField.setClickable(false);
            }
        });

    }

    //yo recreate safe
    void getViewIds(int noOfplayers, int gunManPerPlayer) {
        imageViews = new ImageView[noOfplayers][gunManPerPlayer];
        LinearLayout layoutMain = findViewById(R.id.playerField);
        int id = 0, col = 0;

        for (int i = 0; i < layoutMain.getChildCount(); i++) {
            View sublayout = layoutMain.getChildAt(i);

            if (sublayout.getVisibility() == View.GONE) continue;

            if (sublayout instanceof LinearLayout) {
                for (int j = 0; j < ((LinearLayout) sublayout).getChildCount(); j++) {
                    View v = ((LinearLayout) sublayout).getChildAt(j);
                    if (v instanceof ImageView) {
                        if (col == 6) {
                            col = 0;
                            id++;
                        }
                        imageViews[id][col] = (ImageView) v;
                        imageViews[id][col].setImageResource(getImageResource(playerViewModel.getPlayer(id).getGunMans().get(col)));
                        setOnClickImageListener((ImageView) v, id, col);
                        col++;
                    }
                }
            }
        }
    }

    int getImageResource(int x) {
        switch (x) {
            case 3:
                return R.mipmap.licenced;
            case 2:
                return R.mipmap.baby;
            case 1:
                return R.mipmap.born;
            case 0:
                return R.mipmap.dead;
        }
        return R.mipmap.licenced; // i,e he's on fire 3+
    }

    void setOnClickImageListener(ImageView imageView, int id, int col) {
        imageView.setOnClickListener(v -> {
            mPLayerField.setEnable(!gameController.killPlayer(id, col));//disable Views
        });
    }
}

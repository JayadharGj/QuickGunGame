package com.example.diceandload;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class PlayerSelectionFragment extends Fragment implements View.OnClickListener{

    private PlayerSelectionViewModel mViewModel;

    public static PlayerSelectionFragment newInstance() {
        return new PlayerSelectionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.player_selection_fragment, container, false);
        final MainActivity mainActivity = (MainActivity) getActivity();
        Button button = (Button)  v.findViewById(R.id.two);
        Button button1 = (Button)  v.findViewById(R.id.three);
        Button button2 = (Button)  v.findViewById(R.id.four);

        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        // implements your things
        MainActivity mainActivity = (MainActivity) getActivity();
        switch (v.getId()){
            case R.id.two:
                mainActivity.playerCount(2);
                break;
            case R.id.three:
                mainActivity.playerCount(3);
                break;
            case R.id.four:
                mainActivity.playerCount(4);
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlayerSelectionViewModel.class);
        // TODO: Use the ViewModel
    }

}

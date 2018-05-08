package com.example.jobedylbas.genius.GameMVP.Presenter;

import android.view.View;

import java.util.List;

/**
 * Created by jobedylbas on 07/05/18.
 */

public interface GamePresenterInterface {
    public void setModel(List<Integer> btn_ids);
    public void onCreate(String game_diff);
    public void newRound();
}

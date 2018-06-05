package com.example.jobedylbas.genius.ui.game;

import android.view.View;

import java.util.List;

/**
 * Created by jobedylbas on 07/05/18.
 */

public interface GamePresenterInterface {
    public void setModel(List<Integer> btn_ids, Integer diff);
    public void newGame(Integer diff);
    public void newRound();
    public void checkButton(Integer btn_id);
}
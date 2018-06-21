package com.example.jobedylbas.genius.ui.game;

import android.view.View;

import java.util.List;

/**
 * Created by jobedylbas on 07/05/18.
 */

public interface GamePresenterInterface {
    void setModel(List<Integer> btn_ids);
    void newGame(Integer diff);
    void newRound();
    void checkButton(Integer btn_id);
}

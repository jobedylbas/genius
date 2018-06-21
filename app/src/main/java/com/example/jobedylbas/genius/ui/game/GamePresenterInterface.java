package com.example.jobedylbas.genius.ui.game;

import android.view.View;

import java.util.List;

/**
 * Created by jobedylbas on 07/05/18.
 */

public interface GamePresenterInterface {
    void newGame(Integer diff);
    void newRound();
    void endGame();
    void checkButton(Integer btn_id);
    void resetGame();
    boolean saveRecord(String player_name);
}

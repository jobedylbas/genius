package com.example.jobedylbas.genius.ui.game;

import android.content.Context;

import com.example.jobedylbas.genius.utils.SimonButton;

import java.util.List;
import java.util.Queue;

/**
 * Created by jobedylbas on 07/05/18.
 */

public interface GameViewInterface {
    void setLayout(Integer game_diff);
    void playSeq(Queue<Integer> btn_ids) throws InterruptedException;
    void setPoints(Integer points);
    void newRecord();
    Integer getDifficulty();
    Context getViewContext();
    void gameOver(Integer score);
}

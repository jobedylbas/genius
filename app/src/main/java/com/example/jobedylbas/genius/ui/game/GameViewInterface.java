package com.example.jobedylbas.genius.ui.game;

import java.util.List;
import java.util.Queue;

/**
 * Created by jobedylbas on 07/05/18.
 */

public interface GameViewInterface {
    public void setLayout(Integer game_diff);
    public void playSeq(Queue<Integer> btn_ids) throws InterruptedException;
}

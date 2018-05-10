package com.example.jobedylbas.genius.GameMVP.Model;

import java.util.Queue;

/**
 * Created by jobedylbas on 06/05/18.
 */

public interface GameModelInterface {
    Integer getLastBtnSqnSize();
    void newBtnSeq();
    Queue<Integer> getBtnSeq();
    Boolean checkBtn(Integer btn_id);
}

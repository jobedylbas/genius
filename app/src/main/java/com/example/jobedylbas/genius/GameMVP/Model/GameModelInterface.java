package com.example.jobedylbas.genius.GameMVP.Model;

import java.util.Queue;

/**
 * Created by jobedylbas on 06/05/18.
 */

public interface GameModelInterface {
    Integer getSqnSize();
    void newBtnSeq();
    Queue<Integer> getBtnSeq();
    boolean checkBtn(Integer btn_id);
    boolean isEmptySqe();
    void resetModel();
}

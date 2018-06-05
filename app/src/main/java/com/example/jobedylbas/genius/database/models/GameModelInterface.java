package com.example.jobedylbas.genius.database.models;

import java.util.Queue;

/**
 * Created by jobedylbas on 06/05/18.
 */

public interface GameModelInterface {
    Integer getSeqSize();
    void newBtnSeq();
    Queue<Integer> getBtnSeq();
    boolean checkBtn(Integer btn_id);
    boolean isEmptySeq();
    void resetModel();
}

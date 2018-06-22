package com.example.jobedylbas.genius.database.models;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Created by jobedylbas on 02/05/18.
 * Class game is abstract class that has the basic function of
 * the game Genius like create sequence of colors
 */

public class GameModel {
    private final static String TAG = GameModel.class.getName();
    private final static Queue<Integer> EMPTY_LIST = new LinkedList<>();
    private final static Integer ZERO = 0;
    private Queue<Integer> btn_seq;
    private Queue<Integer> aux_seq;
    private List<Integer> btn_available;
    private Integer seq_size;

    public GameModel(List<Integer> btn_available) {
        this.btn_seq = EMPTY_LIST;
        this.aux_seq = EMPTY_LIST;
        this.btn_available = btn_available;
        this.seq_size = ZERO;
    }

    // Return the size of the button sequence
    public Integer getSeqSize() { return seq_size; }

    public Queue<Integer> getBtnSeq(){ return btn_seq; }

    // Create a new button sequence
    public void newBtnSeq() {
        Random rand = new Random();
        this.seq_size = aux_seq.size();
        this.btn_seq = aux_seq;
        Integer btn = btn_available.get(rand.nextInt(btn_available.size()));
        this.btn_seq.add(btn);
        this.aux_seq = new LinkedList<>();
    }

    // Check if the button is equal the head of the button queue
    public boolean checkBtn(Integer btn_id) {
        if (btn_seq.element().equals(btn_id)) {
            Log.d(TAG, "Right Button");
            this.aux_seq.add(btn_seq.poll());
            return true;
        }
        else {
            Log.d(TAG, "Wrong Button");
            return false;
        }
    }

    public boolean isEmptySeq(){
        return btn_seq.isEmpty();
    }

    public void resetModel(){
        this.btn_seq = EMPTY_LIST;
        this.aux_seq = EMPTY_LIST;
        this.seq_size = ZERO;
    }

}
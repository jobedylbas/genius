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

public class GameModel implements GameModelInterface {
    private Queue<Integer> backup_seq;
    private Queue<Integer> btn_seq;
    private Queue<Integer> last_seq;
    private List<Integer> btn_available;
    private Integer seq_size;
    private Integer difficulty;

    public GameModel(List<Integer> btn_available, Integer difficulty) {
        this.seq_size = 0;
        this.backup_seq = new LinkedList<>();
        this.btn_seq = new LinkedList<>();
        this.last_seq = new LinkedList<>();
        this.btn_available = btn_available;
        this.difficulty = difficulty;
    }

    // Return the size of the button sequence
    public Integer getSeqSize() {
        return seq_size;}

    public Queue<Integer> getBtnSeq(){ return btn_seq; }

    // Create a new button sequence
    public void newBtnSeq() {
        Random rand = new Random();
        seq_size = last_seq.size();
        btn_seq = last_seq;
        Integer btn = btn_available.get(rand.nextInt(btn_available.size()));
        Log.d("Sorted", String.valueOf(btn));
        btn_seq.add(btn);
        backup_seq.add(btn);
        last_seq = new LinkedList<>();
    }

    // Check if the button is equal the head of the button queue
    public boolean checkBtn(Integer btn_id) {
        if (btn_seq.element().equals(btn_id)) {
            Log.d("Model/checkBtn", "Right Button");
            last_seq.add(btn_seq.poll());
            return true;
        }
        else {
            Log.d("Model/checkBtn", "Right Button");
            return false;
        }
    }

    public boolean isEmptySeq(){
        return btn_seq.isEmpty();
    }

    public void resetModel(){
        this.seq_size = 0;
        this.btn_seq = new LinkedList<>();
        this.last_seq = new LinkedList<>();
    }
}
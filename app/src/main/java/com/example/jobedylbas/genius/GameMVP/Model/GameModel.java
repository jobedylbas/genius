package com.example.jobedylbas.genius.GameMVP.Model;

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
    private Queue<Integer> btn_sqe;
    private Integer btn_sqe_size;
    private List<Integer> btn_available;

    public GameModel(List<Integer> btn_available) {
        this.btn_sqe = new LinkedList<>();
        this.btn_sqe_size = btn_sqe.size();
        this.btn_available = btn_available;
    }

    // Return the size of the button sequence
    public Integer getBtnSqnSize() { return this.btn_sqe_size; }

    public Queue<Integer> getBtnSqe(){ return this.btn_sqe; }

    // Create a new button sequence
    public void newBtnSeq() {
        Random rand = new Random();
        this.btn_sqe_size++;
        for (int i = 0; i < btn_sqe_size; i++) {
            this.btn_sqe.add(this.btn_available.get(rand.nextInt(this.btn_available.size())));
        }
    }

    // Check if the button is equal the head of the button queue
    public Boolean checkBtn(Integer btn_id) {
        if(btn_id == this.btn_sqe.element()){
            this.btn_sqe.remove();
            return Boolean.TRUE;
        }
        else return Boolean.FALSE;
    }
}
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
    private Queue<Integer> last_sqe;
    private List<Integer> btn_available;

    public GameModel(List<Integer> btn_available) {
        this.btn_sqe = new LinkedList<>();
        this.last_sqe = new LinkedList<>();
        this.btn_available = btn_available;
    }

    // Return the size of the button sequence
    public Integer getLastBtnSqnSize() { return btn_sqe.size()-1; }

    public Queue<Integer> getBtnSeq(){ return btn_sqe; }

    // Create a new button sequence
    public void newBtnSeq() {
        Random rand = new Random();
        btn_sqe = last_sqe;
        btn_sqe.add(btn_available.get(rand.nextInt(btn_available.size())));
        last_sqe = new LinkedList<>();
    }

    // Check if the button is equal the head of the button queue
    public Boolean checkBtn(Integer btn_id) {
        if(btn_id == btn_sqe.element()){
            last_sqe.add(btn_sqe.poll());
            return true;
        }
        else return false;
    }

    public Boolean isEmptySqe(){
        if(this.btn_sqe.isEmpty()){
            return true;
        }
        else return false;
    }
}
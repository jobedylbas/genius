package com.example.jobedylbas.genius.GameMVP.Presenter;

import android.util.Log;

import com.example.jobedylbas.genius.GameMVP.Model.GameModel;
import com.example.jobedylbas.genius.GameMVP.View.GameView;

import java.util.List;
import java.util.Queue;

/**
 * Created by jobedylbas on 07/05/18.
 */

public class GamePresenter implements GamePresenterInterface{
    private GameView view;
    private GameModel model;

    public GamePresenter(GameView view){
        this.view = view;
    }

    public void setModel(List<Integer> btn_ids) {
        this.model = new GameModel(btn_ids);
    }

    @Override
    public void onCreate(Integer game_diff) {
        view.setLayout(game_diff);
    }

    public void newRound(){
            model.newBtnSeq();
            view.playSeq(model.getBtnSeq());
    }

    public void checkButton(Integer btn_id){
        if(model.checkBtn(btn_id)) {
            Log.d("/Presenter/CheckBtn", "Right Button");
            if (model.isEmptySqe()) {
                Log.d("Presenter/CheckBtn", "Empty Sequence");
                this.newRound();
            }
        }
        else{
               Log.d("Presenter/CheckBtn","Wrong Button");
                view.gameOver(model.getSqnSize());
        }

    }
}

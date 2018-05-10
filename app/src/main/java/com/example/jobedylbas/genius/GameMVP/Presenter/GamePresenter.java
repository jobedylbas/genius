package com.example.jobedylbas.genius.GameMVP.Presenter;

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
    public void onCreate(String game_diff) {
        view.setLayout(game_diff);
    }

    public void newRound(){
        try {
            model.newBtnSeq();
            view.playSeq(model.getBtnSeq());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

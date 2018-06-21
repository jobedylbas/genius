package com.example.jobedylbas.genius.ui.game;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.speech.RecognizerResultsIntent;
import android.util.Log;

import com.example.jobedylbas.genius.database.DatabaseHelper;
import com.example.jobedylbas.genius.database.models.GameModel;
import com.example.jobedylbas.genius.database.models.Record;

import java.util.List;
import java.util.Queue;

/**
 * Created by jobedylbas on 07/05/18.
 */

public class GamePresenter implements GamePresenterInterface {
    private final static String TAG = GamePresenter.class.getName();
    private DatabaseHelper dbhelper;
    private GameView view;
    private GameModel model;

    public GamePresenter(GameView view){
        this.view = view;
    }

    public void newGame(Integer diff) {
        view.setLayout(diff);
        model = new GameModel(view.getBtnList());
    }

    public void newRound(){
        model.newBtnSeq();
        view.setPoints(model.getSeqSize());
        view.playSeq(model.getBtnSeq());
    }

    public void endGame(){
        view.gameOver(model.getSeqSize());
    }

    public void checkButton(Integer btn_id){
        if(model.checkBtn(btn_id)) {
            Log.d(TAG, "Right Button");
            if(model.isEmptySeq()){
                Log.d(TAG, "Empty Sequence");
                newRound();
            }
        }
        else {
            Log.d(TAG, "Wrong Button");
            dbhelper = new DatabaseHelper(view.getViewContext());
            Integer min_record = dbhelper.getTheMinRecord(view.getDifficulty());
            if (model.getSeqSize() >= min_record) {
                view.newRecord();
            } else {
                endGame();
            }
        }
    }

    public void resetGame(){
        model.resetModel();
    }

    public boolean saveRecord(String player_name){
        dbhelper = new DatabaseHelper(view.getViewContext());
        long id = dbhelper.insertRecord(player_name, view.getDifficulty(), model.getSeqSize());
        return true;
    }
}

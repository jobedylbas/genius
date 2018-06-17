package com.example.jobedylbas.genius.ui.game;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.speech.RecognizerResultsIntent;
import android.util.Log;

import com.example.jobedylbas.genius.database.DatabaseHelper;
import com.example.jobedylbas.genius.database.models.GameModel;
import com.example.jobedylbas.genius.database.models.Record;

import java.util.List;

/**
 * Created by jobedylbas on 07/05/18.
 */

public class GamePresenter implements GamePresenterInterface {
    private final static String TAG = GamePresenter.class.getName();
    private Record record;
    private DatabaseHelper dbhelper;
    private GameView view;
    private GameModel model;

    public GamePresenter(GameView view){
        this.view = view;
    }

    public void setModel(List<Integer> btn_ids, Integer diff) {
        this.model = new GameModel(btn_ids, diff);
    }

    public void newGame(Integer diff) {
        view.setLayout(diff);
        model = new GameModel(view.getBtnList(), diff);
        newRound();
    }

    public void newRound(){
        model.newBtnSeq();
        view.playSeq(model.getBtnSeq());
    }

    public void checkButton(Integer btn_id){
        if(model.checkBtn(btn_id)) {
            Log.d(TAG, "Right Button");
            if (model.isEmptySeq()) {
                Log.d(TAG, "Empty Sequence");
                this.newRound();
            }
        }
        else{
               Log.d("Presenter/CheckBtn","Wrong Button");
               view.gameOver(model.getSeqSize());
        }

    }

    public void resetGame(){
        model.resetModel();
    }

    public void saveRecord(Context context){
        dbhelper = new DatabaseHelper(context);
        long id = dbhelper.insertRecord(new Record("jobe", model.getDifficulty(), model.getSeqSize()));
    }
}

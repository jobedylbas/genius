package com.example.jobedylbas.genius.ui.records;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jobedylbas.genius.database.models.Record;
import com.example.jobedylbas.genius.database.DatabaseHelper;

import java.util.List;

/**
 * Created by jobedylbas on 29/05/18.
 */

public class RecordsPresenter implements RecordsPresenterInterface{
    private final static String TAG = RecordsPresenter.class.getName();
    private DatabaseHelper model;
    private RecordsView view;
    private SQLiteDatabase db;

    public RecordsPresenter(RecordsView view, Context context){
        this.view = view;
        this.model = new DatabaseHelper(context);
    }

    public void getRecords(Integer difficulty){
        List<Record> records = model.getRecordsByDifficulty(difficulty, 10);
        if(records.isEmpty()){
            view.noRecords();
        }
        else{
            int i = 1;
            for(Record record : records){
                view.inflate(i, record.getPlayerName(), record.getSeq_size());
                i = i +1;
            }
        }


    }
}

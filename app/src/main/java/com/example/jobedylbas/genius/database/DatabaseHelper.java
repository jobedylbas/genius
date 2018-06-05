package com.example.jobedylbas.genius.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jobedylbas.genius.database.models.Record;

import java.util.ArrayList;
import java.util.List;

import static com.example.jobedylbas.genius.database.DatabaseContract.Entry.KEY_DIFFICULTY;
import static com.example.jobedylbas.genius.database.DatabaseContract.Entry.KEY_ID;
import static com.example.jobedylbas.genius.database.DatabaseContract.Entry.KEY_PLAYER_NAME;
import static com.example.jobedylbas.genius.database.DatabaseContract.Entry.KEY_SEQ_SIZE;
import static com.example.jobedylbas.genius.database.DatabaseContract.Entry.KEY_TIMESTAMP;
import static com.example.jobedylbas.genius.database.DatabaseContract.Entry.RECORDS_TABLE;

/**
 * Created by jobedylbas on 01/06/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String LOG = "RecordDbHelper";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Record.db";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + RECORDS_TABLE + " ( " +
                KEY_ID + " INTEGER PRIMARY KEY, "+
                KEY_PLAYER_NAME + " TEXT, " +
                KEY_DIFFICULTY + " INTEGER, " +
                KEY_SEQ_SIZE + " INTEGER, " +
                KEY_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + RECORDS_TABLE;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }

    public long insertRecord(Record record){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PLAYER_NAME, record.getPlayerName());
        values.put(KEY_DIFFICULTY, record.getDifficulty());
        values.put(KEY_SEQ_SIZE, record.getSeq_size());

        long record_id = db.insert(DATABASE_NAME, null, values);
        db.close();
        return record_id;
    }

    public void deleteRecord(Record record){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(RECORDS_TABLE, KEY_ID + " = ?",
                new String[]{String.valueOf(record.getId())});
        db.close();
    }

    public List<Record> getRecordsByDifficulty(Integer difficulty){
        List<Record> records = new ArrayList<Record>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + RECORDS_TABLE + " WHERE " +
                KEY_DIFFICULTY + " = " + difficulty + " ORDER BY " +
                KEY_SEQ_SIZE + " DESC";
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                Record record = new Record(
                        cursor.getString(cursor.getColumnIndex(KEY_PLAYER_NAME)),
                        cursor.getInt(cursor.getColumnIndex(KEY_SEQ_SIZE)),
                        difficulty
                );
                records.add(record);
            }while(cursor.moveToNext());
        }
        db.close();
        return records;
    }
}

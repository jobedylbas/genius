package com.example.jobedylbas.genius.database;

import android.provider.BaseColumns;

/**
 * Created by jobedylbas on 01/06/18.
 */

public class DatabaseContract {
    private DatabaseContract(){}

    public static class Entry implements BaseColumns{
        public static final String GAME_TABLE = "game_table";
        public static final String RECORDS_TABLE = "records_table";
        public static final String KEY_PLAYER_NAME = "player_name";
        public static final String KEY_DIFFICULTY = "difficulty";
        public static final String KEY_SEQ_SIZE = "sequence_size";
        public static final String KEY_ID = "id";
        public static final String KEY_TIMESTAMP = "timestamp";
        public static final String KEY_BTN_SEQ = "btn_seq";
        public static final String KEY_BTN_AVAILABLE = "btn_available";
    }

}

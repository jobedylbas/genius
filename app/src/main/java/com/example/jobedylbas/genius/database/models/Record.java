package com.example.jobedylbas.genius.database.models;

/**
 * Created by jobedylbas on 01/06/18.
 */

public class Record {
    private final static String TAG = Record.class.getName();
    private int id;
    private String timestamp;
    private String player_name;
    private Integer difficulty;
    private Integer seq_size;

    public Record(String name, Integer difficulty, Integer seq_size){
        this.player_name = name;
        this.difficulty = difficulty;
        this.seq_size = seq_size;
    }

    public String getPlayerName(){ return this.player_name;}

    public Integer getDifficulty(){ return this.difficulty;}

    public Integer getSeq_size(){ return this.seq_size;}

    public int getId(){ return this.id;}

    public String getTimestamp(){ return this.timestamp;}
}

package com.example.jobedylbas.genius.ui.records;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import com.example.jobedylbas.genius.R;

/**
 * Created by jobedylbas on 03/06/18.
 */

public class RecordsView extends AppCompatActivity implements RecordsViewInterface{
    private final static String TAG = RecordsView.class.getName();
    private RecordsPresenter presenter;
    private Integer difficulty;
    private LinearLayout parent;
    private LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.records);
        difficulty = getIntent().getIntExtra("GAME_DIFF", R.id.easy_records);
        presenter = new RecordsPresenter(this, getApplicationContext());

        parent = findViewById(R.id.records_parent);
        layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.records_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        presenter.getRecords(difficulty);
    }

    public void inflate(Integer i, String player_name, Integer seq_size){
        // Add the row to layout
        String position;
        View view = layoutInflater.inflate(R.layout.record_row, null, false);

        parent.addView(view);

        // Add the text to the row
        TextView aux = view.findViewById(R.id.position_text);
        switch (i){
            case 1:
                position = getResources().getString(R.string.first);
                break;
            case 2:
                position = getResources().getString(R.string.second);
                break;
            case 3:
                position = getResources().getString(R.string.third);
                break;
            default:
                position = String.valueOf(i)+getResources().getString(R.string.metric);
        }
        aux.setText(position);
        aux = view.findViewById(R.id.player_text);
        aux.setText(player_name);
        aux = view.findViewById(R.id.seq_text);
        aux.setText(String.valueOf(seq_size));
    }

    public void noRecords(){
        TextView no_records = findViewById(R.id.norecords_text);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        no_records.setText(R.string.norecords);
        no_records.setLayoutParams(params);
    }
}

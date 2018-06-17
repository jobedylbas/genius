package com.example.jobedylbas.genius.ui.records;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jobedylbas.genius.R;

/**
 * Created by jobedylbas on 03/06/18.
 */

public class RecordsView extends AppCompatActivity {
    private final static String TAG = RecordsView.class.getName();
    private RecordsPresenter presenter;
    private Integer difficulty;
    private LinearLayout parent;
    private LayoutInflater layoutInflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_screen);
        difficulty = getIntent().getIntExtra("GAME_DIFF", R.id.easy_records);
        presenter = new RecordsPresenter(this, getApplicationContext());

        parent = findViewById(R.id.records_parent);
        layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        presenter.getRecords(difficulty);
    }

    public void inflate(Integer i, String player_name, Integer seq_size){
        // Add the row to layout
        View view = layoutInflater.inflate(R.layout.record_row, null, false);

        parent.addView(view);

        // Add the text to the row
        TextView aux = view.findViewById(R.id.position_text);
        aux.setText(String.valueOf(i));
        aux = view.findViewById(R.id.player_text);
        aux.setText(player_name);
        aux = view.findViewById(R.id.seq_text);
        aux.setText(String.valueOf(seq_size));
    }

    public void noRecords(){
        TextView norecords = findViewById(R.id.norecords_text);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        norecords.setText(R.string.norecords);
        norecords.setLayoutParams(params);
    }
}

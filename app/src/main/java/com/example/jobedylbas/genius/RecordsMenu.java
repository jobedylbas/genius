package com.example.jobedylbas.genius;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.jobedylbas.genius.R;
import com.example.jobedylbas.genius.ui.records.RecordsView;

/**
 * Created by jobedylbas on 29/05/18.
 */

public class RecordsMenu extends AppCompatActivity {
    private int difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.records_type);

        // Bind Buttons
        findViewById(R.id.easyRecords).setOnClickListener(this.clickHandler);
        findViewById(R.id.normalRecords).setOnClickListener(this.clickHandler);
        findViewById(R.id.hardcoreRecords).setOnClickListener(this.clickHandler);
    }



    private android.view.View.OnClickListener clickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.easyRecords:
                    difficulty = R.id.easy_btn;
                    break;
                case R.id.normalRecords:
                    difficulty = R.id.normal_btn;
                    break;
                case R.id.hardcoreRecords:
                    difficulty = R.id.hardcore_btn;
                    break;
                default:
                    break;
            }
            Log.d("MVPRec/View/RecordsMenu", "Difficulty: "+String.valueOf(difficulty));
            Intent records_view = new Intent(view.getContext(), RecordsView.class);
            records_view.putExtra("GAME_DIFF", difficulty);
            startActivity(records_view);
            onPause();
        }

    };


}

package com.example.jobedylbas.genius;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jobedylbas.genius.GameMVP.View.GameView;
import com.example.jobedylbas.genius.MVPRecords.View.RecordsView;

/**
 * Created by jobedylbas on 29/05/18.
 */

public class RecordsType extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.records_type);

        findViewById(R.id.easyRecords).setOnClickListener(this.clickHandler);;
        findViewById(R.id.normalRecords).setOnClickListener(this.clickHandler);
        findViewById(R.id.hardcoreRecords).setOnClickListener(this.clickHandler);
    }

    private android.view.View.OnClickListener clickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent game = new Intent(view.getContext(), RecordsView.class);
            Button b = (Button) view;
            Integer difficulty = b.getId();
            game.putExtra("GAME_DIFF", difficulty);
            startActivity(game);
            onPause();
        }
    };
}

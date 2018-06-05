package com.example.jobedylbas.genius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jobedylbas.genius.ui.game.GameView;

/**
 * Created by jobedylbas on 02/05/18.
 */

public class DifficultyMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_menu);

        findViewById(R.id.easy_btn).setOnClickListener(this.clickHandler);;
        findViewById(R.id.normal_btn).setOnClickListener(this.clickHandler);
        findViewById(R.id.hardcore_btn).setOnClickListener(this.clickHandler);
        findViewById(R.id.free_btn).setOnClickListener(this.clickHandler);
    }

    private android.view.View.OnClickListener clickHandler = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.free_btn){
                    Intent game = new Intent(DifficultyMenu.this, Free.class);
                    startActivity(game);
                    onPause();
                }
                else {
                    Intent game = new Intent(view.getContext(), GameView.class);
                    Button b = (Button) view;
                    Integer game_difficulty = b.getId();
                    game.putExtra("GAME_DIFF", game_difficulty);
                    startActivity(game);
                    onPause();
                }
            }
    };
}
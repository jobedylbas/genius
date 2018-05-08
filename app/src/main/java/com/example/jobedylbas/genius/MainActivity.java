package com.example.jobedylbas.genius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.view.View.OnClickListener;


public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.new_game_btn).setOnClickListener(clickHandler);
        Button load_game_btn = (Button) findViewById(R.id.load_game_btn);
        Button records_btn = (Button) findViewById(R.id.records_btn);
        findViewById(R.id.quit_btn).setOnClickListener(clickHandler);
    }


    private OnClickListener clickHandler = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.new_game_btn:
                    Intent new_game = new Intent(MainActivity.this, DifficultyMenu.class);
                    startActivity(new_game);
                    onPause();
                    break;
                case R.id.quit_btn:
                    finish();
                    System.exit(0);
                default:
                    break;
            }
        }
    };
}

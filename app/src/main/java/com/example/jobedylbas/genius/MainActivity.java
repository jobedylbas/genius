package com.example.jobedylbas.genius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.example.jobedylbas.genius.database.DatabaseHelper;


public class MainActivity extends AppCompatActivity{
    private final static String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.new_game_btn).setOnClickListener(clickHandler);
        Button load_game_btn = (Button) findViewById(R.id.load_game_btn);
        findViewById(R.id.records_btn).setOnClickListener(clickHandler);

    }


    private OnClickListener clickHandler = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.new_game_btn:
                    Intent new_game = new Intent(MainActivity.this, DifficultyMenu.class);
                    onPause();
                    startActivity(new_game);
                    break;
                case R.id.records_btn:
                    Intent records = new Intent(MainActivity.this, RecordsMenu.class);
                    onPause();
                    startActivity(records);
                    break;
                default:
                    break;
            }
        }
    };
}

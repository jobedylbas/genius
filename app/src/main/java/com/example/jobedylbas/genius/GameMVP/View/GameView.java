package com.example.jobedylbas.genius.GameMVP.View;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.jobedylbas.genius.GameMVP.Presenter.GamePresenter;
import com.example.jobedylbas.genius.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Thread.sleep;

/**
 * Created by jobedylbas on 03/05/18.
 * GameModel Easy show the activity/layout for Difficulty Easy
 * of game Genius
 */

public class GameView extends AppCompatActivity implements GameViewInterface{
    private GamePresenter presenter;
    private static List<Integer> btn_list;
    private static String game_diff;
    private SimonButton[] buttons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game_diff = getIntent().getStringExtra("GAME_DIFF");

        presenter = new GamePresenter(this);
        presenter.onCreate(game_diff);
        presenter.setModel(AllButtonsId());
        presenter.newRound();
    }

    // Get all the buttons id
    private List<Integer> AllButtonsId(){
        List<View> all_btn;
        List<Integer> all_btn_id = new LinkedList<>();

        // Get All Buttons
        all_btn = (findViewById(R.id.top_line)).getTouchables();
        try {
            all_btn.addAll((findViewById(R.id.medium_line)).getTouchables());
        }catch (NullPointerException npe) {
            // Do nothing
        }
        all_btn.addAll((findViewById(R.id.bottom_line)).getTouchables());

        // Get the id fo buttons collected
        for(View button : all_btn){
            all_btn_id.add(button.getId());
        }

        return all_btn_id;
    }

    // Set the layout of the game based on difficulty
    public void setLayout(String game_diff){
        switch (game_diff){
            case "EASY":
                setContentView(R.layout.activity_game_easy);
                buttons = createButtons();
                setOnClick(buttons);
                break;
            case "NORMAL":
                setContentView(R.layout.activity_game_normal);
                break;
            case "HARDCORE":
                setContentView(R.layout.activity_game_harcore);
                break;
            default:
                break;
        }
    }

    public void playSeq(Queue<Integer> btn_ids) {
        final Queue<Integer> btn_list = btn_ids;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(final Integer btn_id : btn_list) {
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Log.d("playSeq", String.valueOf(findViewById(btn_id)));
                                findViewById(btn_id).performLongClick();
                            }
                        });
                        //Thread.sleep(250);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private SimonButton[] createButtons(){

        MediaPlayer[] sounds = new Media(this).getMedias();

        SimonButton btn_red = new SimonButton(sounds[0], (Button) this.findViewById(R.id.btn_red),
                R.drawable.background_red, R.drawable.background_red_shiny);
        SimonButton btn_blue = new SimonButton(sounds[1], (Button) this.findViewById(R.id.btn_blue),
                R.drawable.background_blue, R.drawable.background_blue_shiny);
        SimonButton btn_green = new SimonButton(sounds[2], (Button) this.findViewById(R.id.btn_green),
                R.drawable.background_green, R.drawable.background_green_shiny);
        SimonButton btn_yellow = new SimonButton(sounds[3], (Button) this.findViewById(R.id.btn_yellow),
                R.drawable.background_yellow, R.drawable.background_yellow_shiny);

        SimonButton[] buttons = {btn_red, btn_green, btn_blue, btn_yellow};
        return buttons;

    }

    private void setOnClick(SimonButton[] buttons){

        for (final SimonButton current_button : buttons) {
            current_button.getButtonObject().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    current_button.setBkgShiny();
                                    current_button.getSound().start();
                                }
                            });

                            try {
                                Thread.sleep(600);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    current_button.setBkgNormal();
                                }
                            });

                        }

                    }).start();
                    return true;
                }
            });
            current_button.getButtonObject().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                current_button.getSound().start();
                                current_button.setBkgShiny();
                            }

                        });
                    }

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                current_button.setBkgNormal();
                                Log.d("View/onTouch/ButtonId", String.valueOf(current_button.getButtonObject().getId()));
                                presenter.checkButton(current_button.getButtonObject().getId());
                            }
                        });
                    }

                    return true;
                }
            });

        }

    }

}

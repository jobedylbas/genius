package com.example.jobedylbas.genius.GameMVP.View;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game_diff = getIntent().getStringExtra("GAME_DIFF");

        presenter = new GamePresenter(this);
        presenter.onCreate(game_diff);
        presenter.setModel(AllButtonsId());
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
                SimonButton[] buttons = this.addSoundToBtn();
                this.setOnClick(buttons);
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

    public void playSeq(Queue<Integer> btn_ids) throws InterruptedException {
        for(final Integer btn_id : btn_ids){
            findViewById(btn_id).performLongClick();
            sleep(1000);
        }
    }

    private SimonButton[] addSoundToBtn(){

        MediaPlayer[] sounds = new Media(this).getMedias();

        SimonButton btn_red = new SimonButton(sounds[0], (Button) this.findViewById(R.id.btn_red));
        SimonButton btn_blue = new SimonButton(sounds[1], (Button) this.findViewById(R.id.btn_blue));
        SimonButton btn_green = new SimonButton(sounds[2], (Button) this.findViewById(R.id.btn_green));
        SimonButton btn_yellow = new SimonButton(sounds[3], (Button) this.findViewById(R.id.btn_yellow));

        SimonButton[] buttons = {btn_red, btn_green, btn_blue, btn_yellow};
        return buttons;

    }

    private void setOnClick(SimonButton[] buttons){

        for (final SimonButton current_button : buttons) {
            current_button.getButtonObject().setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    current_button.getSound().start();
                }
            });
            current_button.getButtonObject().setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v){
                    current_button.getSound().start();
                    return true;
                }
            });

        }

    }


}

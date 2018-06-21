package com.example.jobedylbas.genius.ui.game;

import android.content.Context;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobedylbas.genius.MainActivity;
import com.example.jobedylbas.genius.R;
import com.example.jobedylbas.genius.utils.Media;
import com.example.jobedylbas.genius.utils.SimonButton;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static java.lang.Thread.sleep;

/**
 * Created by jobedylbas on 03/05/18.
 * GameView the activity/layout
 * of game Genius
 */

public class GameView extends AppCompatActivity implements GameViewInterface {
    private static final int EASY = R.id.easy_btn;
    private static final int NORMAL = R.id.normal_btn;
    private static final int HARD = R.id.hardcore_btn;
    private static final int TOP_LINE = R.id.top_line;
    private static final int BOT_LINE = R.id.bottom_line;
    private static final int INITIAL_POINTS = 0;
    private static Boolean SOUND = true;
    private GamePresenter presenter;
    private List<Integer> btn_list;
    private Integer difficulty;
    private SimonButton[] buttons;
    private Integer interval;
    private Integer long_click_interval;
    private SoundPool sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        difficulty = getIntent().getIntExtra("GAME_DIFF", EASY);

        presenter = new GamePresenter(this);
        presenter.newGame(difficulty);
        presenter.newRound();
    }

    public Context getViewContext(){
        return getApplicationContext();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sound) {
            SOUND = !SOUND;
            if(SOUND){
                item.setIcon(R.drawable.volume_up);
            }
            else {
                item.setIcon(R.drawable.volume_off);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {   for(SimonButton current_button : buttons){
        sp.stop(current_button.getSoundId());
    }
        sp.release();
        presenter.resetGame();
        super.onBackPressed();
    }

    public Integer getDifficulty(){ return difficulty; }

    // Get all the buttons id
    private void getAllButtonsId(){
        btn_list = new LinkedList<>();
        // Get All Buttons
        List<View> all_btn = (findViewById(TOP_LINE)).getTouchables();

        all_btn.addAll((findViewById(BOT_LINE)).getTouchables());

        // Get the id fo buttons collected
        for(View button : all_btn){
            btn_list.add(button.getId());
        }

    }

    public List<Integer> getBtnList(){
        return this.btn_list;
    }
    // Set the layout of the game based on difficulty
    public void setLayout(Integer difficulty){
        switch (difficulty){
            case EASY:
                interval = 1000;
                long_click_interval = 600;
                break;
            case NORMAL:
                interval = 800;
                long_click_interval = 400;
                break;
            case HARD:
                interval = 400;
                long_click_interval = 200;
                break;
            default:
                break;
        }
        setContentView(R.layout.activity_game_easy);
        getAllButtonsId();
        bindButtons();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setPoints(INITIAL_POINTS);
    }

    public void playSeq(Queue<Integer> btn_ids) {

        final Queue<Integer> btn_list = btn_ids;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(final Integer btn_id : btn_list) {
                    try {
                        Thread.sleep(interval);
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

    public void gameOver(Integer score){
        for (SimonButton current_button : buttons){
            sp.stop(current_button.getSoundId());
            current_button.releaseBtn();
        }
        sp.release();
        final ConstraintLayout game_over = findViewById(R.id.endgame);
        game_over.setVisibility(View.VISIBLE);

        TextView score_view = game_over.findViewById(R.id.finalscore);
        score_view.setText(score.toString());

        findViewById(R.id.restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game_over.setVisibility(View.GONE);
                bindButtons();
                presenter.resetGame();
                presenter.newRound();
                setPoints(0);
                for (final SimonButton current_button : buttons){
                    current_button.bindBtn();
                }
            }
        });

        findViewById(R.id.to_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main_menu = new Intent(GameView.this, MainActivity.class);
                startActivity(main_menu);
            }
        });

    }

    public void newRecord(){
        for (SimonButton current_button : buttons){
            sp.stop(current_button.getSoundId());
            current_button.releaseBtn();
        }
        sp.release();

        final ConstraintLayout new_record = findViewById(R.id.new_record);
        new_record.setVisibility(View.VISIBLE);

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText player_name = findViewById(R.id.player_name);
                if(TextUtils.isEmpty(player_name.getText())){
                    Context context = getViewContext();
                    CharSequence text = getString(R.string.erroname);;
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {
                    boolean res = presenter.saveRecord(player_name.getText().toString());
                    if(res){
                        Context context = getViewContext();
                        CharSequence text = getString(R.string.saved);
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        view.setEnabled(false);
                        view.setClickable(false);
                    }
                }
            }
        });

        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new_record.setVisibility(View.GONE);
                presenter.endGame();
            }
        });

    }

    private void bindButtons(){
        Media media = new Media(getViewContext());
        media.setSoundPool();
        int[] soundsId = media.getSoundsId();
        sp = media.getSoundPool();

        SimonButton btn_red = new SimonButton(soundsId[0], (Button) findViewById(R.id.btn_red),
                R.drawable.background_red, R.drawable.background_red_shiny);
        SimonButton btn_blue = new SimonButton(soundsId[1], (Button) this.findViewById(R.id.btn_blue),
                R.drawable.background_blue, R.drawable.background_blue_shiny);
        SimonButton btn_green = new SimonButton(soundsId[2], (Button) this.findViewById(R.id.btn_green),
                R.drawable.background_green, R.drawable.background_green_shiny);
        SimonButton btn_yellow = new SimonButton(soundsId[3], (Button) this.findViewById(R.id.btn_yellow),
                R.drawable.background_yellow, R.drawable.background_yellow_shiny);

        buttons = new SimonButton[]{btn_red, btn_green, btn_blue, btn_yellow};
        setOnClick(buttons);
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
                                    if (SOUND) {
                                        sp.play(current_button.getSoundId(), 1.0f, 1.0f, 0, 0, 1.0f);
                                    }
                                }
                            });

                            try {
                                Thread.sleep(long_click_interval);
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
            current_button.getButtonObject().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    if(SOUND) {
                        sp.play(current_button.getSoundId(), 1.0f, 1.0f, 0, 0, 1.0f);
                    }
                }
            });
            current_button.getButtonObject().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(SOUND){
                                    sp.play(current_button.getSoundId(),1.0f,1.0f,0,0,1.0f);
                                }
                                current_button.setBkgShiny();
                            }

                        });
                    }

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                sp.stop(current_button.getSoundId());
                                current_button.setBkgNormal();
                                presenter.checkButton(current_button.getButtonObject().getId());
                            }
                        });
                    }

                    return true;
                }
            });

        }

    }

    public void setPoints(Integer points){
        TextView points_view = findViewById(R.id.points);
        points_view.setText(getResources().getString(R.string.points)+" "+String.valueOf(points));
    }


}

package com.example.jobedylbas.genius.utils;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.widget.Button;

/**
 * Created by peronha on 06/05/18
 * Classe de modelo dos botoes
 */
public class SimonButton  {
    private final static String TAG = SimonButton.class.getName();
    protected int soundId;
    protected Button button_object;
    protected int color;
    protected int shiny_color;


    public SimonButton (int sound, Button button_object, int color, int shiny_color){
        this.soundId = sound;
        this.button_object = button_object;
        this.color = color;
        this.shiny_color = shiny_color;
    }

    public int getSoundId() {
        return soundId;
    }

    public void setSoundId(int sound) {
        this.soundId = sound;
    }

    public Button getButtonObject() {
        return button_object;
    }

    public void setButtonObject(Button buttonObject) {
        this.button_object = buttonObject;
    }

    public void setBkgNormal(){
        this.button_object.setBackgroundResource(color);
    }

    public void setBkgShiny(){
        this.button_object.setBackgroundResource(shiny_color);
    }

    public void releaseBtn(){
        button_object.setClickable(false);
        button_object.setEnabled(false);
    }

    public void bindBtn(){
        button_object.setClickable(true);
        button_object.setEnabled(true);
    }
}

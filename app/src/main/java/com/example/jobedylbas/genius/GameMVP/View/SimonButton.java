package com.example.jobedylbas.genius.GameMVP.View;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.widget.Button;

/**
 * Created by peronha on 06/05/18
 * Classe de modelo dos botoes
 */
public class SimonButton  {

    protected MediaPlayer sound;
    protected Button button_object;
    protected int color;
    protected int shiny_color;


    public SimonButton (MediaPlayer sound, Button button_object, int color, int shiny_color){
        this.sound = sound;
        this.button_object = button_object;
        this.color = color;
        this.shiny_color = shiny_color;
    }


    public MediaPlayer getSound() {
        return sound;
    }

    public void setSound(MediaPlayer sound) {
        this.sound = sound;
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
}

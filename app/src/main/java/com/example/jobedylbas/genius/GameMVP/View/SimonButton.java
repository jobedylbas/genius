package com.example.jobedylbas.genius.GameMVP.View;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.widget.Button;

/**
 * Created by peronha on 06/05/18
 * Classe de modelo dos botoes
 */
public class SimonButton  {

    protected MediaPlayer sound;
    protected Button buttonObject;

    public SimonButton (MediaPlayer sound, Button buttonObject){
        this.setSound(sound);
        this.setButtonObject(buttonObject);
    }


    public MediaPlayer getSound() {
        return sound;
    }

    public void setSound(MediaPlayer sound) {
        this.sound = sound;
    }

    public Button getButtonObject() {
        return buttonObject;
    }

    public void setButtonObject(Button buttonObject) {
        this.buttonObject = buttonObject;
    }
}

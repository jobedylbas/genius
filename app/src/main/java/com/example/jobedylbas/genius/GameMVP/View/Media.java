package com.example.jobedylbas.genius.GameMVP.View;

import android.media.MediaPlayer;
import android.view.View;

import com.example.jobedylbas.genius.R;

import java.util.List;

/**
 * Created by jobedylbas on 07/05/18.
 */

public class Media {
    private GameView view;

    public Media(GameView view){ this.view = view;}

    public MediaPlayer[] getMedias(){

        final MediaPlayer simonSound1 = MediaPlayer.create(this.view, R.raw.simon_sound1);
        final MediaPlayer simonSound2 = MediaPlayer.create(this.view, R.raw.simon_sound2);
        final MediaPlayer simonSound3 = MediaPlayer.create(this.view, R.raw.simon_sound3);
        final MediaPlayer simonSound4 = MediaPlayer.create(this.view, R.raw.simon_sound4);

        final MediaPlayer[] simonSounds = {simonSound1, simonSound2, simonSound3, simonSound4};

        return simonSounds;

    }
}

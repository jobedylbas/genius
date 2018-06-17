package com.example.jobedylbas.genius.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.jobedylbas.genius.R;

import java.util.List;

/**
 * Created by jobedylbas on 07/05/18.
 */

public class Media {
    private final static String TAG = Media.class.getName();
    private Context context;
    private SoundPool sp;
    private int[] soundsId;

    public Media(Context context){ this.context = context;}

    public void setSoundPool(){
        sp = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
        int simonSound1 = sp.load(context, R.raw.simon_sound1,1);
        int simonSound2 = sp.load(context, R.raw.simon_sound2,1);
        int simonSound3 = sp.load(context, R.raw.simon_sound3,1);
        int simonSound4 = sp.load(context, R.raw.simon_sound4,1);
        soundsId = new int[]{simonSound1, simonSound2, simonSound3, simonSound4};
    }

    public int[] getSoundsId(){
        return soundsId;
    }

    public SoundPool getSoundPool() {
        return sp;
    }

}

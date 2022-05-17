package sk.upjs.ics.android.tetris;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {
    private static SoundPool soundPool;
    private static int fastDrop;
    private static int drop;
    private static int end;

    public SoundPlayer(Context context){
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        System.out.println("TEST");


        fastDrop = soundPool.load(context, R.raw.fast_drop, 1);
        drop = soundPool.load(context, R.raw.drop, 1);
        end = soundPool.load(context, R.raw.end, 1);
    }

    public void playFastDrop(){
        soundPool.play(fastDrop, 1.0f, 1.0f, 1, 0, 1.0f);
    }
    public void playDrop(){
        soundPool.play(drop, 1.0f, 1.0f, 1, 0, 1.0f);
    }
    public void playEnd(){soundPool.play(end, 1.0f, 1.0f, 1, 0, 1.0f);}
}

package sk.upjs.ics.android.tetris;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Points {

    private int currentPoints = 0;
    private MainActivity mainActivity;
    private int level = 0;

    public Points(Context context) {
        mainActivity = (MainActivity) context;
    }

    public void writeHighscore() {
        SharedPreferences pref = mainActivity.getSharedPreferences("GAME",0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("HIGHSCORE", level);
        editor.commit();
    }

    public int loadHighscore() {
        SharedPreferences pref = mainActivity.getSharedPreferences("GAME",0);
        return pref.getInt("HIGHSCORE",0);
    }


    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public int getCurrentPoints() {
        return this.currentPoints;
    }

    public void setLevel() {

        if(currentPoints>=200) {
            this.level = 1;
        }

        if(currentPoints>=600) {
            this.level = 2;
        }

        if(currentPoints>=1000) {
            this.level = 3;
        }

        if(currentPoints>=1400) {
            this.level = 4;
        }

        if(currentPoints>=1800) {
            this.level = 5;
        }

        if(currentPoints>=2200) {
            this.level = 6;
        }

        if(currentPoints>=2600) {
            this.level = 7;
        }
    }

    public int getLevel() {
        return this.level;
    }
}
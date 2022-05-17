package sk.upjs.ics.android.tetris;

import static sk.upjs.ics.android.tetris.MenuActivity.option;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;

public class PopActivity extends Activity {

    private Switch soundSwitch;
    private Switch musicSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_settings);


        soundSwitch = (Switch) findViewById(R.id.sound_switch);
        musicSwitch = (Switch) findViewById(R.id.music_switch);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        soundSwitch = new Switch(this);
            soundSwitch.setChecked(false);
        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (soundSwitch.isChecked()){
                    prefs.edit().putBoolean("sound", true).commit();
                }
                else {
                    prefs.edit().putBoolean("sound", false).commit();
                }
            }
        });

        musicSwitch = new Switch(this);
        musicSwitch.setChecked(false);
        /*
        if (prefs.getBoolean("music",true)){
            musicSwitch.setChecked(true);
            System.out.println("OMG");
        }
        else musicSwitch.setChecked(false);

         */
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                System.out.println("CHANGE");
                System.out.println("CHANGE");
                if (musicSwitch.isChecked()){
                    prefs.edit().putBoolean("music", true).commit();
                    System.out.println("MUSIC ON");
                    System.out.println("MUSIC ON");
                }
                else {
                    prefs.edit().putBoolean("music", false).commit();
                    System.out.println("MUSIC OFF");
                    System.out.println("MUSIC OFF");
                }
            }
        });




        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int height = dm.heightPixels;
        int width = dm.widthPixels;

        getWindow().setLayout((int)(width*.7), (int)(height*.6));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
    }


}
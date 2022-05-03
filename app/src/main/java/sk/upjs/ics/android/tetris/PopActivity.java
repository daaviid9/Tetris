package sk.upjs.ics.android.tetris;

import static sk.upjs.ics.android.tetris.MenuActivity.option;

import android.app.Activity;
import android.os.Bundle;
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

        soundSwitch = (Switch) findViewById(R.id.sound_switch);
        musicSwitch = (Switch) findViewById(R.id.music_switch);

        soundSwitch = new Switch(this);
        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                
            }
        });


        if (option == 1)
            setContentView(R.layout.activity_pop_help);
        else if (option == 2)
            setContentView(R.layout.activity_pop_settings);


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
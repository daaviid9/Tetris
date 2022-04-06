package sk.upjs.ics.android.tetris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {
    private ImageButton helpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // make fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        helpBtn = (ImageButton) findViewById(R.id.help_lb);

        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PopActivity.class));
            }
        });
    }




    public void startGame(View v){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void quit(View v){
        finish();
        System.exit(0);
    }

    //show and hide instructions

}
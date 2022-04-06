package sk.upjs.ics.android.tetris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {
    private LinearLayout instructions;
    private LinearLayout menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // make fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        instructions = (LinearLayout) findViewById(R.id.instructions);
        menu = (LinearLayout) findViewById(R.id.menu);

    }




    public void startGame(View v){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void quit(View v){
        finish();
        System.exit(0);
    }

    //show and hide instructions
    public void instructions(View view){
        if (instructions.getVisibility() == View.GONE){
            instructions.setVisibility(View.VISIBLE);
            menu.setVisibility(View.GONE);
        }
        else{
            instructions.setVisibility(View.GONE);
            menu.setVisibility(View.VISIBLE);
        }
    }
}
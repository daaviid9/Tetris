package sk.upjs.ics.android.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private Button quitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // make fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }




    public void startGame(View v){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void quit(View v){
        finish();
        System.exit(0);
    }
}
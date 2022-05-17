package sk.upjs.ics.android.tetris;

import static sk.upjs.ics.android.tetris.MainActivity.sound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameOverScreen extends AppCompatActivity {
    private ImageButton homeBtn;
    private TextView scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_screen);
        sound.playEnd();
        homeBtn = (ImageButton) findViewById(R.id.home_lb);
        scoreView = (TextView) findViewById(R.id.score_lb);

        int score = getIntent().getIntExtra("score", 0);

        scoreView.append(String.valueOf(score));

        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });
    }
}
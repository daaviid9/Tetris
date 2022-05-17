package sk.upjs.ics.android.tetris;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton pauseBtn;
    private ImageButton rotateButton;
    private ImageButton rightButton;
    private ImageButton downButton;
    private ImageButton leftButton;

    private TextView pointTextView;
    private TextView currentLevelTextView;
    private Tetris tetris;
    private NextPieceView nextPieceView;
    static boolean pause = false;
    static MediaPlayer mediaPlayer;
    static SoundPlayer sound;
    private GameBoard gameBoard = new GameBoard();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.tetris_soundtrack);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(0.3f,0.3f);
        if (prefs.getBoolean("music",true))
            mediaPlayer.start();

        sound = new SoundPlayer(this);

        pauseBtn = (ImageButton) findViewById(R.id.pauseBtn);
        rotateButton = (ImageButton) findViewById(R.id.rotateButton);
        rightButton = (ImageButton) findViewById(R.id.rightButton);
        downButton = (ImageButton) findViewById(R.id.downButton);
        leftButton = (ImageButton) findViewById(R.id.leftButton);
        pointTextView = (TextView) findViewById(R.id.textViewPoints);
        currentLevelTextView = (TextView) findViewById(R.id.levelText);

        nextPieceView = new NextPieceView(this, gameBoard);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(275,300);
        nextPieceView.setLayoutParams(params1);
        RelativeLayout relativeLay = (RelativeLayout) findViewById(R.id.relativelayout1);
        nextPieceView.setBackgroundColor(Color.parseColor("#4a8a9e"));
        relativeLay.addView(nextPieceView);

        tetris = new Tetris(this,nextPieceView, gameBoard);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(700, 1470);
        tetris.setLayoutParams(params);
        RelativeLayout relativeTetris = (RelativeLayout) findViewById(R.id.relativelayout);
        tetris.setBackgroundColor(Color.parseColor("#4a8a9e"));
        relativeTetris.addView(tetris);

        pause = false;
        
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PopPause.class));
                if(pause){
                    pause = false;
                }
                else if(!pause){
                    pause = true;
                }
            }
        });

    }

    @Override
    public void onRestart() {
        super.onRestart();
        pause = false;
        mediaPlayer.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        pause = true;
        mediaPlayer.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        finish();
    }


    public ImageButton getRightButton() { return rightButton;}
    public ImageButton getDownButton() { return downButton;}
    public ImageButton getLeftButton() { return leftButton;}
    public ImageButton getRotateButton() { return rotateButton; }
    public boolean getPause() {  return pause;}
    public void setPause(boolean pause) { this.pause=pause;}
    public TextView getPointTextView() { return pointTextView; }
    public TextView getCurrentLevelTextView() { return currentLevelTextView;}
    public MediaPlayer getMediaPlayer() {  return mediaPlayer; }
}
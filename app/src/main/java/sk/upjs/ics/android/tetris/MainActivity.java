package sk.upjs.ics.android.tetris;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton settingsBtn;
    private ImageButton rotateButton;
    private ImageButton rightButton;
    private ImageButton downButton;
    private ImageButton leftButton;

    private TextView pointTextView;
    private TextView currentLevelTextView;
    private Tetris tetris;
    private NextPieceView nextPieceView;
    static boolean pause = false;
    private MediaPlayer mediaPlayer;
    private int stopMediaplayer;
    private GameBoard gameBoard = new GameBoard();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource( this, Uri.parse("android.resource://sk.upjs.ics.android.tetris/raw/tetris_soundtrack"));
            mediaPlayer.prepare();
        } catch (Exception e) {e.printStackTrace();}


        settingsBtn = (ImageButton) findViewById(R.id.settingsBtn);
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
        settingsBtn.setOnClickListener(new View.OnClickListener() {

            int tmp=0;
            @Override
            public void onClick(View v) {
                stopMediaplayer = mediaPlayer.getCurrentPosition();
                tmp++;

                startActivity(new Intent(getApplicationContext(), PopPause.class));

                if(pause) {
                    pause = false;
                    if(tmp==1) {
                        mediaPlayer.start();
                        mediaPlayer.setLooping(true);
                    } else if(tmp>1) {
                        mediaPlayer.seekTo(stopMediaplayer);
                        mediaPlayer.start();
                    }
                }

                else if(!pause) {
                    pause = true;
                    mediaPlayer.pause();
                }
            }
        });

    }

    @Override
    public void onRestart() {
        super.onRestart();
        pause = false;
        mediaPlayer.seekTo(stopMediaplayer);
        mediaPlayer.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        pause = true;
        mediaPlayer.stop();
        mediaPlayer.pause();
        stopMediaplayer = mediaPlayer.getCurrentPosition();
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
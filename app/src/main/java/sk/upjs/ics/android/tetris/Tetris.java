package sk.upjs.ics.android.tetris;

import static sk.upjs.ics.android.tetris.MainActivity.prefs;
import static sk.upjs.ics.android.tetris.MainActivity.sound;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Tetris extends View implements View.OnClickListener{

    private MediaPlayer mediaPlayer;
    private GameBoard gameBoard;
    private MainActivity mainActivity;
    private ImageButton rotateButton;
    private ImageButton rightButton;
    private ImageButton downButton;
    private ImageButton leftButton;
    private Timer timer = new Timer();
    private Random random = new Random();
    private ArrayList<Piece> pieceList;
    private NextPieceView nextPieceView;
    private TextView currentLevelTextView;
    private TextView currentPointTextView;
    private Points points;
    private final int score = 100;
    private int timerPeriod = 290;
    private int level=0;
    private boolean pause;

    public Tetris(Context context, NextPieceView nextPieceView, GameBoard
            gameBoard) {
        super(context);

        this.mainActivity = (MainActivity) context;
        this.nextPieceView = nextPieceView;
        this.gameBoard =  gameBoard;
        pause = mainActivity.getPause();
        pieceList = gameBoard.getPieceList();
        mediaPlayer = mainActivity.getMediaPlayer();
        points = new Points(context);

        currentLevelTextView = mainActivity.getCurrentLevelTextView();
        currentPointTextView = mainActivity.getPointTextView();

        currentLevelTextView.append(" 0");
        currentPointTextView.append(" 0");

        rotateButton = mainActivity.getRotateButton();
        rightButton = mainActivity.getRightButton();
        downButton = mainActivity.getDownButton();
        leftButton = mainActivity.getLeftButton();

        rotateButton .setOnClickListener(this);
        rightButton.setOnClickListener(this);
        downButton.setOnClickListener(this);
        leftButton.setOnClickListener(this);
        gameLoop();
    }

    public void gameLoop() {

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                mainActivity.runOnUiThread(new TimerTask() {

                    @Override
                    public void run() {

                        if(gameOver()==false && mainActivity.getPause()==false ) {

                            gameBoard.moveDown(gameBoard.getCurrentPiece());



                            if (gameBoard.can_Move_Down(gameBoard.getCurrentPiece()) == false) {
                                points.setCurrentPoints(points.getCurrentPoints() + 1);

                                int deletedRows = gameBoard.clearRows();
                                gameBoard.clearRows();
                                pieceList.remove(gameBoard.getCurrentPiece());
                                pieceList.add(new Piece(random.nextInt(7) + 1));
                                nextPieceView.invalidate();

                                if (deletedRows > 0) {
                                    if (prefs.getBoolean("sound",true))
                                        sound.playRow();

                                    points.setCurrentPoints(points.getCurrentPoints() + deletedRows * score);

                                    if (points.getLevel() > points.loadHighscore()) {
                                        points.writeHighscore();
                                    }
                                }

                                points.setLevel();

                                currentPointTextView.setText("Points: " + points.getCurrentPoints());
                                currentLevelTextView.setText("Level: " + points.getLevel());


                                if(points.getLevel()>level) {
                                    level++;
                                    timerPeriod = timerPeriod - (points.getLevel()*20);
                                    timer.cancel();
                                    timer = new Timer();
                                    gameLoop();
                                }
                            }
                            invalidate();
                        }
                    }
                });
            }
        }, 0, timerPeriod);
    }

    public boolean gameOver() {

        if( gameBoard.checkGameOver(gameBoard.getCurrentPiece())==true ) {
            timer.cancel();
            pieceList.clear();
            gameBoard.clearGameBoard();
            mainActivity.setPause(true);
            mediaPlayer.stop();
            showGameOverScreen();
            return true;
        }
        return false;
    }

    public void showGameOverScreen() {
        Intent intent = new Intent(this.getContext(), GameOverScreen.class);
        intent.putExtra("score",points.getCurrentPoints());
        getContext().startActivity(intent);
    }

    /*
    change colorCode to specific Color and paint on Gameboard
    */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        for (int x = 0; x < gameBoard.getBoardHeight(); x++) {
            for (int y = 0; y < gameBoard.getBoardWidth(); y++) {

                int color  = gameBoard.codeToColor(x,y);
                paint.setColor(color);

                canvas.drawRect(y*70, x*70, y*70+70, x*70+70,paint);
            }
        }
    }

/*
control falling pieces with buttons
 */

    @Override
    public void onClick(View v) {
        if(!mainActivity.getPause()) {

            switch(v.getId()) {
                case R.id.rightButton:
                    gameBoard.moveRight(gameBoard.getCurrentPiece());
                    invalidate();
                    break;
                case R.id.downButton:
                    gameBoard.fastDrop(gameBoard.getCurrentPiece());
                    invalidate();
                    break;
                case R.id.leftButton:
                    gameBoard.moveLeft(gameBoard.getCurrentPiece());
                    invalidate();
                    break;
                case R.id.rotateButton:
                    gameBoard.rotatePiece(gameBoard.getCurrentPiece());
                    invalidate();
                    break;
            }
        }
    }
}

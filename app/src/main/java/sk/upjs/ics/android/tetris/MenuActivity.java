package sk.upjs.ics.android.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;


public class MenuActivity extends AppCompatActivity {
    private ImageButton helpBtn;
    private ImageButton settingsBtn;
    private ImageButton loginBtn;
    private Button startBtn;
    private Button quitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // make fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        helpBtn = (ImageButton) findViewById(R.id.help_lb);
        settingsBtn = (ImageButton) findViewById(R.id.settings_lb);
        loginBtn = (ImageButton) findViewById(R.id.login_lb);
        startBtn = (Button) findViewById(R.id.startBt);
        quitBtn = (Button) findViewById(R.id.quitBt);



        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PopHelp.class));


                // button spam protection
                helpBtn.setClickable(false);
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        helpBtn.setClickable(true);
                    }
                }, 500);
            }

        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PopSettings.class));


                // button spam protection
                settingsBtn.setClickable(false);
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        settingsBtn.setClickable(true);
                    }
                }, 500);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PopLogIn.class));

                // button spam protection
                settingsBtn.setClickable(false);
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        settingsBtn.setClickable(true);
                    }
                }, 500);
            }
        });


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

    }
}
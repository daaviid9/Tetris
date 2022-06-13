package sk.upjs.ics.android.tetris;

import static sk.upjs.ics.android.tetris.MainActivity.prefs;
import static sk.upjs.ics.android.tetris.MainActivity.sound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GameOverScreen extends AppCompatActivity {
    private ImageButton homeBtn;
    private TextView scoreView;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_screen);
        if (prefs.getBoolean("sound",true))
            sound.playEnd();
        homeBtn = (ImageButton) findViewById(R.id.home_lb);
        scoreView = (TextView) findViewById(R.id.score_lb);

        int score = getIntent().getIntExtra("score", 0);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        auth= FirebaseAuth.getInstance();

        FirebaseUser firUser = auth.getCurrentUser();

        if (firUser != null){
            userID = user.getUid();
            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userprofile= snapshot.getValue(User.class);

                    if (userprofile!=null){
                        int scoreDatabase = userprofile.score;

                        if (score > scoreDatabase)
                            FirebaseDatabase.getInstance().getReference("Users").child(userID).child("score")
                                    .setValue(score);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(GameOverScreen.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
                }
            });
        }

        scoreView.append(String.valueOf(score));

        homeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });
    }
}
package sk.upjs.ics.android.tetris;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends Activity  {

    private FirebaseAuth mAuth;
    private EditText nick, emailRegister,passwordRegister;
    private Button registerBtn;
    private TextView backtologin;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        mAuth = FirebaseAuth.getInstance();

        nick = (EditText) findViewById(R.id.nick);
        emailRegister = (EditText) findViewById(R.id.emailRegister);
        passwordRegister = (EditText) findViewById(R.id.passwordRegister);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        backtologin=(TextView) findViewById(R.id.backToLogIn);

        progressBar=(ProgressBar) findViewById(R.id.progressBar);



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int height = dm.heightPixels;
        int width = dm.widthPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.7));

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.registerBtn:
                        RegisterUser();
                        break;

                }
            }

        });
        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), PopLogIn.class));
            }
        });
    }


    private  void RegisterUser(){
        String meno = nick.getText().toString().trim();
        String password = passwordRegister.getText().toString().trim();
        String email= emailRegister.getText().toString().trim();

        if (meno.isEmpty()){
            nick.setError("Nick is required!");
            nick.requestFocus();
            return;
        }
        if (email.isEmpty()){
            emailRegister.setError("Email is required!");
            emailRegister.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailRegister.setError("Please provide valid email!");
            emailRegister.requestFocus();
            return;
        }

        if (password.isEmpty()){
            passwordRegister.setError("Password is required!");
            passwordRegister.requestFocus();
            return;
        }

        if (password.length()<6){
            passwordRegister.setError("Min password lenght should be 6 characters!");
            passwordRegister.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User( meno, email);
                            FirebaseDatabase.getInstance().getReference("Users")
                             .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(Register.this, "User has been registered succesfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }else{
                                        Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });

    }
}


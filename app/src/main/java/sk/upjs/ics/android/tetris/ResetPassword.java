package sk.upjs.ics.android.tetris;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    private EditText email;
    private Button resetpassword;
    private TextView backToLogin;
    private ProgressBar progressBar;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int height = dm.heightPixels;
        int width = dm.widthPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        email= (EditText) findViewById(R.id.email);
        resetpassword= (Button) findViewById(R.id.resetpassword);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        auth= FirebaseAuth.getInstance();
        backToLogin = (TextView) findViewById(R.id.backToLogIn);

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), PopLogIn.class));
            }
        });

    }
    public void reset(View view){
        resetPassword();
    }

    private void resetPassword() {
        String em= email.getText().toString().trim();

        if (em.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(em).matches()){
            email.setError("Please provide valid email!");
            email.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(em).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ResetPassword.this, "Check your email to reset your password", Toast.LENGTH_LONG).show();
                }else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ResetPassword.this, "Try again! Something wrong happened", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
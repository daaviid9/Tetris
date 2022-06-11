package sk.upjs.ics.android.tetris;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

import org.w3c.dom.Text;

public class PopLogIn extends Activity {
    private EditText email,password;
    private Button signin;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private TextView forgotpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_log_in);

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
        signin= (Button) findViewById(R.id.signIn);

        email= (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        mAuth= FirebaseAuth.getInstance();
        forgotpassword=(TextView) findViewById(R.id.forgotPassword);

    }

    public void goToRegister(View v){
        this.finish();
        startActivity(new Intent(getApplicationContext(), Register.class));
    }

    public void singIN(View v){
        UserLogin();
    }

    public void ForgorPassword(View v){
        this.finish();
        startActivity(new Intent(getApplicationContext(), ResetPassword.class));
    }
    private void UserLogin() {
      String em = email.getText().toString().trim();
      String pass = password.getText().toString().trim();

      if (em.isEmpty()){
          email.setError("Email is required!");
          email.requestFocus();
          return;
      }
      if (!Patterns.EMAIL_ADDRESS.matcher(em).matches()){
          email.setError("Please enter valid email!");
          email.requestFocus();
          return;
      }

      if (pass.isEmpty()){
          password.setError("Password is required!");
          password.requestFocus();
          return;
      }
      if (pass.length()<6){
          password.setError("Min password lenght is 6 characters!");
          password.requestFocus();
          return;
      }

      progressBar.setVisibility(View.VISIBLE);
      mAuth.signInWithEmailAndPassword(em,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if (task.isSuccessful()){
                  progressBar.setVisibility(View.GONE);
                  finish();
                  startActivity(new Intent(PopLogIn.this, ProfileActivity.class));

              }else{
                  progressBar.setVisibility(View.GONE);
                  Toast.makeText(PopLogIn.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
              }
          }
      });

    }


}



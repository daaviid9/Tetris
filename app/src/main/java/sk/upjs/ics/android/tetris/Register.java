package sk.upjs.ics.android.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText nick, emailRegister,passwordRegister;
    private Button registerBtn;
    private TextView backtologin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
    }
}
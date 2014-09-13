package edu.grinnellappdev.grinwell_android;

/**
 * Created by Michael on 9/13/14.
 */
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.TextView;


public class LoginActivity extends ActionBarActivity {

    Button loginButton, signupButton;

    TextView emailTextView, passwordTextView;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }
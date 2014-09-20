package edu.grinnellappdev.grinwell_android;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {
    //Variable declaration
    EditText email;
    EditText password;
    String emailText;
    String passwordText;
    FrameLayout logInButton;
    FrameLayout signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Get The EditText(s)
        email = (EditText) findViewById(R.id.editText_email);
        password = (EditText) findViewById(R.id.editText_password);

        //Get The Button(s)
        logInButton = (FrameLayout) findViewById(R.id.login_button);


        //Log in action
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get input values
                emailText = email.getText().toString();
                passwordText = password.getText().toString();
                //send to parse
                ParseUser.logInInBackground(emailText, passwordText, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser != null) {
                            //goto main page
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }//if(parseUser != null)
                        else {
                            //something went wrong!
                            //failed
                            //create the builder
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage(e.getMessage());
                            builder.setTitle("Sign In Error");
                            builder.setPositiveButton(android.R.string.ok, null);
                            //create the dialog
                            AlertDialog dialog = builder.create();
                            //show the dialog
                            dialog.show();
                        }//else
                    }// public void done
                }//ParseUser.logInInBackground(
                );
            }//public void onClick
        }//logInButton.setOnClickListener
        );

        //signup button onClick
        signUpButton = (FrameLayout) findViewById(R.id.signup_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, NewSignUpActivity.class));
                finish();
            }
        });
    }//protected void onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }//onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }//if
        return super.onOptionsItemSelected(item);
    }//onOptionsItemSelected
}//public class Log_In_Activity
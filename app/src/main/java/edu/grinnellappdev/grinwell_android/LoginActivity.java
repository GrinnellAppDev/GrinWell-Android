package edu.grinnellappdev.grinwell_android;

/**
 * Created by Michael on 9/13/14.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {
    //Variable declaration
    EditText username;
    EditText password;
    String usernameText;
    String passwordText;
    Button logInButton;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Get The EditText(s)
        username = (EditText) findViewById(R.id.editText_username);
        password = (EditText) findViewById(R.id.editText_password);

        //Get The Button(s)
        logInButton = (Button) findViewById(R.id.button_signin);


        //Log in action
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get input values
                usernameText = username.getText().toString();
                passwordText = password.getText().toString();
                //send to parse
                ParseUser.logInInBackground(usernameText, passwordText, new LogInCallback() {
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
        signUpButton = (Button) findViewById(R.id.button_signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, NewSignUpActivity.class));
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
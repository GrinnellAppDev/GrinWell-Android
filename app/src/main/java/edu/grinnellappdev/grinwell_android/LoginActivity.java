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
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.Date;

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


        getActionBar().hide();
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

                email = (EditText) findViewById(R.id.editText_email);
                password = (EditText) findViewById(R.id.editText_password);


                signUp();

            }
        });
    }//protected void onCreate

    public void signUp(){


        emailText = email.getText().toString();
        passwordText = password.getText().toString();

        // if one or more fields are empty
        if (passwordText.equals("") || emailText.equals("")) {
            //todo: refactor string
            errorDialog("Please make sure all fields are provided");
        } else {

            emailText = email.getText().toString();
            passwordText = password.getText().toString();

            ParseUser newUser = new ParseUser();

            newUser.setPassword(passwordText);
            newUser.setEmail(emailText);
            newUser.setUsername(emailText);


            ParseACL defaultACL = new ParseACL();
            defaultACL.setPublicReadAccess(true);
            newUser.setACL(defaultACL);

            newUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        //start feed intent
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);

                        ParseObject data = new ParseObject("Dates");
                        data.put("createdBy", ParseUser.getCurrentUser().getObjectId());
                        data.put("Date", new Date());
                        data.saveInBackground();
                        finish();
                    }//if (e ==null)
                    else {
                        errorDialog(e.getMessage());
                    }//else
                }//done
            }//newUser.sign....
            );


        }

    }


    public void errorDialog(String str) {
        //something went wrong!
        //failed
        //create the builder
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage(str);
        //TODO: add string to Strings file
        builder.setTitle("Sign-Up Error");
        builder.setPositiveButton(android.R.string.ok, null);
        //create the dialog
        AlertDialog dialog = builder.create();
        //show the dialog
        dialog.show();
    }//errorDialog



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
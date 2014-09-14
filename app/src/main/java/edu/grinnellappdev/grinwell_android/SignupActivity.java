package edu.grinnellappdev.grinwell_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignupActivity extends ActionBarActivity {


    public class Sign_Up_Activity extends Activity {

        //variable declaration
        EditText password;
        EditText email;
        String usernameText;
        String passwordText;
        String emailText;
        Button signUpButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);

            //get the EditText(s)
            email = (EditText) findViewById(R.id.editText_username);
            password = (EditText) findViewById(R.id.editText_password);

            //get button
            signUpButton = (Button) findViewById(R.id.button_makeaccount);

            //on button click
            signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //get values
                    passwordText = password.getText().toString();
                    emailText = email.getText().toString();
                    //if one or more fields are empty
                    if (usernameText.equals("") || passwordText.equals("") || emailText.equals("")) {
                        errorDialog("Please make sure all fields are provided");
                    }

                    //else all entries are valid
                    else {
                        ParseUser newUser = new ParseUser();
                        newUser.setUsername(usernameText);
                        newUser.setPassword(passwordText);
                        newUser.setEmail(emailText);

                        ParseACL defaultACL = new ParseACL();
                        defaultACL.setPublicReadAccess(true);
                        newUser.setACL(defaultACL);

                        newUser.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    //start feed intent
                                    Intent intent = new Intent(Sign_Up_Activity.this, MainActivity.class);
                                    //clear prev. page so that back button does not go back to the sign up page
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }//if (e ==null)
                                else {
                                    errorDialog(e.getMessage());
                                }//else
                            }//done
                        }//newUser.sign....
                        );
                    }///else
                }//onclick

            }//signupbutton....
            );
        }//onCreate

        public void errorDialog(String str) {
            //something went wrong!
            //failed
            //create the builder
            AlertDialog.Builder builder = new AlertDialog.Builder(Sign_Up_Activity.this);
            builder.setMessage(str);
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
            }// if (id == R.id.action_settings)
            return super.onOptionsItemSelected(item);
        }//onOptionsItemSelected
    }//sign_up_activity
}

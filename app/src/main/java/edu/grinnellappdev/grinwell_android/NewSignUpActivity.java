package edu.grinnellappdev.grinwell_android;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class NewSignUpActivity extends ActionBarActivity {

    //variable declaration
    EditText password;
    EditText email;
    String passwordText;
    String emailText;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sign_up);

        //get the EditText(s)
        email = (EditText) findViewById(R.id.NeditText_username);
        password = (EditText) findViewById(R.id.NeditText_password);

        //get button
        signUpButton = (Button) findViewById(R.id.Nbutton_makeaccount);

        //on button click
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get values
                passwordText = password.getText().toString();
                emailText = email.getText().toString();
                //if one or more fields are empty
                if (passwordText.equals("") || emailText.equals("")) {
                    errorDialog("Please make sure all fields are provided");
                }

                //else all entries are valid
                else {
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
                                Intent intent = new Intent(NewSignUpActivity.this, HomeActivity.class);
                                startActivity(intent);
                                //TODO: finish not working as expected
                                finish();
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
    }

    public void errorDialog(String str) {
        //something went wrong!
        //failed
        //create the builder
        AlertDialog.Builder builder = new AlertDialog.Builder(NewSignUpActivity.this);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

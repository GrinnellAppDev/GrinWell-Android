package edu.grinnellappdev.grinwell_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class NewRelaxActivity extends ActionBarActivity {

    TextView mMeditation, mReading, mKnitting, mPlay, mListen, mOther;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_relax);

        getActionBar().hide();

        mMeditation = (TextView) findViewById(R.id.relax_meditation);
        mMeditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();

            }
        });

        mReading = (TextView) findViewById(R.id.relax_reading);
        mReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        mKnitting = (TextView) findViewById(R.id.relax_knitting);
        mKnitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();

            }
        });


        mPlay = (TextView) findViewById(R.id.relax_play);
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });


        mListen = (TextView) findViewById(R.id.relax_listen);
        mListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        mMeditation = (TextView) findViewById(R.id.relax_meditation);
        mMeditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });








    }


    public void save(){


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dates");
        query.whereEqualTo("User", ParseUser.getCurrentUser());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject data, ParseException e) {

                    data.put("WellnessActivity", true);
                    data.saveInBackground();

                }
            });

        startActivity(new Intent(NewRelaxActivity.this, HomeActivity.class));
        finish();



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_relax, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

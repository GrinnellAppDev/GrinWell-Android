package edu.grinnellappdev.grinwell_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;


public class NewMovementActivity extends Activity {


    FrameLayout mWalk15, mWalk30, mRunning15, mRunning30, mDancing15, mDancing30, mSwimming15, mSwimming30,
    mBiking15, mBiking30, mOther15, mOther30;
    int mWalkTotal = 0, mRunningTotal = 0, mDancingTotal = 0, mSwimmingTotal = 0, mBikingTotal = 0, mOtherTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_movement);

        getActionBar().hide();



        mWalk15 = (FrameLayout) findViewById(R.id.walking_15);
        mWalk30 = (FrameLayout)findViewById(R.id.walking_30);

        mWalk15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mWalkTotal =+ 15;

            }
        });

        mWalk30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mWalkTotal =+ 30;

            }
        });

        mRunning15 =(FrameLayout) findViewById(R.id.running_15);
        mRunning30 = (FrameLayout)findViewById(R.id.running_30);

        mRunning15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mRunningTotal =+ 15;
            }
        });

        mRunning30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mRunningTotal =+ 30;

            }
        });

        mDancing15 = (FrameLayout)findViewById(R.id.dancing_15);
        mDancing30 = (FrameLayout)findViewById(R.id.dancing_30);

        mDancing15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDancingTotal =+ 15;
            }
        });

        mDancing30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDancingTotal =+ 30;
            }
        });

        mSwimming15 = (FrameLayout)findViewById(R.id.swimming_15);
        mSwimming30 = (FrameLayout)findViewById(R.id.swimming_30);

        mSwimming15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mSwimming30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mBiking15 = (FrameLayout)findViewById(R.id.biking_15);
        mBiking30 = (FrameLayout)findViewById(R.id.biking_30);

        mBiking15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mBiking30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mOther15 = (FrameLayout)findViewById(R.id.other_15);
        mOther30 = (FrameLayout)findViewById(R.id.other_30);

        mOther15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mOther30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_movement, menu);
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

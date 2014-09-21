package edu.grinnellappdev.grinwell_android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class NewMovementActivity extends Activity {


    FrameLayout mWalk15, mWalk30, mRunning15, mRunning30, mDancing15, mDancing30, mSwimming15, mSwimming30,
    mBiking15, mBiking30, mOther15, mOther30;
    int mWalkTotal = 0, mRunningTotal = 0, mDancingTotal = 0, mSwimmingTotal = 0, mBikingTotal = 0, mOtherTotal = 0,  mGrandTotal = 0;
    TextView mTotal;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_movement);

        getActionBar().hide();

        mContext = this;

        mTotal = (TextView) findViewById(R.id.mvmt_total);

        mWalk15 = (FrameLayout) findViewById(R.id.walking_15);
        mWalk30 = (FrameLayout)findViewById(R.id.walking_30);

        mWalk15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mWalkTotal =+ 15;
                mGrandTotal = mGrandTotal + 15;
                updateTotal(mGrandTotal);
                Toast.makeText(mContext, "clicked walk 15", Toast.LENGTH_SHORT).show();

            }
        });

        mWalk30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mWalkTotal =+ 30;
                mGrandTotal = mGrandTotal + 30;
                updateTotal(mGrandTotal);
                Toast.makeText(mContext, "clicked walk 30", Toast.LENGTH_SHORT).show();

            }
        });

        mRunning15 =(FrameLayout) findViewById(R.id.running_15);
        mRunning30 = (FrameLayout)findViewById(R.id.running_30);

        mRunning15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mRunningTotal =+ 15;
                mGrandTotal = mGrandTotal + 15;
                updateTotal(mGrandTotal);
            }
        });

        mRunning30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mRunningTotal =+ 30;
                mGrandTotal = mGrandTotal + 30;
                updateTotal(mGrandTotal);

            }
        });

        mDancing15 = (FrameLayout)findViewById(R.id.dancing_15);
        mDancing30 = (FrameLayout)findViewById(R.id.dancing_30);

        mDancing15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDancingTotal =+ 15;
                mGrandTotal = mGrandTotal + 15;
                updateTotal(mGrandTotal);
            }
        });

        mDancing30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDancingTotal =+ 30;
                mGrandTotal = mGrandTotal + 30;
                updateTotal(mGrandTotal);
            }
        });

        mSwimming15 = (FrameLayout)findViewById(R.id.swimming_15);
        mSwimming30 = (FrameLayout)findViewById(R.id.swimming_30);

        mSwimming15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSwimmingTotal =+ 15;
                mGrandTotal = mGrandTotal + 15;
                updateTotal(mGrandTotal);
            }
        });

        mSwimming30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSwimmingTotal =+ 30;
                mGrandTotal = mGrandTotal + 30;
                updateTotal(mGrandTotal);
            }
        });

        mBiking15 = (FrameLayout)findViewById(R.id.biking_15);
        mBiking30 = (FrameLayout)findViewById(R.id.biking_30);

        mBiking15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mBikingTotal =+ 15;
                mGrandTotal = mGrandTotal + 15;
                updateTotal(mGrandTotal);
            }
        });

        mBiking30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mBikingTotal =+ 30;
                mGrandTotal = mGrandTotal + 30;
                updateTotal(mGrandTotal);
            }
        });

        mOther15 = (FrameLayout)findViewById(R.id.other_15);
        mOther30 = (FrameLayout)findViewById(R.id.other_30);

        mOther15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOtherTotal =+ 15;
                mGrandTotal = mGrandTotal + 15;
                updateTotal(mGrandTotal);
            }
        });

        mOther30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mOtherTotal =+ 30;
                mGrandTotal = mGrandTotal + 30;
                updateTotal(mGrandTotal);
            }
        });


    }

    public void updateTotal(int total){

        double grandTotalD = total/60.0;
        mTotal.setText(grandTotalD + " hrs");
        save(grandTotalD);


    }



    public void save(final double total){


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dates");
        query.whereEqualTo("UserID", ParseUser.getCurrentUser().getObjectId());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject data, ParseException e) {

                if (e ==null) {
                    data.put("MovementAmount", total);
                    data.saveInBackground();
                }

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

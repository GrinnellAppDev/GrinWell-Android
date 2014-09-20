package edu.grinnellappdev.grinwell_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;


public class NewSleepActivity extends Activity {

    TimePicker mPicker;
    TextView mPrompt, mWakeTime, mSleepTime;
    FrameLayout mSetButton;
    String AmPm, mSleepHour, mSleepMin, mWakeHour, mWakeMin;
    int mClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        getActionBar().hide();
    

        mSetButton = (FrameLayout) findViewById(R.id.setButton);
        mPrompt = (TextView) findViewById(R.id.Prompt);
        mPicker = (TimePicker) findViewById(R.id.timePicker);
        mSleepTime =  (TextView) findViewById(R.id.asleepTime);
        mWakeTime =  (TextView) findViewById(R.id.wakeTime);

        mSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mClick ++ ;

                if (mClick == 1) {


                    mSleepHour = mPicker.getCurrentHour().toString();
                    mSleepMin = mPicker.getCurrentMinute().toString();

                    String sleepMinString = (Integer.valueOf(mSleepMin) < 10) ? 0 + mSleepMin : mSleepMin;
                    String sleepHourString =  String.valueOf(Integer.valueOf(mSleepHour) % 12);
                    AmPm = (Integer.valueOf(mSleepHour) < 12) ? "AM" : "PM";

                    mSleepTime.setText(sleepHourString + ":" + sleepMinString + " " + AmPm);

                    mPicker.setCurrentHour(0);
                    mPicker.setCurrentMinute(0);

                    mPrompt.setText("What time did you wake up?");

                } else if (mClick == 2){

                    mWakeHour = mPicker.getCurrentHour().toString();
                    mWakeMin = mPicker.getCurrentMinute().toString();


                    String wakeHourString =  String.valueOf(Integer.valueOf(mWakeHour) % 12);
                    String wakeMinString = (Integer.valueOf(mWakeMin) < 10) ? 0 + mWakeMin : mWakeMin;
                    AmPm = (Integer.valueOf(mWakeHour) < 12) ? "AM" : "PM";

                    mWakeTime.setText( wakeHourString + ":" +   wakeMinString + " " + AmPm);

                    mPicker.setCurrentHour(0);
                    mPicker.setCurrentMinute(0);



                   String timeSlept = calculateTime(Integer.parseInt(mSleepHour), Integer.parseInt(mSleepMin),
                            Integer.parseInt(mWakeHour), Integer.parseInt(mWakeMin));


                    mPrompt.setText("You slept for:" + timeSlept);

                }
            }
        });
    }

    public void publicSubmit(){


        finish();
    }


    public String calculateTime(int startHour, int startMin, int endHour, int endMin){


        int hoursSlept = (24 - startHour) + endHour;
        int minsSlept = (startMin - 60) + endMin;

        hoursSlept = (minsSlept < 0) ? hoursSlept - 1 : hoursSlept;
        minsSlept = Math.abs(minsSlept);

        String endRes = hoursSlept + " hours and " + minsSlept + " minute";
        endRes = (minsSlept > 1) ? endRes + "s": endRes;

        return endRes;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sleep, menu);
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

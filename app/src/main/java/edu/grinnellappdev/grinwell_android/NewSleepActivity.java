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

                mSleepHour = mPicker.getCurrentHour().toString();
                mSleepMin = mPicker.getCurrentMinute().toString();

                AmPm = (Integer.valueOf(mSleepHour) < 12) ? "AM" : "PM";
                mSleepMin = (Integer.valueOf(mSleepMin) < 10) ? 0 + mSleepMin: mSleepMin;
                mSleepHour = String.valueOf(Integer.valueOf(mSleepHour) % 12);

                mSleepTime.setText(mSleepHour + ":" + mSleepMin + " " + AmPm);

                mPicker.setCurrentHour(0);
                mPicker.setCurrentMinute(0);
            }
        });
    }

    public void publicSubmit(){
//        int hoursSlept = endTime.getCurrentHour() - startTime.getCurrentHour();
//        if (hoursSlept<0) { hoursSlept += 24; }
//
//        int minutesSlept = endTime.getCurrentMinute() - startTime.getCurrentMinute();
//        if (minutesSlept<0) {
//            minutesSlept += 60;
//            hoursSlept -= 1;
//        }

        //send the sleep info to parse
        finish();
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

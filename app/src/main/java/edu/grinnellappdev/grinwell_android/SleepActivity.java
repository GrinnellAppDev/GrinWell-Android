package edu.grinnellappdev.grinwell_android;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TimePicker;


public class SleepActivity extends ActionBarActivity {

    TimePicker startTime = (TimePicker) findViewById(R.id.timePicker_start);
    TimePicker endTime = (TimePicker) findViewById(R.id.timePicker_end);
    Button buttonSubmit = (Button) findViewById(R.id.button_submit);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
    }

    public void publicSubmit(){
        int hoursSlept = endTime.getCurrentHour() - startTime.getCurrentHour();
        if (hoursSlept<0) { hoursSlept += 24; }

        int minutesSlept = endTime.getCurrentMinute() - startTime.getCurrentMinute();
        if (minutesSlept<0) {
            minutesSlept += 60;
            hoursSlept -= 1;
        }

        //send the sleep info to parse
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

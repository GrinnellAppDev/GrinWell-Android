package edu.grinnellappdev.grinwell_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.Date;


public class NewSleepActivity extends Activity {

    TimePicker mPicker;
    TextView mPrompt, mWakeTime, mSleepTime, mButtonText;
    FrameLayout mSetButton;
    String AmPm, mSleepHour, mSleepMin, mWakeHour, mWakeMin;
    int mClick = 0;
    double finalDiffTime;

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


                    animateFade(mSleepTime, sleepHourString + ":" + sleepMinString + " " + AmPm);

                    mPicker.setCurrentHour(0);
                    mPicker.setCurrentMinute(0);

                   animateFade(mPrompt, "What time did you wake up?");

//                    animateFadeIn(mPrompt, 0);

                } else if (mClick == 2){

                    mWakeHour = mPicker.getCurrentHour().toString();
                    mWakeMin = mPicker.getCurrentMinute().toString();


                    String wakeHourString =  String.valueOf(Integer.valueOf(mWakeHour) % 12);
                    String wakeMinString = (Integer.valueOf(mWakeMin) < 10) ? 0 + mWakeMin : mWakeMin;
                    AmPm = (Integer.valueOf(mWakeHour) < 12) ? "AM" : "PM";


                    animateFade(mWakeTime, wakeHourString + ":" +   wakeMinString + " " + AmPm);

                    mPicker.setCurrentHour(0);
                    mPicker.setCurrentMinute(0);



                   String timeSlept = calculateTime(Integer.parseInt(mSleepHour), Integer.parseInt(mSleepMin),
                            Integer.parseInt(mWakeHour), Integer.parseInt(mWakeMin));

                    animateFade(mPrompt, "You slept for: " + timeSlept);
                    mButtonText = (TextView) findViewById(R.id.sleep_button);
                    animateFade(mButtonText, "Reset");


                    save(finalDiffTime);

                } else if (mClick > 2 ){

                    mClick = 0;
                    animateFade(mButtonText, "Set");
                    animateFade(mPrompt, "When did you fall asleep?");
                    mWakeTime.setText("");
                    mSleepTime.setText("");


                }
            }
        });
    }

    public void save(final double sleepAmount){


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dates");
        query.whereEqualTo("createdBy", ParseUser.getCurrentUser().getObjectId());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject data, ParseException e) {

                if (e == null) {
                    data.put("Sleep", Math.abs(sleepAmount));
                    data.saveInBackground();
                }

            }
        });

//        startActivity(new Intent(NewRelaxActivity.this, HomeActivity.class));
//        finish();



    }


    public void animateFadeIn(View v){

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(400);
        anim.setInterpolator(new AccelerateInterpolator());
        anim.setFillAfter(true);

        v.startAnimation(anim);
    }

    public void animateFade(final TextView v, final String message){

        Animation anim = new AlphaAnimation( 1.0f, 0.0f);
        anim.setDuration(400);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setFillAfter(true);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                animateFadeIn(v);
                v.setText(message);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        v.startAnimation(anim);
    }
    public String calculateTime(int startHour, int startMin, int endHour, int endMin){

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String startTime = startHour + ":" + startMin;
        String endTime = endHour + ":" + endMin;
        String endRes = "";

        try {
            Date date1 = format.parse(startTime);
            Date date2 = format.parse(endTime);
            long diff = date2.getTime() - date1.getTime();

            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;

            finalDiffTime = (diff / (60 * 1000)) / 60.0;


            endRes = Math.abs(diffHours) + " hours and " + Math.abs(diffMinutes) + " minute";

        } catch (Exception e){


        }


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

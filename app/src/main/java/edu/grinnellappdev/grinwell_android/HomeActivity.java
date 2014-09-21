package edu.grinnellappdev.grinwell_android;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.Date;

import static edu.grinnellappdev.grinwell_android.R.id.imageButton_eat;
import static edu.grinnellappdev.grinwell_android.R.id.imageButton_move;
import static edu.grinnellappdev.grinwell_android.R.id.imageButton_relax;
import static edu.grinnellappdev.grinwell_android.R.id.imageButton_sleep;


public class HomeActivity extends Activity {

    TextView statsDate, mKingtonSleepText, mKingtonMovementText, mKingtonEatText, mKingtonWellnessText;
    ImageView mSleepCrown, mEatCrown, mRelaxCrown, mMoveCrown;
    ParseObject mData;
    ParseUser mKington;
    int mKingtonFood = 0;
    double mKingtonSleep = 0.0, mKingtonMovement = 0.0;
    boolean mKingtonWellness = false;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mContext = this;

//        //parse initialize
//        Parse.initialize(this, "lZHvycY7GTVgeq52BwD1fFHlNUKHlMsrN5lrmBUm", "zRCIHNyIYcm2VEPbOgOqrSkkLVFtZOb4vTF9j1Na");
//        ParseAnalytics.trackAppOpened(getIntent());

        LayoutInflater mInflater1 = LayoutInflater.from(this);

        View mMenuView = mInflater1.inflate(R.layout.menu, null, false);

        TextView logout = (TextView) mMenuView.findViewById(R.id.logout_menu);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseUser user = ParseUser.getCurrentUser();
                user.logOut();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));

            }
        });



        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setBehindWidth(600);
        menu.setShadowWidth(100);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setMenu(mMenuView);


        //Get The Button(s)
        ImageView clickSleep = (ImageView) findViewById(imageButton_sleep);
        mSleepCrown = (ImageView) findViewById(R.id.imageButton_sleep_crown);
        clickSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, NewSleepActivity.class));
            }
        });

        ImageView clickEat = (ImageView) findViewById(imageButton_eat);
        mEatCrown = (ImageView) findViewById(R.id.imageButton_eat_crown);

        clickEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, NewEatActivity.class));
            }
        });

        ImageView clickRelax = (ImageView) findViewById(imageButton_relax);
        mRelaxCrown = (ImageView) findViewById(R.id.imageButton_relax_crown);
        clickRelax.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                startActivity(new Intent(HomeActivity.this, NewRelaxActivity.class));
            }
        });

        ImageView clickMove = (ImageView) findViewById(imageButton_move);
        mMoveCrown = (ImageView) findViewById(R.id.imageButton_move_crown);
        clickMove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                startActivity(new Intent(HomeActivity.this, NewMovementActivity.class));
            }
        });


        mKingtonEatText = (TextView) findViewById(R.id.wellness_eat);
        mKingtonMovementText = (TextView) findViewById(R.id.wellness_move);
        mKingtonWellnessText = (TextView) findViewById(R.id.wellness_restore);
        mKingtonSleepText = (TextView) findViewById(R.id.wellness_sleep);
        //set today's date
        statsDate = (TextView) findViewById(R.id.date_text_view);
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("LLL.dd");
        statsDate.setText(ft.format(now));




//        ParseQuery<ParseUser> userQuery = ParseUser.getQuery();
//        userQuery.whereEqualTo("objectId", "EvFCcNYqRu");
//
//        userQuery.getFirstInBackground(new GetCallback<ParseUser>() {
//            @Override
//            public void done(ParseUser parseUser, ParseException e) {
//                if (e == null){
//                    mKington = parseUser;
//                }
//            }
//        });

        ParseQuery<ParseObject> queryKington = ParseQuery.getQuery("Dates");
        queryKington.whereEqualTo("UserID", "EvFCcNYqRu");
        queryKington.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject data, ParseException e) {


                if (e == null) {
                    mKingtonFood = data.getInt("FruitsAndVegetables");
                    mKingtonEatText.setText("Eat: " + mKingtonFood +"/5");

                    mKingtonSleep = data.getDouble("Sleep");
                    mKingtonSleepText.setText("Sleep: " + (int)mKingtonSleep +"hr/8hr");

                    mKingtonMovement = data.getDouble("MovementAmount");
                    mKingtonMovementText.setText("Move: " + mKingtonMovement + "/30");

                    mKingtonWellness = data.getBoolean("WellnessActivity");
                    String setText = (mKingtonWellness) ? "Complete" : "Incomplete";
                    mKingtonWellnessText.setText(setText);

                } else{
                    Toast.makeText(mContext, e.toString(), Toast.LENGTH_LONG).show();
                }


            }
        });

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dates");
        query.whereEqualTo("UserID", ParseUser.getCurrentUser().getObjectId());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject data, ParseException e) {

                mData = data;

                if (data != null) {

                    if (data.getInt("FruitsAndVegetables") >= mKingtonFood) {
                      crownAnimator(mEatCrown);

                    }

                    if (data.getDouble("Sleep") >= mKingtonSleep){

                     crownAnimator(mSleepCrown);


                    }

                    if (data.getDouble("MovementAmount") >= mKingtonMovement){

                        crownAnimator(mMoveCrown);


                    }

                    if(data.getBoolean("WellnessActivity")){
                        crownAnimator(mRelaxCrown);
                    }
                }

            }
        });



        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.actionbar, null, false);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

    }


    public void animator(View v){
        AlphaAnimation anim = new AlphaAnimation(0, 1);
//					ObjectAnimator animator = ObjectAnimator.ofInt(scrollView,
//							"scrollY", 0, mRecipeName.getBottom());
        anim.setDuration(800);

        v.startAnimation(anim);

    }


    public void crownAnimator(ImageView crown){


        crown.setVisibility(View.VISIBLE);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
//					ObjectAnimator animator = ObjectAnimator.ofInt(scrollView,
//							"scrollY", 0, mRecipeName.getBottom());
        anim.setDuration(700);
        anim.setFillAfter(true);

        crown.startAnimation(anim);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
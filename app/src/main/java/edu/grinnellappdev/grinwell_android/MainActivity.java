package edu.grinnellappdev.grinwell_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class MainActivity extends Activity {

    ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Crashlytics.start(this);
        setContentView(R.layout.activity_main);

        getActionBar().hide();
        //parse initialize
        Parse.initialize(this, "lZHvycY7GTVgeq52BwD1fFHlNUKHlMsrN5lrmBUm", "zRCIHNyIYcm2VEPbOgOqrSkkLVFtZOb4vTF9j1Na");
        ParseAnalytics.trackAppOpened(getIntent());




        user = ParseUser.getCurrentUser();

        //TODO: navigate to login page
        if (user==null){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        //navigate to home screen
        else {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
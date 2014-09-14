package edu.grinnellappdev.grinwell_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class EatActivity extends Activity {

    int counter = 0;//TODO: THIS SHOULD PULL FROM PARSE
    //TextView textviewCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat);
    //    textviewCounter = (TextView) findViewById(R.id.textView_counter);
    }

//   public void clickAdd(){
//       counter++;
//       textviewCounter.setText("You've eaten " + counter + " piece(s) of fruit today.");
//   }
//
//
//    public void clickSubmit(){
//        //send the sleep info to parse
//        finish();
//    }

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

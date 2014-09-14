package edu.grinnellappdev.grinwell_android;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class RelaxActivity extends ActionBarActivity {

    int counter = 0; //TODO: THIS SHOULD PULL FROM PARSE
    TextView textviewCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relax);
        textviewCounter = (TextView) findViewById(R.id.textView_counter);
    }

    public void clickAdd(){
        counter++;
        textviewCounter.setText("You've performed " + counter + " relaxing activity today.");
    }

    public void onClick(){
        //send to parse
        finish()
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.relax, menu);
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

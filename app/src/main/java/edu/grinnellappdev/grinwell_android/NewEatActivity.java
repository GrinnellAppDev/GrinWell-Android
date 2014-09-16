package edu.grinnellappdev.grinwell_android;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class NewEatActivity extends ActionBarActivity {

    ImageView vegButton;
    ImageView fruitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_eat);

        vegButton = (ImageView) findViewById(R.id.add_veg_button);
        vegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //increment the value of the vegies eaten
                //update database
            }
        });

        fruitButton = (ImageView) findViewById(R.id.add_fruit_button);
        fruitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //increment the value of the fruits eaten
                //update database
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_eat, menu);
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

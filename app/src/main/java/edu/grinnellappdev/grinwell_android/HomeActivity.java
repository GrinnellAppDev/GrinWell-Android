package edu.grinnellappdev.grinwell_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import static edu.grinnellappdev.grinwell_android.R.id.imageButton_eat;
import static edu.grinnellappdev.grinwell_android.R.id.imageButton_move;
import static edu.grinnellappdev.grinwell_android.R.id.imageButton_relax;
import static edu.grinnellappdev.grinwell_android.R.id.imageButton_sleep;


public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Get The Button(s)
        ImageButton clickSleep = (ImageButton) findViewById(imageButton_sleep);
        clickSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SleepActivity.class));
            }
        });

        ImageButton clickEat = (ImageButton) findViewById(imageButton_eat);
        clickEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, NewEatActivity.class));
            }
        });

        ImageButton clickRelax = (ImageButton) findViewById(imageButton_relax);
        clickRelax.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                startActivity(new Intent(HomeActivity.this, NewRelaxActivity.class));
            }
        });

        ImageButton clickMove = (ImageButton) findViewById(imageButton_move);
        clickMove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                startActivity(new Intent(HomeActivity.this, NewMovementActivity.class));
            }
        });


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
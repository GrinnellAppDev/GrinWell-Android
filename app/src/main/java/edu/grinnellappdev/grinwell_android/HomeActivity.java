package edu.grinnellappdev.grinwell_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import static edu.grinnellappdev.grinwell_android.R.id.imageButton_eat;
import static edu.grinnellappdev.grinwell_android.R.id.imageButton_move;
import static edu.grinnellappdev.grinwell_android.R.id.imageButton_relax;
import static edu.grinnellappdev.grinwell_android.R.id.imageButton_sleep;


public class HomeActivity extends Activity {

    TextView statsDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Get The Button(s)
        ImageView clickSleep = (ImageView) findViewById(imageButton_sleep);
        clickSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, NewSleepActivity.class));
            }
        });

        ImageView clickEat = (ImageView) findViewById(imageButton_eat);
        clickEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, NewEatActivity.class));
            }
        });

        ImageView clickRelax = (ImageView) findViewById(imageButton_relax);
        clickRelax.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                startActivity(new Intent(HomeActivity.this, NewRelaxActivity.class));
            }
        });

        ImageView clickMove = (ImageView) findViewById(imageButton_move);
        clickMove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                startActivity(new Intent(HomeActivity.this, NewMovementActivity.class));
            }
        });

        //set today's date
        statsDate = (TextView) findViewById(R.id.date_text_view);
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("MM.dd");
        statsDate.setText(ft.format(now));

        //todo: put text value into strings file

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
package edu.grinnellappdev.grinwell_android;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class NewEatActivity extends ActionBarActivity {

    ImageView vegButton, fruitButton;
    TextView mTotal;
    int numFruitAndVeg = 0;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_eat);

        getActionBar().hide();

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dates");
        query.whereEqualTo("UserID", ParseUser.getCurrentUser().getObjectId());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject data, ParseException e) {

                if (e==null) {
                    numFruitAndVeg = data.getInt("FruitsAndVegetables");

                    mTotal.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mTotal.setText(numFruitAndVeg + "/5");
                            mProgressBar.setVisibility(View.INVISIBLE);
                        }
                    }, 1000);

                }


            }
        });


        mTotal = (TextView) findViewById(R.id.total_eaten);
        vegButton = (ImageView) findViewById(R.id.add_veg_button);
        vegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numFruitAndVeg++;
                mTotal.setText(numFruitAndVeg + "/5");

                save(numFruitAndVeg);
            }
        });

        fruitButton = (ImageView) findViewById(R.id.add_fruit_button);
        fruitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                numFruitAndVeg++;
                mTotal.setText(numFruitAndVeg + "/5");

                save(numFruitAndVeg);
            }
        });
    }

    public void save(final int total){


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dates");
        query.whereEqualTo("UserID", ParseUser.getCurrentUser().getObjectId());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject data, ParseException e) {


                if (e == null) {
                    data.put("FruitsAndVegetables", total);
                    data.saveInBackground();
                }
                else{
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

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

package edu.floridapoly.mobiledev.crimetrackerapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class MainScreenActivity extends AppCompatActivity {

private Boolean status = TRUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        if (status == TRUE) {
            FirstRun test = new FirstRun(getApplicationContext());
            test.onStart(status);
            status = FALSE;
        }
        TextView activities_text = (TextView) findViewById(R.id.home_activities);
        activities_text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainScreenActivity.this, ActivityListActivity.class));
            }
        });

        TextView map_text = (TextView) findViewById(R.id.home_map);
        map_text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainScreenActivity.this, MapActivity.class));
            }
        });

        TextView settings_text = (TextView) findViewById(R.id.home_settings);
        settings_text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainScreenActivity.this, SettingsActivity.class));
            }
        });

        DatabaseHelper dbOne = new DatabaseHelper(this);
        dbOne.onUpgrade(dbOne.getWritableDatabase(), 1,3);
        int test = (int)dbOne.insertActivity("test",5,4,"5/5/5",1,"test activity");
        int status = (dbOne.insertClassification("police"));
        //Log.d("neclassificationcreated",String.valueOf(status));

        Log.d("insert into activity",String.valueOf(test));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }
}

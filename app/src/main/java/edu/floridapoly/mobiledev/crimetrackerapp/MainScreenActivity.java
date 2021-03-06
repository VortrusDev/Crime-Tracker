package edu.floridapoly.mobiledev.crimetrackerapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class MainScreenActivity extends AppCompatActivity {

private Boolean status = TRUE;
private DatabaseHelper dbOne;
private Bundle dNuts;
ArrayList<crimeActivity> test = new ArrayList<crimeActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dNuts = new Bundle();
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
                dNuts.putParcelable("activities",dbOne.getAll());
                startActivity(new Intent(MainScreenActivity.this, MapsActivity.class).putExtra("databae",dNuts));

            }
        });

        TextView settings_text = (TextView) findViewById(R.id.home_settings);
        settings_text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainScreenActivity.this, SettingsActivity.class));
            }
        });

        dbOne = new DatabaseHelper(this);
        dbOne.onUpgrade(dbOne.getWritableDatabase(), 1,4);
        int status = (dbOne.insertClassification("police"));

        Log.d("neclassificationcreated",String.valueOf(status));

        Log.d("databae",String.valueOf(test));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }
}

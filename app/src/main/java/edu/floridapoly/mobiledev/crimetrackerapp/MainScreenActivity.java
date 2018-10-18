package edu.floridapoly.mobiledev.crimetrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }
}

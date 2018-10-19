package edu.floridapoly.mobiledev.crimetrackerapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.content.Context.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class FirstRun extends Application {

    private String fileName, fileContents;
    private JSONObject defaultSettings;
    private Context test;

    private OutputStreamWriter outputStreamWriter;
    private FileOutputStream fos;
    public FirstRun(Context applicationContext) {
        Log.d("appLaunch","FirstRun was found and is running");
        test = applicationContext;

    }


    public void onStart(Boolean status) {


        defaultSettings = new JSONObject();
        fileName = "defaultSettings"+".json";

        if (status == TRUE) {
            try {
                defaultSettings.put("pushNotif", TRUE);
                defaultSettings.put("inAppNotif", FALSE);
                defaultSettings.put("showCrime", FALSE);
                defaultSettings.put("showPolice", FALSE);
                defaultSettings.put("showTraffic", FALSE);
                Log.d("write status", "json file created");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            fileContents = defaultSettings.toString();
                Log.d("write status", "writing json file");

            try {


                fos = test.openFileOutput(fileName, Context.MODE_PRIVATE);

                fos.write(fileContents.getBytes());
                fos.close();
                Log.d("write status","finished writing file");
            } catch (IOException e) {
                Log.e("ERROR", e.toString());
            }

        }

    else

    {
        Log.d("appLaunch","App was already started");
    }
    }
    }



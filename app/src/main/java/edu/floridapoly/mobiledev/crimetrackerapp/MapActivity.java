package edu.floridapoly.mobiledev.crimetrackerapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.location.Location;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapActivity extends AppCompatActivity {


    //MapActivity mapActivity = new MapActivity();

    private static final int PERMISSIONS_REQUEST_COARSE_LOCATION = 1;
    private static final int MINIMUM_TIME = 10000; //10 seconds
    private static final int MIN_DISTANCE = 50; //50 meters

    private FusedLocationProviderClient client; //used to grab location
    private Task lastLocation; //Task used to grab last location
    public static String providerName = ""; //provider used in the grab for location
    public  LocationManager LocationManager; //location manager which manages all things to do with this

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (LocationManager == null)
        {
            Log.d("broke","A gigantic middle finger");
        }
        client = LocationServices.getFusedLocationProviderClient(this);

        try {
            client.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                            }
                        }
                    });
        } catch (SecurityException exception) {
            //DO stuff here if they rejected that permission
        }
        Context context = getApplicationContext();
        Criteria criteria = new Criteria();
        providerName = LocationManager.getBestProvider(criteria, true); //get best provider
        //out of passive,
        //network, and gps
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);
        getSystemService(context.LOCATION_SERVICE); //access location services using the device
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //no provider given. Prompt for GPS
            if (providerName == null || providerName.equals("")) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }

            lastLocation = client.getLastLocation();
        } else {
            //We need the permission. Prompt for permission.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Toast.makeText(context, "The app must access your location.",
                        Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSIONS_REQUEST_COARSE_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSIONS_REQUEST_COARSE_LOCATION);
            }

        }

    }




    public static void CheckCoarseLocationPermission(Context context, Activity activity)
    {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //no provider given. Prompt for GPS
            if (providerName == null || providerName.equals("")) {
                context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }
        else {
            //We need the permission. Prompt for permission.
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Toast.makeText(context, "The app must access your location.",
                        Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSIONS_REQUEST_COARSE_LOCATION);
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        PERMISSIONS_REQUEST_COARSE_LOCATION);
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[],int grantedResults[]){
        switch (requestCode)
        {
            case PERMISSIONS_REQUEST_COARSE_LOCATION: {
                if (grantedResults.length > 0 && grantedResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permission granted, do location stuffs
                    try {
                        lastLocation = client.getLastLocation(); //
                    }
                    catch (SecurityException e)
                    {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                PERMISSIONS_REQUEST_COARSE_LOCATION);
                    }
                }
                else
                {
                    //Disable map related stuff here
                }
                return;
            }
        }
    }

}
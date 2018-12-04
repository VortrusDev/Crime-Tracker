package edu.floridapoly.mobiledev.crimetrackerapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.location.LocationManager;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import static java.util.jar.Pack200.Unpacker.TRUE;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Bundle deezN, test;
    private ParcelThing thing;
    private LatLng cords, currentLoc;
    private Double latitude, longitude;
    private String activityName;
    private JsonManipulating json;
    private MapActivity map;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location curLocation;
    private String lat,lng;
    private FusedLocationProviderClient mFusedLocationClient;
    private Criteria criteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Location", location.toString());
                lat = String.valueOf(location.getLatitude());
                lng = String.valueOf(location.getLongitude());
                //currentLoc = new LatLng(lat,lng);

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
            return;
        }
        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locationListener);


        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locationListener);

            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

  try {
      mFusedLocationClient.getLastLocation()
              .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                  @Override
                  public void onSuccess(Location location) {
                      // Got last known location. In some rare situations this can be null.


                      if (location != null) {
                          // Logic to handle location object

                      }
                  }
              });

  }
  catch(SecurityException e){

  }

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, locationListener);
            Location location = new Location(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
            currentLoc = new LatLng(location.getLatitude(),location.getLongitude());

        }catch(SecurityException e){}

        json = new JsonManipulating();
        map = new MapActivity();
        Log.d("location","Lat: " + lat + "Lng: " + lng);

        json.loadJson(this, "defaultSettings.json");
     //  currentLoc = new LatLng(0,0);
        mMap = googleMap;
        deezN = getIntent().getBundleExtra("databae");

        thing = deezN.getParcelable("activities");

        //Try building I guess


        mMap.addMarker(new MarkerOptions().position(currentLoc).title("Current Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLoc, 14));

        for (int i = 0; i < thing.size(); i++) {
            Log.d("json", "current lat and long " + String.valueOf(latitude));
            Log.d("json", json.getActivityStatus("showCrime"));
            Log.d("json", thing.get(i).getActivityClassification());


            Log.d("jsons", thing.get(i).getActivityClassification() + "//" + thing.get(i).getLatitude());
            if (thing.get(i).getActivityClassification().equals("0") && json.getActivityStatus("showCrime").equals("true")) {
                latitude = thing.get(i).getLatitude();
                longitude = thing.get(i).getLongitude();
                cords = new LatLng(latitude, longitude);

                mMap.addMarker(new MarkerOptions().position(cords).title("Criminal Incident"));


            } else if (thing.get(i).getActivityClassification().equals("1") && json.getActivityStatus("showTraffic").equals("true")) {
                latitude = thing.get(i).getLatitude();
                longitude = thing.get(i).getLongitude();
                cords = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(cords).title("Traffic Incident"));
            } else if (thing.get(i).getActivityClassification().equals("2") && json.getActivityStatus("showPolice").equals("true")) {
                latitude = thing.get(i).getLatitude();
                longitude = thing.get(i).getLongitude();
                cords = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(cords).title("Police Incident"));
            }
        }


    }

}


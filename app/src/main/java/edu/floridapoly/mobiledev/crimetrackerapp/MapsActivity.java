package edu.floridapoly.mobiledev.crimetrackerapp;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.location.LocationManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static java.util.jar.Pack200.Unpacker.TRUE;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Bundle deezNuts, test;
    private ParcelThing thing;
    private LatLng cords, currentLoc;
    private Double latitude, longitude;
    private String activityName;
    private JsonManipulating json;
    private MapActivity map;
    private Location loc;
    private LocationListener listener;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        LocationManager locMan;
        locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        json = new JsonManipulating();
        map = new MapActivity();
       // MapActivity.getLastKnown
        json.loadJson(this,"defaultSettings.json");

        mMap = googleMap;
        deezNuts = getIntent().getBundleExtra("databae");

        thing = deezNuts.getParcelable("activities");

      //  Log.d("datas",String.valueOf(thing.size())); hi

    //    Log.d("datas","stuff   "+ thing.get(0).getLatitude());
        Criteria crit = new Criteria();


   //     try {
            //How about we run some tests here to see what part is failing.
         //   locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listener);
                //now?
       //     loc = new Location(locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER));         }
      //  catch (SecurityException e)
        {
      //      MapActivity.CheckCoarseLocationPermission(this,this);
        }
     //   catch (NullPointerException e)
        {
     //       Log.d("NullPointerException", "Yea, apparently it throws that"); //Run it all fancy if you will
        }

        //Try building I guess

        currentLoc = new LatLng(28.1512,-81.850438);
        mMap.addMarker(new MarkerOptions().position(currentLoc).title("Current Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLoc,14));

        for(int i=0; i < thing.size();i++){
            Log.d("json","current lat and long " + String.valueOf(latitude));
            Log.d("json",json.getActivityStatus("showCrime"));
            Log.d("json",thing.get(i).getActivityClassification());


            Log.d("jsons",thing.get(i).getActivityClassification() + "//" + thing.get(i).getLatitude());
            if (thing.get(i).getActivityClassification().equals("0") && json.getActivityStatus("showCrime").equals("true") ){
                latitude = thing.get(i).getLatitude() ;
                longitude = thing.get(i).getLongitude() ;
                cords = new LatLng(latitude,longitude);

                mMap.addMarker(new MarkerOptions().position(cords).title("Criminal Incident"));


            }

            else if(thing.get(i).getActivityClassification().equals("1") && json.getActivityStatus("showTraffic").equals("true")){
                latitude = thing.get(i).getLatitude() ;
                longitude = thing.get(i).getLongitude() ;
                cords = new LatLng(latitude,longitude);
                mMap.addMarker(new MarkerOptions().position(cords).title("Traffic Incident"));
            }

            else if(thing.get(i).getActivityClassification().equals("2") && json.getActivityStatus("showPolice").equals("true")){
                latitude = thing.get(i).getLatitude() ;
                longitude = thing.get(i).getLongitude() ;
                cords = new LatLng(latitude,longitude);
                mMap.addMarker(new MarkerOptions().position(cords).title("Police Incident"));
            }
        }





    }


}

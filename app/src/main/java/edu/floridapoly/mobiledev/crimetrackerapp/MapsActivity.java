package edu.floridapoly.mobiledev.crimetrackerapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static java.util.jar.Pack200.Unpacker.TRUE;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Bundle deezNuts;
    private ParcelThing thing;
    private LatLng cords;
    private Double latitude, longitude;
    private String activityName;
    private JsonManipulating json;

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
        json = new JsonManipulating();
        json.loadJson(this,"defaultSettings.json");

        mMap = googleMap;
        deezNuts = getIntent().getBundleExtra("databae");

        thing = deezNuts.getParcelable("activities");

        Log.d("datas",String.valueOf(thing.size()));

        Log.d("datas","stuff   "+ thing.get(0).getLatitude());


        LatLng lakeland = new LatLng(28.04562871062823,-81.93608683964845);
        mMap.addMarker(new MarkerOptions().position(lakeland).title("you"));

        for(int i=0; i < thing.size();i++){
            Log.d("json","current lat and long " + String.valueOf(latitude));
            Log.d("json",json.getActivityStatus("showCrime"));
           // mMap.addMarker(new MarkerOptions().position(cords).title(activityName+ String.valueOf(i)));

            Log.d("jsons",thing.get(i).getActivityClassification() + "//" + thing.get(i).getLatitude());
            if (thing.get(i).getActivityClassification().equals("1") && json.getActivityStatus("showCrime").equals("true") ){
                latitude = thing.get(i).getLatitude() + i + .6 ;
                longitude = thing.get(i).getLongitude() + i + .6;
                cords = new LatLng(latitude,longitude);
                Log.d("mapmark","hit");
                mMap.addMarker(new MarkerOptions().position(cords).title(activityName+ String.valueOf(i)));


            }

            else if(thing.get(i).getActivityClassification().equals("2") && json.getActivityStatus("showTraffic").equals("true")){
                latitude = thing.get(i).getLatitude() ;
                longitude = thing.get(i).getLongitude() ;
                cords = new LatLng(latitude,longitude);
                mMap.addMarker(new MarkerOptions().position(cords).title(activityName + String.valueOf(i)));
            }

            else if(thing.get(i).getActivityClassification().equals("3") && json.getActivityStatus("showPolice").equals("true")){
                latitude = thing.get(i).getLatitude() ;
                longitude = thing.get(i).getLongitude() ;
                cords = new LatLng(latitude,longitude);
                mMap.addMarker(new MarkerOptions().position(cords).title(activityName));
            }
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(lakeland));



    }
}

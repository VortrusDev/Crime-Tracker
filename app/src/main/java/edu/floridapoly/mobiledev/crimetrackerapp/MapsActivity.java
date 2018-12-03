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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Bundle deezNuts;
    private ParcelThing thing;
    private LatLng cords;
    private Integer latitude, longitude;
    private String activityName;

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
        mMap = googleMap;

        deezNuts = getIntent().getBundleExtra("databae");

        thing = deezNuts.getParcelable("activities");
        Log.d("datas",String.valueOf(thing.size()));

        Log.d("datas","stuff   "+ thing.get(0).getLatitude());


        // Add a marker in Sydney and move the camera
        LatLng lakeland = new LatLng(28.0395,81.9498);

        for(int i=0; i < thing.size();i++){
            latitude = thing.get(i).getLatitude() + i;
            longitude = thing.get(i).getLongitude() + i;
            activityName = thing.get(i).getActivityName() + " " + String.valueOf(i);

            cords = new LatLng(latitude , longitude);

            mMap.addMarker(new MarkerOptions().position(cords).title(activityName));
        }
       // mMap.addCircle( new CircleOptions().center(lakeland).radius(10));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lakeland));



    }
}

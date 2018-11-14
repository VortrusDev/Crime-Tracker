package edu.floridapoly.mobiledev.crimetrackerapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

public class Settings_Activities_Fragment extends Fragment {

     private CheckBox crimeBox,policeBox,trafficBox;
     ButtonChange boxChange;
    @Nullable
    @Override


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_activities, container, false);

         crimeBox = view.findViewById(R.id.settings_crime_checkbox);
         policeBox = view.findViewById(R.id.settings_police_checkbox);
         trafficBox = view.findViewById(R.id.settings_traffic_checkbox);

         crimeBox.setOnClickListener(new View.OnClickListener(){
                         @Override
            public void onClick(View view) {
             boxChange.checkBoxStatus( crimeBox.isChecked(),"crime");


            }

         });
         policeBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                boxChange.checkBoxStatus(policeBox.isChecked(), "police");

            }

        });

         trafficBox.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view) {

                 boxChange.checkBoxStatus(trafficBox.isChecked(),"traffic");

             }

         });

        return view;
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        Activity activity = (Activity) context;



        try{
            boxChange = (ButtonChange) activity;
        }catch (ClassCastException e){
            Log.e("error","must override ButtonChange");
        }
    }


}

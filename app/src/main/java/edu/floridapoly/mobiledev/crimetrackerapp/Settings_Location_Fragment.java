package edu.floridapoly.mobiledev.crimetrackerapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Settings_Location_Fragment extends Fragment {
    private TextInputEditText zipCode;
    private ZipcodeChange zipChange;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_location, container, false);

        zipCode = view.findViewById(R.id.zipcodeInput);

        zipCode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                zipChange.grabZipcode(zipCode.getText().toString());


            }

        });
        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        Activity activity = (Activity) context;



        try{
            zipChange = (ZipcodeChange) activity;
        }catch (ClassCastException e){
            Log.e("error","must override ButtonChange");
        }
    }
}

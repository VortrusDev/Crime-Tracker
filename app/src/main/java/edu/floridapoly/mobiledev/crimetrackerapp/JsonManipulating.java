package edu.floridapoly.mobiledev.crimetrackerapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class JsonManipulating {

    private File file;
    private String ret,receiveString;
    private StringBuilder stringBuilder;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private JSONObject jObject;
    private Boolean status;


    public void loadJson(Context context, String fileName){

        file = new File(context.getFilesDir(), fileName);

      ret = "";
        try {
            InputStream inputStream = new FileInputStream(file);

            if ( inputStream != null ) {
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                receiveString = "";
                stringBuilder = new StringBuilder();
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("FileToJson", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("FileToJson", "Can not read file: " + e.toString());
        }

        try {
            jObject = new JSONObject(ret);
        }
        catch(JSONException e){
            Log.d("json","error when loading json string");
        }

    }

    public void changeActivityStatus(Boolean status, String activityName){

        try {
            jObject.put(activityName,status);
        }
        catch(JSONException e){
            Log.d("json","error when editing settings");
        }

        Log.d("json",activityName + String.valueOf(status) + " updated to current settings");
    }

    public void changLocation(String jsonIndex, String zipCode){
        try {
            jObject.put(jsonIndex,zipCode);
        }
        catch(JSONException e){
            Log.d("json","error when editing settings");
        }

        Log.d("json","location " + zipCode + " updated to current settings");
    }


    public String getSettings(){
        return jObject.toString();
    }

    public Boolean getStatus(){
        if (jObject == null){
            status = FALSE;

        }
        else if (jObject != null){
            status = TRUE;
        }

        return status;
    }
}

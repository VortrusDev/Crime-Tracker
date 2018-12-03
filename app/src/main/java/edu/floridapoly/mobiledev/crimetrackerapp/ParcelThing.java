package edu.floridapoly.mobiledev.crimetrackerapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ParcelThing extends ArrayList<crimeActivity> implements Parcelable {


    public ParcelThing() {
    }

    public ParcelThing(Parcel in) {
        readFromParcel(in);
    }


    @Override
    public int describeContents() {
        return 0;
    }



    @SuppressWarnings("unchecked")

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public ParcelThing createFromParcel(Parcel in) {

            return new ParcelThing(in);

        }

        public Object[] newArray(int arg0) {

            return null;

        }
    };

    private void readFromParcel(Parcel in) {

        this.clear();



        //First we have to read the list size

        int size = in.readInt();



        //Reading remember that we wrote first the Name and later the Phone Number.

        //Order is fundamental



        for (int i = 0; i < size; i++) {

            crimeActivity c = new crimeActivity();

            c.setActivityName(in.readString());

            c.setLongitude(in.readInt());

            c.setLatitude(in.readInt());

            this.add(c);

        }



    }

    public void writeToParcel(Parcel dest, int flags) {

        int size = this.size();

        //We have to write the list size, we need him recreating the list

        dest.writeInt(size);

        //We decided arbitrarily to write first the Name and later the Phone Number.

        for (int i = 0; i < size; i++) {

            crimeActivity c = this.get(i);

            dest.writeString(c.getActivityName());

            dest.writeInt(c.getLongitude());

            dest.writeInt(c.getLatitude());

        }

    }


}

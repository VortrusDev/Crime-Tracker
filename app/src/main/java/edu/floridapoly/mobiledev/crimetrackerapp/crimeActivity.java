package edu.floridapoly.mobiledev.crimetrackerapp;

public class crimeActivity {

    private int crimeId;
    private double latitude, longitude;
    private String activityName, activitySummary, activityDate,activityClassification;

    public crimeActivity() {
    }

    public crimeActivity(String name,double longitude, double latitude,String classification){
        setActivityName(name);
        setLatitude(latitude);
        setLongitude(longitude);
        setActivityClassification(classification);
    }

    public crimeActivity(String name, String summary, String classification, double longitude, double latitude, String date, int crimeId){
        setActivityName(name);
        setActivitySummary(summary);
        setActivityClassification(classification);
        setLatitude(latitude);
        setLongitude(longitude);
        setActivityDate(date);
        setCrimeId(crimeId);
    }


    public String getActivityClassification() {
        return activityClassification;
    }

    public void setActivityClassification(String activityClassification) {
        this.activityClassification = activityClassification;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }


    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivitySummary() {
        return activitySummary;
    }

    public void setActivitySummary(String activitySummary) {
        this.activitySummary = activitySummary;
    }

    public int getCrimeId() {
        return crimeId;
    }

    public void setCrimeId(int crimeId) {
        this.crimeId = crimeId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

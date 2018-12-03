package edu.floridapoly.mobiledev.crimetrackerapp;

public class crimeActivity {

    private int crimeId, latitude, longitude;
    private String activityName, activitySummary, activityDate,activityClassification;

    public crimeActivity() {
    }

    public crimeActivity(String name,int longitude, int latitude){
        setActivityName(name);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public crimeActivity(String name, String summary, String classification, int longitude, int latitude, String date, int crimeId){
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

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
}

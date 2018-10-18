package edu.floridapoly.mobiledev.crimetrackerapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ActivityListActivity extends AppCompatActivity {
    public boolean tableInitialized = false; //please keep needed to verify if table has already been set up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);
        AddCrimeToTable("crash","Lakeland","low","12 am");// test data
        AddCrimeToTable("robbery","Orlando","high","6 pm");
        AddCrimeToTable("fire","Tampa","Medium","OnGoing");
        AddCrimeToTable("crash","Lakeland","low","12 am");
        AddCrimeToTable("robbery","Orlando","high","6 pm");
        AddCrimeToTable("fire","Tampa","Medium","OnGoing");
        AddCrimeToTable("crash","Lakeland","low","12 am");
        AddCrimeToTable("robbery","Orlando","high","6 pm");
        AddCrimeToTable("fire","Tampa","Medium","OnGoing");
    }
    
    public void AddCrimeToTable(String type,String location,String severity,String time){
        //Method adds crime to table called "crimeTableList"
        // any question regarding this code please ask Daniel
        TableLayout table = (TableLayout) findViewById(R.id.crimeTableList);
        table.setPadding(30,30,30,30);
        TableRow headerRow = new TableRow(this);
        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        ////////////////////////
        if(!tableInitialized){
            TextView header1 = new TextView(this);
            header1.setText("Type");
            TextView header2 = new TextView(this);
            header2.setText("Location");
            TextView header3 = new TextView(this);
            header3.setText("Severity");
            TextView header4 = new TextView(this);
            header4.setText("Time");
            // --> formating
            header1.setTextSize(20);
            header2.setTextSize(20);
            header3.setTextSize(20);
            header4.setTextSize(20);
            header1.setPadding(0,0,60,0);
            header2.setPadding(0,0,60,0);
            header3.setPadding(0,0,60,0);
            headerRow.addView(header1);
            headerRow.addView(header2);
            headerRow.addView(header3);
            headerRow.addView(header4);
            headerRow.setPadding(0,0,0,10);
            headerRow.setBackgroundResource(R.drawable.crime_table_border);
            table.addView(headerRow);
            tableInitialized = true;
        }
        /////////////////////////
        TextView test1 = new TextView(this);
        test1.setText(type);
        TextView test2 = new TextView(this);
        test2.setText(location);
        TextView test3 = new TextView(this);
        test3.setText(severity);
        TextView test4 = new TextView(this);
        test4.setText(time);
        row.addView(test1);
        row.addView(test2);
        row.addView(test3);
        row.addView(test4);
        //////////////////////
        row.setBackgroundResource(R.drawable.crime_table_border);
        table.addView(row);

    }
}

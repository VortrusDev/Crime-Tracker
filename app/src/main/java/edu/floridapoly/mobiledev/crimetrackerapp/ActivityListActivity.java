package edu.floridapoly.mobiledev.crimetrackerapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ActivityListActivity extends AppCompatActivity {
    public boolean tableInitialized = false; //please keep needed to verify if table has already been set up
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);
<<<<<<< HEAD
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
=======
        AddCrimeToTable("crash","Lakeland","10/1/18");// test data
        AddCrimeToTable("robbery","Orlando","10/1/18");
        AddCrimeToTable("fire","Tampa","10/2/18");
        AddCrimeToTable("crash","Lakeland","10/2/18");
        AddCrimeToTable("robbery","Orlando","10/2/18");
        AddCrimeToTable("fire","Tampa","10/3/18");
        AddCrimeToTable("crash","Lakeland","10/4/18");
        AddCrimeToTable("robbery","Orlando","10/6/18");
        AddCrimeToTable("fire","Tampa","10/10/18");
    }
    
    public void AddCrimeToTable(String type,String location,String date){
>>>>>>> nb-ui-branch
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
<<<<<<< HEAD
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
=======
            header1.setTextColor((getResources().getColor(((R.color.secondary_color)))));
            header1.setText("Activity");
            TextView header2 = new TextView(this);
            header2.setTextColor((getResources().getColor(((R.color.secondary_color)))));
            header2.setText("Location");
            TextView header4 = new TextView(this);
            header4.setTextColor((getResources().getColor(((R.color.secondary_color)))));
            header4.setText("Date");
            // --> formating
            header1.setTextSize(30);
            header2.setTextSize(30);
            header4.setTextSize(30);
            header1.setPadding(0,0,60,0);
            header2.setPadding(0,0,60,0);
            headerRow.addView(header1);
            headerRow.addView(header2);
>>>>>>> nb-ui-branch
            headerRow.addView(header4);
            headerRow.setPadding(0,0,0,10);
            headerRow.setBackgroundResource(R.drawable.crime_table_border);
            table.addView(headerRow);
            tableInitialized = true;
        }
        /////////////////////////
        TextView test1 = new TextView(this);
<<<<<<< HEAD
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
=======
        test1.setTextColor(getResources().getColor((R.color.white_text_color)));
        test1.setText(type);
        TextView test2 = new TextView(this);
        test2.setTextColor(getResources().getColor((R.color.white_text_color)));
        test2.setText(location);
        TextView test4 = new TextView(this);
        test4.setTextColor(getResources().getColor((R.color.white_text_color)));
        test4.setText(date);
        row.addView(test1);
        row.addView(test2);
>>>>>>> nb-ui-branch
        row.addView(test4);
        //////////////////////
        row.setBackgroundResource(R.drawable.crime_table_border);
        table.addView(row);

    }
}

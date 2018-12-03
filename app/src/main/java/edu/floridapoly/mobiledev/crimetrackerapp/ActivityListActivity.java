package edu.floridapoly.mobiledev.crimetrackerapp;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ActivityListActivity extends AppCompatActivity {
    public boolean tableInitialized = false; //please keep needed to verify if table has already been set up
    public static String[][] crimeArray;//needed for Gathering Crime Data
    public static Boolean crimeArrayUpdated = false;//needed for Gathering Crime Data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_screen);
        getAllData();


        while (true) {
            if (crimeArrayUpdated) {
                System.out.println("\n-----------------------------");
                System.out.println("CrimeArray has been updated");
                System.out.println("-----------------------------");
                String p1 = "", p2 = "", p3 = "", p4 = "", p5 = "";
                int part = 0;
                for (int x = 0; x < crimeArray.length; x++) {
                    for (int y = 0; y < crimeArray[x].length; y++) {
                        System.out.print(crimeArray[x][y] + "  |  ");
                        if (part == 0) {
                            p1 = crimeArray[x][y];
                        } else if (part == 1) {
                            p2 = crimeArray[x][y];
                        } else if (part == 2) {
                            p3 = crimeArray[x][y];
                        } else if (part == 3) {
                            p4 = crimeArray[x][y];
                        } else if (part == 4) {
                            p5 = crimeArray[x][y];
                            part = -1;
                            AddCrimeToTable(p1, p2, p3, p4, p5);

                        }
                        part++;

                    }
                    System.out.println();

                }
                break;
            }


            //AddCrimeToTable("crash", "Lakeland", "10/1/18");// test data
            // AddCrimeToTable("robbery", "Orlando", "10/1/18");
            //AddCrimeToTable("fire", "Tampa", "10/2/18");
            //AddCrimeToTable("crash", "Lakeland", "10/2/18");
            //AddCrimeToTable("robbery", "Orlando", "10/2/18");
            //AddCrimeToTable("fire", "Tampa", "10/3/18");
            // AddCrimeToTable("crash", "Lakeland", "10/4/18");
            //AddCrimeToTable("robbery", "Orlando", "10/6/18");
            //AddCrimeToTable("fire", "Tampa", "10/10/18");
        }
    }

    public void getAllData() {
        AsyncTask test1 = new getCrimeData();
        test1.execute(5, 28.1514729, -81.8457492, 50);// PARAMS(Radius, lat, long,entry count)

        DatabaseHelper test2 = new DatabaseHelper(this);


        while (true) {
            if (crimeArrayUpdated) {
                System.out.println("\n-----------------------------");
                System.out.println("CrimeArray has been updated");
                System.out.println("-----------------------------");
                String p1 = "", p2 = "", p3 = "", p4 = "", p5 = "";
                int part = 0;
                for (int x = 0; x < crimeArray.length; x++) {
                    for (int y = 0; y < crimeArray[x].length; y++) {
                        System.out.print(crimeArray[x][y] + "  |  ");
                        if (part == 0) {
                            p1 = crimeArray[x][y];
                        } else if (part == 1) {
                            p2 = crimeArray[x][y];
                        } else if (part == 2) {
                            p3 = crimeArray[x][y];
                        } else if (part == 3) {
                            p4 = crimeArray[x][y];
                        } else if (part == 4) {
                            p5 = crimeArray[x][y];
                            part = -1;
                            
                            String temp1 = p4.substring(5);
                            temp1 = temp1.substring(0,temp1.length()-1);
                            String temp2 = p5.substring(5);
                            temp2 = temp2.substring(0,temp2.length()-1);
                            //(String name, double latitude, double longitude, String date, int classification, String summary)
                            test2.insertActivity(p1,Double.parseDouble(temp1),Double.parseDouble(temp2),p2,0,p3);
                        }
                        part++;

                    }
                    System.out.println();

                }
                break;
            }
        }
    }

    private class getCrimeData extends AsyncTask<Object, Void, String> {
        @Override
        protected String doInBackground(Object... objects) {

            String WebMethodURL = "https://api.spotcrime.com/crimes.json?lat=" + objects[1] + "&lon=" + objects[2] + "&radius=" + objects[0] + "&callback=jsonp1541424592568&key=heythisisforpublicspotcrime.comuse-forcommercial-or-research-use-call-877.410.1607-or-email-pyrrhus-at-spotcrime.com";
            URL u = null;
            StringBuilder response = new StringBuilder();
            try {
                u = new URL(WebMethodURL);
                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    //Get the Stream reader ready
                    BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()), 8192);
                    //Loop through the return data and copy it over to the response object to be processed
                    String line = null;
                    while ((line = input.readLine()) != null) {
                        response.append(line);
                    }
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            postBackground(response.toString(), objects[3]);
            return null;
        }

        protected void postBackground(String returnVal, Object entryAmount) {
            ArrayList<String> crimeTable = new ArrayList();
            int start = 0, end = 0;
            for (int x = 0; x < returnVal.length(); x++) {
                if (returnVal.substring(x, x + 1).equals("{")) {
                    start = x;
                }
                if (returnVal.substring(x, x + 1).equals("}")) {
                    end = x + 1;
                }
                if (start > 0 && end > 0) {
                    String line = returnVal.substring(start, end);
                    String[] splited = line.split(",\"");
                    for (String xx : splited) {
                        crimeTable.add(xx);
                    }


                    start = 0;
                    end = 0;
                }

            }

            System.out.println("------------------------------------------------\nAsync task has finished\nresult:\n" + returnVal + "\n Array Size:" + crimeTable.size() + "\n");
            int width = 0;
            int paddingX = 0;
            int paddingY = 0;
            String[][] returnVar = new String[(int) entryAmount][5];
            int[] whichOnes = {0, 1, 1, 1, 0, 1, 1};
            for (int x = 0; x < crimeTable.size(); x++) {
                if (whichOnes[width] == 1) {
                    if (width == 1) {

                        returnVar[paddingY][paddingX] = crimeTable.get(x).replace("type\":", "").replace("\"", "");
                    } else if (width == 2) {
                        returnVar[paddingY][paddingX] = crimeTable.get(x).replace("date\":", "").replace("\"", "");
                    } else if (width == 3) {
                        returnVar[paddingY][paddingX] = crimeTable.get(x).replace("address\":", "").replace("\"", "");
                    } else {
                        returnVar[paddingY][paddingX] = crimeTable.get(x);
                    }

                    paddingX++;
                }
                width++;
                if (width == 7) {
                    width = 0;
                    paddingX = 0;
                    paddingY++;
                }
            }
            ActivityListActivity.crimeArray = returnVar;
            ActivityListActivity.crimeArrayUpdated = true;
        }
    }


    public void AddCrimeToTable(String type, String location, String date, String latC, String longC) {
        //Method adds crime to table called "crimeTableList"
        // any question regarding this code please ask Daniel
        TableLayout table = (TableLayout) findViewById(R.id.crimeTableList);
        table.setPadding(30, 30, 30, 30);
        TableRow headerRow = new TableRow(this);
        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        ////////////////////////
        if (!tableInitialized) {
            TextView header1 = new TextView(this);
            TextView header2 = new TextView(this);
            TextView header3 = new TextView(this);
            // --> formating
            header1.setTextSize(20);
            header2.setTextSize(20);
            header3.setTextSize(20);
            header1.setPadding(0, 0, 60, 0);
            header2.setPadding(0, 0, 60, 0);
            header3.setPadding(0, 0, 60, 0);
            header1.setTextColor((getResources().getColor(((R.color.secondary_color)))));
            header1.setText("Type");
            header2.setTextColor((getResources().getColor(((R.color.secondary_color)))));
            header2.setText("Date & Time");
            header3.setTextColor((getResources().getColor(((R.color.secondary_color)))));
            header3.setText("Location");
            // --> formating
            header1.setTextSize(10);
            header2.setTextSize(10);
            header3.setTextSize(10);
            headerRow.addView(header1);
            headerRow.addView(header2);
            headerRow.addView(header3);
            headerRow.setPadding(0, 0, 0, 10);
            headerRow.setBackgroundResource(R.drawable.crime_table_border);
            table.addView(headerRow);
            tableInitialized = true;
        }
        /////////////////////////
        TextView test1 = new TextView(this);
        test1.setText(type);
        test1.setTextColor(getResources().getColor((R.color.white_text_color)));
        test1.setTextSize(10);
        test1.setPadding(0, 0, 30, 0);
        TextView test2 = new TextView(this);
        test2.setTextColor(getResources().getColor((R.color.white_text_color)));
        test2.setText(location);
        test2.setTextSize(10);
        test2.setPadding(0, 0, 30, 0);
        TextView test3 = new TextView(this);
        test3.setTextColor(getResources().getColor((R.color.white_text_color)));
        test3.setText(date);
        test3.setTextSize(7);


        row.addView(test1);
        row.addView(test2);
        row.addView(test3);
        //////////////////////
        row.setBackgroundResource(R.drawable.crime_table_border);
        table.addView(row);

    }
}

package com.example.sadistickamikaze.polling_app;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    static String password;
    private TextView mTextMessage;
    static double myDist = 1000000000;
    double myLong=0;
    double myLat=0;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Button FilterButton = (Button)findViewById(R.id.FilterButton);
            Button RefreshButton = (Button)findViewById(R.id.refreshButton);
            Button newPollButton = (Button)findViewById(R.id.newPollButton);
            LinearLayout layout = (LinearLayout)findViewById((R.id.ButtonLayout));
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    FilterButton.setVisibility(View.VISIBLE);
                    newPollButton.setVisibility(View.VISIBLE);
                    RefreshButton.setVisibility(View.VISIBLE);
                    layout.setVisibility(View.VISIBLE);

                    return true;
                case R.id.navigation_notifications:
                    FilterButton.setVisibility(View.GONE);
                    newPollButton.setVisibility(View.GONE);
                    RefreshButton.setVisibility(View.GONE);
                    layout.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password="";
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        String locationProvider = LocationManager.GPS_PROVIDER;
        String networkProvider = LocationManager.NETWORK_PROVIDER;
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        myLong=0;
        myLat=0;
        if(locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            try {
                Location location = locationManager.getLastKnownLocation(locationProvider);
                Location location2 = locationManager.getLastKnownLocation(networkProvider);
                Log.d("test", "provider");
                if (location2 != null) {
                    myLong = location2.getLongitude();
                    myLat = location2.getLatitude();
                }
                if (location != null) {
                    myLong = location.getLongitude();
                    myLat = location.getLatitude();
                }
            } catch (SecurityException e) {
                Log.d("Hey!", "Permission to access location denied, yo!");
                finish();
            }
        }
        else{
            Log.d("test", "no provider");
        }

        Button FilterButton = (Button)findViewById(R.id.FilterButton);
        Button RefreshButton = (Button)findViewById(R.id.refreshButton);

        FilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference pollReference= FirebaseDatabase.getInstance().getReference().child("Polls");
                pollReference.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        }
                );
                Intent startIntent = new Intent(getApplicationContext(), FilterButton.class);
                startActivity(startIntent);
            }
        });
        RefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout = (LinearLayout)findViewById((R.id.ButtonLayout));
                layout.removeAllViewsInLayout();
                DatabaseReference pollReference= FirebaseDatabase.getInstance().getReference().child("Polls");
                pollReference.addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) { //every time page is resumed it refreshes this
                                String[] names = getPollNames((Map<String, Object>) dataSnapshot.getValue()); //runs functions to put passwords, name, and votes into arrays

                                String[] opt1names = getOpt1Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt2names = getOpt2Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt3names = getOpt3Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt4names = getOpt4Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt5names = getOpt5Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt6names = getOpt6Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt7names = getOpt7Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt8names = getOpt8Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt9names = getOpt9Names((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt10names = getOpt10Names((Map<String, Object>) dataSnapshot.getValue());
                                Double[] longitudes = getLongitudes((Map<String, Object>) dataSnapshot.getValue());
                                Double[] latitudes = getLatitudes((Map<String, Object>) dataSnapshot.getValue());
                                Double[] distances = getDistances((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt1PollCount = getOpt1PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt2PollCount = getOpt2PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt3PollCount = getOpt3PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt4PollCount = getOpt4PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt5PollCount = getOpt5PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt6PollCount = getOpt6PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt7PollCount = getOpt7PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt8PollCount = getOpt8PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt9PollCount = getOpt9PollCount((Map<String, Object>) dataSnapshot.getValue());
                                Long[] opt10PollCount = getOpt10PollCount((Map<String, Object>) dataSnapshot.getValue());
                                String[] passwords = getPasswords((Map<String, Object>) dataSnapshot.getValue());
                                String[] delete = getDeletePasswords((Map<String,Object>) dataSnapshot.getValue());
                                String[] opt1voters = getOpt1Voters((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt2voters = getOpt2Voters((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt3voters = getOpt3Voters((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt4voters = getOpt4Voters((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt5voters = getOpt5Voters((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt6voters = getOpt6Voters((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt7voters = getOpt7Voters((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt8voters = getOpt8Voters((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt9voters = getOpt9Voters((Map<String, Object>) dataSnapshot.getValue());
                                String[] opt10voters = getOpt10Voters((Map<String, Object>) dataSnapshot.getValue());
                                LinearLayout layout = (LinearLayout)findViewById((R.id.ButtonLayout));
                                Context context = getApplicationContext();
                                for(int i=0;i<names.length;i++){
                                    if(passwords[i].equals(password) && distFrom(myLat,myLong,latitudes[i],longitudes[i ]) < myDist && distFrom(myLat,myLong,latitudes[i],longitudes[i]) <= distances[i]) { //if passwords match, make a button with the name of the poll
                                        final Button pollButton = new Button(context);
                                        pollButton.setId(i);
                                        pollButton.setText(names[i]);
                                        pollButton.setBackgroundResource(R.drawable.button);
                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1000, 100);
                                        params.topMargin = 20;
                                        layout.addView(pollButton, params);
                                        final String opt1name = opt1names[i];
                                        final String opt2name = opt2names[i];
                                        final String opt3name = opt3names[i];
                                        final String opt4name = opt4names[i];
                                        final String opt5name = opt5names[i];
                                        final String opt6name = opt6names[i];
                                        final String opt7name = opt7names[i];
                                        final String opt8name = opt8names[i];
                                        final String opt9name = opt9names[i];
                                        final String opt10name = opt10names[i];
                                        final long opt1count = opt1PollCount[i];
                                        final long opt2count = opt2PollCount[i];
                                        final long opt3count = opt3PollCount[i];
                                        final long opt4count = opt4PollCount[i];
                                        final long opt5count = opt5PollCount[i];
                                        final long opt6count = opt6PollCount[i];
                                        final long opt7count = opt7PollCount[i];
                                        final long opt8count = opt8PollCount[i];
                                        final long opt9count = opt9PollCount[i];
                                        final long opt10count = opt10PollCount[i];
                                        final String opt1voter = opt1voters[i];
                                        final String opt2voter = opt2voters[i];
                                        final String opt3voter = opt3voters[i];
                                        final String opt4voter = opt4voters[i];
                                        final String opt5voter = opt5voters[i];
                                        final String opt6voter = opt6voters[i];
                                        final String opt7voter = opt7voters[i];
                                        final String opt8voter = opt8voters[i];
                                        final String opt9voter = opt9voters[i];
                                        final String opt10voter = opt10voters[i];
                                        final String del = delete[i];
                                        pollButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent startIntent = new Intent(getApplicationContext(), viewpoll.class);
                                                startIntent.putExtra("name", (String)pollButton.getText());
                                                startIntent.putExtra("opt1info", opt1name+": "+Integer.toString((int)opt1count));
                                                startIntent.putExtra("opt2info", opt2name+": "+Integer.toString((int)opt2count));
                                                startIntent.putExtra("opt3info", opt3name+": "+Integer.toString((int)opt3count));
                                                startIntent.putExtra("opt4info", opt4name+": "+Integer.toString((int)opt4count));
                                                startIntent.putExtra("opt5info", opt5name+": "+Integer.toString((int)opt5count));
                                                startIntent.putExtra("opt6info", opt6name+": "+Integer.toString((int)opt6count));
                                                startIntent.putExtra("opt7info", opt7name+": "+Integer.toString((int)opt7count));
                                                startIntent.putExtra("opt8info", opt8name+": "+Integer.toString((int)opt8count));
                                                startIntent.putExtra("opt9info", opt9name+": "+Integer.toString((int)opt9count));
                                                startIntent.putExtra("opt10info", opt10name+": "+Integer.toString((int)opt10count));
                                                startIntent.putExtra("delPass", del);
                                                startIntent.putExtra("opt1voters", opt1voter);
                                                startIntent.putExtra("opt2voters", opt2voter);
                                                startIntent.putExtra("opt3voters", opt3voter);
                                                startIntent.putExtra("opt4voters", opt4voter);
                                                startIntent.putExtra("opt5voters", opt5voter);
                                                startIntent.putExtra("opt6voters", opt6voter);
                                                startIntent.putExtra("opt7voters", opt7voter);
                                                startIntent.putExtra("opt8voters", opt8voter);
                                                startIntent.putExtra("opt9voters", opt9voter);
                                                startIntent.putExtra("opt10voters", opt10voter);
                                                //startIntent.putExtra(""+n+"", yesname);
                                                //startIntent.putExtra(""+o+"", noname);
                                                startActivity(startIntent);
                                            }
                                        });
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        }
                );
            }
        });

    }
    private String[] getOpt1Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt1"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt2Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt2"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt3Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt3"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt4Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt4"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt5Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt5"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt6Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt6"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt7Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt7"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt8Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt8"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt9Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt9"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getOpt10Names(Map<String, Object> polls){
        ArrayList<String> pollList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add((String) poll.get("opt10"));
        }
        return (String[])pollList.toArray(new String[0]);

    }
    private String[] getPasswords(Map<String, Object> polls){ //puts passwords into a long array by iterating though all polls
        ArrayList<String> passwords= new ArrayList<String>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            passwords.add((String)poll.get("password"));
        }
        return (String[])  passwords.toArray(new String[0]);
    }
    private String[] getDeletePasswords(Map<String, Object> polls){ //puts delete passwords into a long array by iterating though all polls
        ArrayList<String> deletepasswords= new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            deletepasswords.add((String) poll.get("delete"));
        }
        return (String[])  deletepasswords.toArray(new String[0]);
    }
    private Long[] getOpt2PollCount(Map<String, Object> polls){ //puts nos into a long array by iterating though all polls
        ArrayList<Long> noCount = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            noCount.add((Long) poll.get("opt2ans"));
        }
        return (Long[]) noCount.toArray(new Long[0]);
    }
    private Long[] getOpt1PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt1ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt3PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt3ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt4PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt4ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt5PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt5ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt6PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt6ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt7PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt7ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt8PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt8ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt9PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt9ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private Long[] getOpt10PollCount(Map<String, Object> polls){ //puts yeses into a long array by iterating though all polls
        ArrayList<Long> yesCount = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesCount.add((Long) poll.get("opt10ans"));
        }
        return (Long[]) yesCount.toArray(new Long[0]);
    }
    private String[] getPollNames(Map<String, Object> polls){ //puts names into a String array by iterating though all polls
        ArrayList<String> pollList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            pollList.add(entry.getKey());
        }
        return (String[])pollList.toArray(new String[0]);
    }
    private String[] getOpt1Voters(Map<String, Object> polls){ //puts names into a String array by iterating though all polls
        ArrayList<String> yesNames = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            yesNames.add((String)poll.get("opt1names"));
        }
        return (String[])yesNames.toArray(new String[0]);
    }

    private String[] getOpt2Voters(Map<String, Object> polls){ //puts names into a String array by iterating though all polls
        ArrayList<String> noNames = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            noNames.add((String)poll.get("opt2names"));
        }
        return (String[])noNames.toArray(new String[0]);
    }
    private String[] getOpt3Voters(Map<String, Object> polls){ //puts names into a String array by iterating though all polls
        ArrayList<String> noNames = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            noNames.add((String)poll.get("opt3names"));
        }
        return (String[])noNames.toArray(new String[0]);
    }
    private String[] getOpt5Voters(Map<String, Object> polls){ //puts names into a String array by iterating though all polls
        ArrayList<String> noNames = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            noNames.add((String)poll.get("opt5names"));
        }
        return (String[])noNames.toArray(new String[0]);
    }
    private String[] getOpt4Voters(Map<String, Object> polls){ //puts names into a String array by iterating though all polls
        ArrayList<String> noNames = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            noNames.add((String)poll.get("opt4names"));
        }
        return (String[])noNames.toArray(new String[0]);
    }
    private String[] getOpt6Voters(Map<String, Object> polls){ //puts names into a String array by iterating though all polls
        ArrayList<String> noNames = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            noNames.add((String)poll.get("opt6names"));
        }
        return (String[])noNames.toArray(new String[0]);
    }
    private String[] getOpt7Voters(Map<String, Object> polls){ //puts names into a String array by iterating though all polls
        ArrayList<String> noNames = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            noNames.add((String)poll.get("opt7names"));
        }
        return (String[])noNames.toArray(new String[0]);
    }
    private String[] getOpt8Voters(Map<String, Object> polls){ //puts names into a String array by iterating though all polls
        ArrayList<String> noNames = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            noNames.add((String)poll.get("opt8names"));
        }
        return (String[])noNames.toArray(new String[0]);
    }
    private String[] getOpt9Voters(Map<String, Object> polls){ //puts names into a String array by iterating though all polls
        ArrayList<String> noNames = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            noNames.add((String)poll.get("opt9names"));
        }
        return (String[])noNames.toArray(new String[0]);
    }
    private String[] getOpt10Voters(Map<String, Object> polls){ //puts names into a String array by iterating though all polls
        ArrayList<String> noNames = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            noNames.add((String)poll.get("opt10names"));
        }
        return (String[])noNames.toArray(new String[0]);
    }

    private Double[] getLatitudes(Map<String, Object> polls){
        ArrayList<Double> latitudes = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            String temp = poll.get("latitude") + "";
            latitudes.add(Double.parseDouble(temp));
        }
        return latitudes.toArray(new Double[0]);
    }
    private Double[] getLongitudes(Map<String, Object> polls){
        ArrayList<Double> longitudes = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            String temp = poll.get("longitude") + "";
            longitudes.add(Double.parseDouble(temp));
        }
        return longitudes.toArray(new Double[0]);
    }
    private Double[] getDistances(Map<String, Object> polls){
        ArrayList<Double> distances = new ArrayList<>();
        for (Map.Entry<String, Object> entry : polls.entrySet()) {
            Map poll = (Map) entry.getValue();
            String temp = poll.get("distance") + "";
            distances.add(Double.parseDouble(temp));
        }
        return distances.toArray(new Double[0]);
    }

    public void newPoll(View v){
        Intent info = new Intent(this, CreatePoll.class);
        startActivity(info);
    }


    @Override
    public void onResume(){
        super.onResume();
        myLong=0;
        myLat=0;
        String locationProvider = LocationManager.GPS_PROVIDER;
        String networkProvider = LocationManager.NETWORK_PROVIDER;
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            try {
                Location location = locationManager.getLastKnownLocation(locationProvider);
                Location location2 = locationManager.getLastKnownLocation(networkProvider);
                Log.d("test", "provider");
                if (location2 != null) {
                    myLong = location2.getLongitude();
                    myLat = location2.getLatitude();
                }
                if (location != null) {
                    myLong = location.getLongitude();
                    myLat = location.getLatitude();
                }
                Log.d("location", myLong+"");
            } catch (SecurityException e) {
                Log.d("Hey!", "Permission to access location denied, yo!");
                finish();
            }
        }
        else{
            Log.d("test", "no provider");
        }
        final DatabaseReference pollReference= FirebaseDatabase.getInstance().getReference().child("Polls");
        pollReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) { //every time page is resumed it refreshes this
                        String[] names = getPollNames((Map<String, Object>) dataSnapshot.getValue()); //runs functions to put passwords, name, and votes into arrays

                        String[] opt1names = getOpt1Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt2names = getOpt2Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt3names = getOpt3Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt4names = getOpt4Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt5names = getOpt5Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt6names = getOpt6Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt7names = getOpt7Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt8names = getOpt8Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt9names = getOpt9Names((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt10names = getOpt10Names((Map<String, Object>) dataSnapshot.getValue());
                        Double[] longitudes = getLongitudes((Map<String, Object>) dataSnapshot.getValue());
                        Double[] latitudes = getLatitudes((Map<String, Object>) dataSnapshot.getValue());
                        Double[] distances = getDistances((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt1PollCount = getOpt1PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt2PollCount = getOpt2PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt3PollCount = getOpt3PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt4PollCount = getOpt4PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt5PollCount = getOpt5PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt6PollCount = getOpt6PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt7PollCount = getOpt7PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt8PollCount = getOpt8PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt9PollCount = getOpt9PollCount((Map<String, Object>) dataSnapshot.getValue());
                        Long[] opt10PollCount = getOpt10PollCount((Map<String, Object>) dataSnapshot.getValue());
                        String[] passwords = getPasswords((Map<String, Object>) dataSnapshot.getValue());
                        String[] delete = getDeletePasswords((Map<String,Object>) dataSnapshot.getValue());
                        String[] opt1voters = getOpt1Voters((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt2voters = getOpt2Voters((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt3voters = getOpt3Voters((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt4voters = getOpt4Voters((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt5voters = getOpt5Voters((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt6voters = getOpt6Voters((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt7voters = getOpt7Voters((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt8voters = getOpt8Voters((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt9voters = getOpt9Voters((Map<String, Object>) dataSnapshot.getValue());
                        String[] opt10voters = getOpt10Voters((Map<String, Object>) dataSnapshot.getValue());
                        LinearLayout layout = (LinearLayout)findViewById((R.id.ButtonLayout));
                        Context context = getApplicationContext();
                        for(int i=0;i<names.length;i++){
                            if(passwords[i].equals(password) && distFrom(myLat,myLong,latitudes[i],longitudes[i ]) < myDist && distFrom(myLat,myLong,latitudes[i],longitudes[i]) <= distances[i]) { //if passwords match, make a button with the name of the poll
                                final Button pollButton = new Button(context);
                                pollButton.setId(i);
                                pollButton.setText(names[i]);
                                pollButton.setBackgroundResource(R.drawable.button);
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1000, 100);
                                params.topMargin = 20;
                                layout.addView(pollButton, params);
                                final String opt1name = opt1names[i];
                                final String opt2name = opt2names[i];
                                final String opt3name = opt3names[i];
                                final String opt4name = opt4names[i];
                                final String opt5name = opt5names[i];
                                final String opt6name = opt6names[i];
                                final String opt7name = opt7names[i];
                                final String opt8name = opt8names[i];
                                final String opt9name = opt9names[i];
                                final String opt10name = opt10names[i];
                                final long opt1count = opt1PollCount[i];
                                final long opt2count = opt2PollCount[i];
                                final long opt3count = opt3PollCount[i];
                                final long opt4count = opt4PollCount[i];
                                final long opt5count = opt5PollCount[i];
                                final long opt6count = opt6PollCount[i];
                                final long opt7count = opt7PollCount[i];
                                final long opt8count = opt8PollCount[i];
                                final long opt9count = opt9PollCount[i];
                                final long opt10count = opt10PollCount[i];
                                final String opt1voter = opt1voters[i];
                                final String opt2voter = opt2voters[i];
                                final String opt3voter = opt3voters[i];
                                final String opt4voter = opt4voters[i];
                                final String opt5voter = opt5voters[i];
                                final String opt6voter = opt6voters[i];
                                final String opt7voter = opt7voters[i];
                                final String opt8voter = opt8voters[i];
                                final String opt9voter = opt9voters[i];
                                final String opt10voter = opt10voters[i];
                                final String del = delete[i];
                                pollButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent startIntent = new Intent(getApplicationContext(), viewpoll.class);
                                        startIntent.putExtra("name", (String)pollButton.getText());
                                        startIntent.putExtra("opt1info", opt1name+": "+Integer.toString((int)opt1count));
                                        startIntent.putExtra("opt2info", opt2name+": "+Integer.toString((int)opt2count));
                                        startIntent.putExtra("opt3info", opt3name+": "+Integer.toString((int)opt3count));
                                        startIntent.putExtra("opt4info", opt4name+": "+Integer.toString((int)opt4count));
                                        startIntent.putExtra("opt5info", opt5name+": "+Integer.toString((int)opt5count));
                                        startIntent.putExtra("opt6info", opt6name+": "+Integer.toString((int)opt6count));
                                        startIntent.putExtra("opt7info", opt7name+": "+Integer.toString((int)opt7count));
                                        startIntent.putExtra("opt8info", opt8name+": "+Integer.toString((int)opt8count));
                                        startIntent.putExtra("opt9info", opt9name+": "+Integer.toString((int)opt9count));
                                        startIntent.putExtra("opt10info", opt10name+": "+Integer.toString((int)opt10count));
                                        startIntent.putExtra("delPass", del);
                                        startIntent.putExtra("opt1voters", opt1voter);
                                        startIntent.putExtra("opt2voters", opt2voter);
                                        startIntent.putExtra("opt3voters", opt3voter);
                                        startIntent.putExtra("opt4voters", opt4voter);
                                        startIntent.putExtra("opt5voters", opt5voter);
                                        startIntent.putExtra("opt6voters", opt6voter);
                                        startIntent.putExtra("opt7voters", opt7voter);
                                        startIntent.putExtra("opt8voters", opt8voter);
                                        startIntent.putExtra("opt9voters", opt9voter);
                                        startIntent.putExtra("opt10voters", opt10voter);
                                        //startIntent.putExtra(""+n+"", yesname);
                                        //startIntent.putExtra(""+o+"", noname);
                                        startActivity(startIntent);
                                    }
                                });
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

    }
    public static double distFrom(Double lat1, Double lng1, Double lat2, Double lng2) {
        double earthRadius = 3958.75; // miles (or 6371.0 kilometers)
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        return dist;
    }
    @Override
    public void onPause(){ //stops duplicates being created when page refreshes
        super.onPause();
        LinearLayout layout = (LinearLayout)findViewById((R.id.ButtonLayout));
        layout.removeAllViewsInLayout();
    }
}

package com.example.sadistickamikaze.polling_app;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreatePoll extends AppCompatActivity implements LocationListener{
    final DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child("Polls");
    String dropdown;
    double longitude;
    double latitude;
    double distance;

    @Override
    public void onProviderEnabled(String provider){

    }

    @Override
    public void onProviderDisabled(String provider){

    }

    @Override
    public void onLocationChanged(Location location){

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle b){

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String locationProvider = LocationManager.GPS_PROVIDER;
        String networkProvider = LocationManager.NETWORK_PROVIDER;
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        longitude=0;
        latitude = 0;
        distance = 1000000000;

        setContentView(R.layout.activity_create_poll);
        if(locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            try {
                Location location = locationManager.getLastKnownLocation(locationProvider);
                Location location2 = locationManager.getLastKnownLocation(networkProvider);
                Log.d("test", "provider");
                if (location2 != null) {
                    longitude = location2.getLongitude();
                    latitude = location2.getLatitude();
                }
                if (location != null) {
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                }
                Log.d("long", longitude+"");
            } catch (SecurityException e) {
                Log.d("Hey!", "Permission to access location denied, yo!");
                finish();
            }
        }
        else{
            Log.d("test", "no provider");
        }



        Spinner numberofpolldropdown = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.numberofpolls));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberofpolldropdown.setAdapter(adapter);
        final EditText ddoption1 = (EditText)findViewById(R.id.ddoption1);
        final EditText ddoption2 = (EditText)findViewById(R.id.ddoption2);
        final EditText ddoption3 = (EditText)findViewById(R.id.ddoption3);
        final EditText ddoption4 = (EditText)findViewById(R.id.ddoption4);
        final EditText ddoption5 = (EditText)findViewById(R.id.ddoption5);
        final EditText ddoption6 = (EditText)findViewById(R.id.ddoption6);
        final EditText ddoption7 = (EditText)findViewById(R.id.ddoption7);
        final EditText ddoption8 = (EditText)findViewById(R.id.ddoption8);
        final EditText ddoption9 = (EditText)findViewById(R.id.ddoption9);
        final EditText ddoption10 = (EditText)findViewById(R.id.ddoption10);
        numberofpolldropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 dropdown = parent.getItemAtPosition(position).toString();
                 if(dropdown.equals("2")){
                     ddoption1.setVisibility(View.VISIBLE);
                     ddoption2.setVisibility(View.VISIBLE);
                     ddoption3.setVisibility(View.GONE);
                     ddoption4.setVisibility(View.GONE);
                     ddoption5.setVisibility(View.GONE);
                     ddoption6.setVisibility(View.GONE);
                     ddoption7.setVisibility(View.GONE);
                     ddoption8.setVisibility(View.GONE);
                     ddoption9.setVisibility(View.GONE);
                     ddoption10.setVisibility(View.GONE);
                 }
                if(dropdown.equals("3")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.GONE);
                    ddoption5.setVisibility(View.GONE);
                    ddoption6.setVisibility(View.GONE);
                    ddoption7.setVisibility(View.GONE);
                    ddoption8.setVisibility(View.GONE);
                    ddoption9.setVisibility(View.GONE);
                    ddoption10.setVisibility(View.GONE);
                }
                if(dropdown.equals("4")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.VISIBLE);
                    ddoption5.setVisibility(View.GONE);
                    ddoption6.setVisibility(View.GONE);
                    ddoption7.setVisibility(View.GONE);
                    ddoption8.setVisibility(View.GONE);
                    ddoption9.setVisibility(View.GONE);
                    ddoption10.setVisibility(View.GONE);
                }
                if(dropdown.equals("5")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.VISIBLE);
                    ddoption5.setVisibility(View.VISIBLE);
                    ddoption6.setVisibility(View.GONE);
                    ddoption7.setVisibility(View.GONE);
                    ddoption8.setVisibility(View.GONE);
                    ddoption9.setVisibility(View.GONE);
                    ddoption10.setVisibility(View.GONE);
                }
                if(dropdown.equals("6")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.VISIBLE);
                    ddoption5.setVisibility(View.VISIBLE);
                    ddoption6.setVisibility(View.VISIBLE);
                    ddoption7.setVisibility(View.GONE);
                    ddoption8.setVisibility(View.GONE);
                    ddoption9.setVisibility(View.GONE);
                    ddoption10.setVisibility(View.GONE);
                }
                if(dropdown.equals("7")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.VISIBLE);
                    ddoption5.setVisibility(View.VISIBLE);
                    ddoption6.setVisibility(View.VISIBLE);
                    ddoption7.setVisibility(View.VISIBLE);
                    ddoption8.setVisibility(View.GONE);
                    ddoption9.setVisibility(View.GONE);
                    ddoption10.setVisibility(View.GONE);
                }
                if(dropdown.equals("8")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.VISIBLE);
                    ddoption5.setVisibility(View.VISIBLE);
                    ddoption6.setVisibility(View.VISIBLE);
                    ddoption7.setVisibility(View.VISIBLE);
                    ddoption8.setVisibility(View.VISIBLE);
                    ddoption9.setVisibility(View.GONE);
                    ddoption10.setVisibility(View.GONE);
                }
                if(dropdown.equals("9")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.VISIBLE);
                    ddoption5.setVisibility(View.VISIBLE);
                    ddoption6.setVisibility(View.VISIBLE);
                    ddoption7.setVisibility(View.VISIBLE);
                    ddoption8.setVisibility(View.VISIBLE);
                    ddoption9.setVisibility(View.VISIBLE);
                    ddoption10.setVisibility(View.GONE);
                }
                if(dropdown.equals("10")){
                    ddoption1.setVisibility(View.VISIBLE);
                    ddoption2.setVisibility(View.VISIBLE);
                    ddoption3.setVisibility(View.VISIBLE);
                    ddoption4.setVisibility(View.VISIBLE);
                    ddoption5.setVisibility(View.VISIBLE);
                    ddoption6.setVisibility(View.VISIBLE);
                    ddoption7.setVisibility(View.VISIBLE);
                    ddoption8.setVisibility(View.VISIBLE);
                    ddoption9.setVisibility(View.VISIBLE);
                    ddoption10.setVisibility(View.VISIBLE);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button ApplyButton = (Button)findViewById(R.id.CreatePollButton);
        ApplyButton.setOnClickListener(new View.OnClickListener() {

            EditText pollquestion = (EditText)findViewById(R.id.question);
            EditText option1answer = (EditText)findViewById(R.id.option1answer);
            EditText option2answer = (EditText)findViewById(R.id.option2answer);
            EditText distanceanswer = (EditText)findViewById(R.id.distanceanswer);

            @Override
            public void onClick(View v) {
                String password="";
                String delete="";
                String d = distanceanswer.getText().toString();
                String string = pollquestion.getText().toString();
                String stringoption1 = ddoption1.getText().toString();
                String stringoption2 = ddoption2.getText().toString();
                String stringoption3 = ddoption3.getText().toString();
                String stringoption4 = ddoption4.getText().toString();
                String stringoption5 = ddoption5.getText().toString();
                String stringoption6 = ddoption6.getText().toString();
                String stringoption7 = ddoption7.getText().toString();
                String stringoption8 = ddoption8.getText().toString();
                String stringoption9 = ddoption9.getText().toString();
                String stringoption10 = ddoption10.getText().toString();

                if(!string.equals("") && !stringoption1.equals("") && !stringoption2.equals("")) {


                    if (option1answer.getText().toString().matches("") == false) {
                        password = option1answer.getText().toString();
                    }
                    if (option2answer.getText().toString().matches("") == false) {
                        delete = option1answer.getText().toString();
                    }
                    myref.child(string).child("opt1").setValue(stringoption1);
                    myref.child(string).child("opt2").setValue(stringoption2);
                    myref.child(string).child("opt3").setValue(stringoption3);
                    myref.child(string).child("opt4").setValue(stringoption4);
                    myref.child(string).child("opt5").setValue(stringoption5);
                    myref.child(string).child("opt6").setValue(stringoption6);
                    myref.child(string).child("opt7").setValue(stringoption7);
                    myref.child(string).child("opt8").setValue(stringoption8);
                    myref.child(string).child("opt9").setValue(stringoption9);
                    myref.child(string).child("opt10").setValue(stringoption10);
                    for (int i = 1; i < 11; i++) {
                        myref.child(string).child("opt" + i + "ans").setValue(0);
                    }
                    for(int i =1; i<11;i++){
                        myref.child(string).child("opt"+i+"names").setValue("");
                    }
                    myref.child(string).child("password").setValue("");
                    if (password.matches("") == false) {
                        myref.child(string).child("password").setValue(password);
                    }
                    myref.child(string).child("delete").setValue("");
                    if (delete.matches("") == false) {
                        myref.child(string).child("delete").setValue(delete);
                    }
                    if (d.matches("") == false){
                        distance = Double.parseDouble(d);
                    }
                    myref.child(string).child("distance").setValue(distance);
                    myref.child(string).child("opt1names").setValue("");
                    myref.child(string).child("opt2names").setValue("");
                    myref.child(string).child("longitude").setValue(longitude);
                    myref.child(string).child("latitude").setValue(latitude);
                    finish();
                }
            }
        });
    }
}

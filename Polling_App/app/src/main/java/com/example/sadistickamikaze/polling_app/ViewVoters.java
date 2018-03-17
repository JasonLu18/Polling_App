package com.example.sadistickamikaze.polling_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewVoters extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_voters);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        TextView textView1 = findViewById(R.id.voters1);
        TextView textView2 = findViewById(R.id.voters2);
        TextView textView3 = findViewById(R.id.voters3);
        TextView textView4 = findViewById(R.id.voters4);
        TextView textView5 = findViewById(R.id.voters5);
        TextView textView6 = findViewById(R.id.voters6);
        TextView textView7 = findViewById(R.id.voters7);
        TextView textView8 = findViewById(R.id.voters8);
        TextView textView9 = findViewById(R.id.voters9);
        TextView textView10 = findViewById(R.id.voters10);

        if(b.get("opt1").equals("NONE")==false && b.get("opt1").equals(": 0") ==false){
            textView1.setText((String)b.get("opt1"));
        }
        if(b.get("opt2").equals("NONE")==false && b.get("opt2").equals(": 0") ==false){
            textView2.setText((String)b.get("opt2"));
        }
        if(b.get("opt3").equals("NONE")==false && b.get("opt3").equals(": 0") ==false){
            textView3.setText((String)b.get("opt3"));
        }
        if(b.get("opt4").equals("NONE")==false && b.get("opt4").equals(": 0") ==false){
            textView4.setText((String)b.get("opt4"));
        }
        if(b.get("opt5").equals("NONE")==false && b.get("opt5").equals(": 0") ==false){
            textView5.setText((String)b.get("opt5"));
        }
        if(b.get("opt6").equals("NONE")==false && b.get("opt6").equals(": 0") ==false){
            textView6.setText((String)b.get("opt6"));
        }
        if(b.get("opt7").equals("NONE")==false && b.get("opt7").equals(": 0") ==false){
            textView7.setText((String)b.get("opt7"));
        }
        if(b.get("opt8").equals("NONE")==false && b.get("opt8").equals(": 0") ==false){
            textView8.setText((String)b.get("opt8"));
        }
        if(b.get("opt9").equals("NONE")==false && b.get("opt9").equals(": 0") ==false){
            textView9.setText((String)b.get("opt9"));
        }
        if(b.get("opt10").equals("NONE")==false && b.get("opt10").equals(": 0") ==false){
            textView10.setText((String)b.get("opt10"));
        }



    }
}

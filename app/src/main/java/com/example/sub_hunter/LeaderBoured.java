package com.example.sub_hunter;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class LeaderBoured extends AppCompatActivity {
    private ArrayList<Pair<String,Integer>> leaferBoared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_boured);
        leaferBoared=new ArrayList<Pair<String,Integer>>();
        configSubmit();
    }
    protected void configSubmit(){
        Button sub=(Button)findViewById(R.id.button2);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recorddata();
            }
        });
    }
    protected void recorddata(){
        String enterd=((TextView)findViewById(R.id.editText)).getText().toString();

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("score");

        Pair<String,Integer> result=new Pair<String,Integer>(enterd,Integer.valueOf(message));
        leaferBoared.add(result);
        TextView leader=(TextView)findViewById(R.id.result);
        leader.setText("");
        for(Pair<String,Integer> pair:leaferBoared){
            leader.append("name: "+pair.first+" score: "+pair.second);
            leader.append("\n");
        }

    }

}

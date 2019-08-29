package com.example.sub_hunter;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class loginActivaty extends AppCompatActivity {
    private String user;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activaty);
        configStartGameButton();

    }
    protected void configStartGameButton(){
        Button button =(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Debugging","clicked the start button");
                chackAuto();


            }
        });
    }
    protected void chackAuto(){
        this.user=((TextView)findViewById(R.id.username)).getText().toString();
        this.password=((TextView)findViewById(R.id.password)).getText().toString();
        Log.d("Debugging","username: "+user);
        Log.d("Debugging","Password: "+password);
        
        this.user="1";
        this.password="1";

        if((this.user.equals("1")) && (this.password.equals("1")))
            startActivity(new Intent(loginActivaty.this,MainActivity.class));
        else
            ((TextView)findViewById(R.id.error)).setText("wrong password");
    }

}

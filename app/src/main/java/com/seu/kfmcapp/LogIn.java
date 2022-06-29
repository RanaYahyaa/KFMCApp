package com.seu.kfmcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LogIn extends AppCompatActivity {
    TextView signUptxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setSignUptxt();
    }
    private void setSignUptxt(){
    signUptxt= (TextView) findViewById(R.id.signUpPrompttxt);
    signUptxt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LogIn.this, Registration.class);
            startActivity(intent);
        }
        });
    }
}
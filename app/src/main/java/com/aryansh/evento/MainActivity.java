package com.aryansh.evento;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer t=new Timer();
        mAuth= FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }
    private void login() {
        Intent i=new Intent(MainActivity.this,Login.class);
        startActivity(i);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Timer t=new Timer();

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                FirebaseUser user = mAuth.getCurrentUser();
                if(user!=null){
                    Intent o=new Intent(MainActivity.this,Login.class);
                    ActivityOptions options =
                            ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
                    startActivity(o,options.toBundle());
                }
                else {
                    login();
                }
            }
        },5000);
    }
}
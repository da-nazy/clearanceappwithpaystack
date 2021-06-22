package com.example.clearanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clearanceapp.R;

public class MainActivity extends AppCompatActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
    }

    public void next(View view) {
        startActivity(new Intent(getApplicationContext(), searchMatricNumber.class));
    }
}

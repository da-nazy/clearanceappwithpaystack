package com.example.clearanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class userdetails extends AppCompatActivity {
    EditText dog;
    EditText fullName;
    EditText matric_no;
    DatabaseHelper mydb;
    EditText university;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_userdetails);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        mydb = new DatabaseHelper(this);
        bindView();
    }

    public void bindView() {
        matric_no = (EditText) findViewById(R.id.entMatNum);
        fullName = (EditText) findViewById(R.id.entFullName);
        university = (EditText) findViewById(R.id.univeristy);
       dog = (EditText) findViewById(R.id.dog);
    }

    public boolean check() {
        String str = "Invalid input";
        if (matric_no.getText().toString().trim().isEmpty()) {
            matric_no.setError(str);
        }
        if (fullName.getText().toString().trim().isEmpty()) {
            fullName.setError(str);
        }
        if (university.getText().toString().trim().isEmpty()) {
            university.setError("Invalide input");
        }
        if (!dog.getText().toString().trim().isEmpty()) {
            return true;
        }
        dog.setError("Invalide Input");
        return false;
    }

    public void addUserDetails(View view) {
        if (!check()) {
            return;
        }
        if (mydb.insterUser(matric_no.getText().toString().trim(), fullName.getText().toString().trim(), dog.getText().toString().trim(), university.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "User Added succefully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, searchMatricNumber.class));
            mydb.close();
            return;
        }
        Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
    }
}

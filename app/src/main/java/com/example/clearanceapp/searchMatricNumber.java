package com.example.clearanceapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class searchMatricNumber extends AppCompatActivity {
    Button check;
    EditText matric;
    DatabaseHelper mydb;
    int operation;
    Cursor res;
    Cursor resClearance;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mydb = new DatabaseHelper(this);
        setContentView((int) R.layout.activity_search_matric_number);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        bindView();
        this.check.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (matric.getText().toString().trim().isEmpty()) {
                    matric.setError("Invalid input");
                    return;
                }
                      if(matric.getText().toString().trim()!=null){
                          res = mydb.getMatric(matric.getText().toString().trim());
                          resClearance = mydb.getClearance(matric.getText().toString().trim());
                      }else{
                          customToast("Please Enter a valid matric number",false);
                      }

                while (res.moveToNext()){
                    customToast(res.getString(0),true);
                   saveMatric(res.getString(0),res.getString(1),res.getString(2),res.getString(3),res.getString(4));
                }


                if (res == null || res.getCount() <= 0) {
                    customToast("Empty", false);
                    return;
                }else{
                    customToast("Okay", true);
                }

 if (resClearance == null || resClearance.getCount() <= 0) {
            operation = 1;
 } else {
     operation=2;
 customToast("Clearance Completed", true);
 startActivity(new Intent(searchMatricNumber.this,clearanceSection.class));
 }

            }
        });
    }

    public void customToast(String toast, boolean check) {
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
    }

    public void bindView() {
        check = (Button) findViewById(R.id.checkMatric);
        matric = (EditText) findViewById(R.id.matricNumber);
    }

public void saveMatric(String id,String fullName,String matric,String university,String dog){
    SharedPreferences sp=getApplicationContext().getSharedPreferences("userdetails",MODE_PRIVATE);
    SharedPreferences.Editor editor=sp.edit();
    editor.putString("id",id);
    editor.putString("Matric",matric);
    editor.putString("dog",dog);
    editor.putString("fullName",fullName);
    editor.putString("university",university);
    editor.apply();
}
    public void addUser(View view) {

        if (operation == 0) {
            startActivity(new Intent(this, userdetails.class));
        } else if (operation == 1) {

            Intent intent = new Intent(this, addClearance.class);
            if (matric.getText().toString().trim().isEmpty()) {
                matric.setError("Invalid input");
                intent.putExtra("MatNum", matric.getText().toString().trim());
                return;
            }

            startActivity(intent);
        }
        if(operation==2){
            // user has completely registered
            if(matric.getText().toString().trim()!=null) {
                res = mydb.getMatric(matric.getText().toString().trim());
                while(res.moveToNext()){
                    saveMatric(res.getString(0),res.getString(1),res.getString(2),res.getString(3),res.getString(4));

                }
            }
            startActivity(new Intent(this,clearanceSection.class));
        }
    }
}

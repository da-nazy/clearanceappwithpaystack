package com.example.clearanceapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addClearance extends AppCompatActivity {
    /* Access modifiers changed, original: protected */
    DatabaseHelper mydb;
    Button addClearance;
    CheckBox alumniFee,magazinFee,libraryFee,facultyFee,sportFee,toiletFee,busarFee,waterFee,schoolFee,departmentalFee,convocationFee,graduationFee;
    String  alumniFeed,magazinFeed,libraryFeed,facultyFeed,sportFeed,toiletFeed,busarFeed,waterFeed,schoolFeed,departmentalFeed,convocationFeed,graduationFeed;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mydb=new DatabaseHelper(this);

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView((int) R.layout.activity_add_clearance);

        bindView();
        initialize();
        alumniFee.setChecked(false);
        magazinFee.setChecked(false);
        facultyFee.setChecked(false);
        sportFee.setChecked(false);
        toiletFee.setChecked(false);
        busarFee.setChecked(false);
        waterFee.setChecked(false);
        schoolFee.setChecked(false);
        departmentalFee.setChecked(false);
        convocationFee.setChecked(false);
        graduationFee.setChecked(false);
        libraryFee.setChecked(false);
            alumniFee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(alumniFee.isChecked()){
                        alumniFeed="1";
                     //   Toast.makeText(getApplicationContext(),"checked",Toast.LENGTH_SHORT).show();
                    }else{
                        alumniFeed="0";
                     //   Toast.makeText(getApplicationContext(),"Not checked",Toast.LENGTH_SHORT).show();
                    }
                }
            });


        magazinFee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(magazinFee.isChecked()){
                    magazinFeed="1";
                 //   Toast.makeText(getApplicationContext(),"checked",Toast.LENGTH_SHORT).show();
                }else{
                    magazinFeed="0";
                  //  Toast.makeText(getApplicationContext(),"Not checked",Toast.LENGTH_SHORT).show();
                }
            }
        });
        facultyFee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(facultyFee.isChecked()){
                    facultyFeed="1";
                  //  Toast.makeText(getApplicationContext(),"checked",Toast.LENGTH_SHORT).show();
                }else{
                    facultyFeed="0";
                   // Toast.makeText(getApplicationContext(),"Not checked",Toast.LENGTH_SHORT).show();
                }
            }
        });
        sportFee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(sportFee.isChecked()){
                    sportFeed="1";
                //    Toast.makeText(getApplicationContext(),"checked",Toast.LENGTH_SHORT).show();
                }else{
                    sportFeed="0";
                //    Toast.makeText(getApplicationContext(),"Not checked",Toast.LENGTH_SHORT).show();
                }
            }
        });
        libraryFee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(libraryFee.isChecked()){
                    libraryFeed="1";
                   // Toast.makeText(getApplicationContext(),"checked",Toast.LENGTH_SHORT).show();
                }else{
                    libraryFeed="0";
                 //   Toast.makeText(getApplicationContext(),"Not checked",Toast.LENGTH_SHORT).show();
                }
            }
        });
        toiletFee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(toiletFee.isChecked()){
                    toiletFeed="1";
                   // Toast.makeText(getApplicationContext(),"checked",Toast.LENGTH_SHORT).show();
                }else{
                    toiletFeed="0";
                   // Toast.makeText(getApplicationContext(),"Not checked",Toast.LENGTH_SHORT).show();
                }
            }
        });
        busarFee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(busarFee.isChecked()){
                    busarFeed="1";
                    //Toast.makeText(getApplicationContext(),"checked",Toast.LENGTH_SHORT).show();
                }else{
                    busarFeed="0";
                   // Toast.makeText(getApplicationContext(),"Not checked",Toast.LENGTH_SHORT).show();
                }
            }
        });
        waterFee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(waterFee.isChecked()){
                    waterFeed="1";
                  //  Toast.makeText(getApplicationContext(),"checked",Toast.LENGTH_SHORT).show();
                }else{
                    waterFeed="0";
                   // Toast.makeText(getApplicationContext(),"Not checked",Toast.LENGTH_SHORT).show();
                }
            }
        });
        schoolFee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(schoolFee.isChecked()){
                    schoolFeed="1";
                    //Toast.makeText(getApplicationContext(),"checked",Toast.LENGTH_SHORT).show();
                }else{
                    schoolFeed="0";
                   // Toast.makeText(getApplicationContext(),"Not checked",Toast.LENGTH_SHORT).show();
                }
            }
        });
        departmentalFee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(departmentalFee.isChecked()){
                    departmentalFeed="1";
                   // Toast.makeText(getApplicationContext(),"checked",Toast.LENGTH_SHORT).show();
                }else{
                    departmentalFeed="0";
                  //  Toast.makeText(getApplicationContext(),"Not checked",Toast.LENGTH_SHORT).show();
                }
            }
        });
        convocationFee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(convocationFee.isChecked()){
                    convocationFeed="1";
                  //  Toast.makeText(getApplicationContext(),"checked",Toast.LENGTH_SHORT).show();
                }else{
                    convocationFeed="0";
                   // Toast.makeText(getApplicationContext(),"Not checked",Toast.LENGTH_SHORT).show();
                }
            }
        });

        graduationFee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(graduationFee.isChecked()){
                    graduationFeed="1";
                   // Toast.makeText(getApplicationContext(),"checked",Toast.LENGTH_SHORT).show();
                }else{
                    graduationFeed="0";
                   // Toast.makeText(getApplicationContext(),"Not checked",Toast.LENGTH_SHORT).show();
                }
            }
        });

        addClearance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(check()){
                if(mydb.addClearance(getMatric(),alumniFeed,magazinFeed,libraryFeed,facultyFeed,sportFeed,toiletFeed,busarFeed,waterFeed,schoolFeed,departmentalFeed,convocationFeed,graduationFeed)){
                 Toast.makeText(getApplicationContext(),"Your clearance was succefull",Toast.LENGTH_SHORT).show();
                 Intent intent=new Intent(addClearance.this,clearanceSection.class);
                 startActivity(intent);
                }
            }
            }
        });
    }
    public boolean check(){
        boolean valid=false;
        if(alumniFeed.equals("0")||magazinFeed.equals("0")||libraryFeed.equals("0")||facultyFeed.equals("0")||sportFeed.equals("0")||toiletFeed.equals("0")||busarFeed.equals("0")||waterFeed.equals("0")||schoolFeed.equals("0")||departmentalFeed.equals("0")||convocationFeed.equals("0")||graduationFeed.equals("0")){
            valid=false;
            Toast.makeText(getApplicationContext(),"Please complete your clearance",Toast.LENGTH_SHORT).show();

        }else{
            valid=true;
        }

        return valid;
    }
    public void bindView(){
        alumniFee=findViewById(R.id.alufee);
        magazinFee=findViewById(R.id.magfee);
        libraryFee=findViewById(R.id.libfee);
        facultyFee=findViewById(R.id.facfee);
       sportFee=findViewById(R.id.sportfee);
       toiletFee=findViewById(R.id.toiletfee);
       busarFee=findViewById(R.id.bursfee);
       waterFee=findViewById(R.id.watfee);
       schoolFee=findViewById(R.id.schoolfee);
       departmentalFee=findViewById(R.id.departfee);
       convocationFee=findViewById(R.id.convocfee);
       graduationFee=findViewById(R.id.gradfee);
       addClearance=findViewById(R.id.addClearance);

    }
    public void initialize(){
        alumniFeed="0";
        magazinFeed="0";
        libraryFeed="0";
        facultyFeed="0";
        sportFeed="0";
        toiletFeed="0";
        busarFeed="0";
        waterFeed="0";
        schoolFeed="0";
        departmentalFeed="0";
        convocationFeed="0";
        graduationFeed="0";
    }
    public String  getMatric(){
        SharedPreferences sp=getSharedPreferences("userdetails", MODE_PRIVATE);
      return sp.getString("Matric"," ");
    }
}

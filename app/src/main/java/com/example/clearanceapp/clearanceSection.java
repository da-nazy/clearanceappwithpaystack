package com.example.clearanceapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class clearanceSection extends AppCompatActivity {
    private TextView congrats;
   String matric;
   String dog;
   String name;
   String university;
   Button transRequest;
   String id;
    DatabaseHelper db;
    Cursor res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
         db=new DatabaseHelper(this);

        bindView();

        setContentView(R.layout.activity_clearance_section);
        getMatric();
        congrats=findViewById(R.id.congrats);

      //  Toast.makeText(this,matric+dog+name+university,Toast.LENGTH_SHORT);
        congrats.setText(name+" From " +university+"  with Matric Number "+matric+" has completed his '\' her clearance.");

    }
    public void  getMatric(){
        SharedPreferences sp=getSharedPreferences("userdetails", MODE_PRIVATE);
        id=sp.getString("id"," ");
       matric=sp.getString("Matric"," ");
       dog=sp.getString("dog"," ");
       name=sp.getString("fullName"," ");
       university=sp.getString("university"," ");
    }


    public void bindView(){
        transRequest=(Button) findViewById(R.id.reqTranscript);
    }

    public void  checkRef(View view){
        res=db.checkRef(id);
        String refNum="";
        while(res.moveToNext()){
              // check if the value is null
            // if the value is null go else  show dialog
            refNum=res.getString(0)+"";


        }
        if(refNum.matches("null")){
            Toast.makeText(getApplicationContext(),refNum,Toast.LENGTH_SHORT).show();
            transcript();
        }else{
            transcriptExist(refNum);
        }
    }
    public void transcriptExist(String msg){
        ViewGroup viewGroup=findViewById(android.R.id.content);

        View transcriptView= LayoutInflater.from(this).inflate(R.layout.transcript_gen,viewGroup,false);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(transcriptView);
         TextView txt;
         Button gtMenu;
         txt=transcriptView.findViewById(R.id.trans_gen_txt);
         gtMenu=transcriptView.findViewById(R.id.cmp_trans_gen);
         txt.setText("Transcript payment of  #8000 was succesfull with reference number "+msg+" \\n Come to the department with your reference number.");

        final AlertDialog alertDialog=builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.show();
        gtMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(clearanceSection.this,searchMatricNumber.class));
                alertDialog.dismiss();
            }
        });
    }

    public void transcript() {
        ViewGroup viewGroup=findViewById(android.R.id.content);

        View transcriptView= LayoutInflater.from(this).inflate(R.layout.transcript_dialog,viewGroup,false);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(transcriptView);
        Button canc_transcript,cont_transcript;
        canc_transcript=transcriptView.findViewById(R.id.canc_transcript);
        cont_transcript=transcriptView.findViewById(R.id.cont_transcript);
        final AlertDialog alertDialog=builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.show();
        cont_transcript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clearanceSection.this, transcript_checkout.class);
                intent.putExtra("transcript", 8000);
                startActivity(intent);
            }
        });
        canc_transcript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}
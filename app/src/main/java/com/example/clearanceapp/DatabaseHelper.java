package com.example.clearanceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CLEARANCE_TABLE = "CLEARANCE";
    public static final String COL_10 = "SPORTFEE";
    public static final String COL_11 = "TOILETFEE";
    public static final String COL_12 = "BURSARFEE";
    public static final String COL_13 = "WATERFEE";
    public static final String COL_14 = "SCHOOLFEE";
    public static final String COL_15 = "DEPARTMENTALFEE";
    public static final String COL_16 = "CONVOCATIONFEE";
    public static final String COL_17 = "GRADUATIONFEE";
    public static final String COL_2 = "MATRIC_NO";
    public static final String COL_3 = "FULLNAME";
    public static final String COL_4 = "UNIVERSITY";
    public static final String COL_5 = "DOG";
    public static final String COL_6 = "ALUMNIFEE";
    public static final String COL_7 = "MAGAZINEFEE";
    public static final String COL_8 = "LIBRARYFEE";
    public static final String COL_9 = "FACULTYFEE";
    public static final String DATABASE_NAME = "matric.db";
    public static final String USER_TABLE = "USER";
   public static final String  COL_18="TRANSCRIPT";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table USER (ID INTEGER PRIMARY KEY AUTOINCREMENT,FULLNAME TEXT,MATRIC_NO TEXT UNIQUE,UNIVERSITY TEXT,DOG TEXT,TRANSCRIPT TEXT)");
        db.execSQL("create table CLEARANCE(ID INTEGER PRIMARY KEY AUTOINCREMENT, ALUMNIFEE TEXT,MATRIC_NO TEXT UNIQUE,MAGAZINEFEE TEXT, LIBRARYFEE TEXT, FACULTYFEE TEXT , SPORTFEE TEXT, TOILETFEE TEXT, BURSARFEE TEXT, WATERFEE TEXT,SCHOOLFEE TEXT,DEPARTMENTALFEE TEXT,CONVOCATIONFEE TEXT,GRADUATIONFEE TEXT )");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE  IF EXISTS USER");
        db.execSQL("DROP TABLE IF EXISTS CLEARANCE");
        onCreate(db);
    }

    public boolean insterUser(String matric_no, String fullName, String dog, String university) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, matric_no);
        contentValues.put(COL_3, fullName);
        contentValues.put(COL_4, university);
        contentValues.put(COL_5, dog);
        if (db.insert(USER_TABLE, null, contentValues) == -1) {
            return false;
        }
        return true;
    }

    public Cursor getMatric(String matric) {
        return getWritableDatabase().rawQuery("select * from USER where MATRIC_NO=?", new String[]{matric});
    }

    public Cursor getClearance(String matric) {
        return getWritableDatabase().rawQuery("select * from CLEARANCE where MATRIC_NO=?", new String[]{matric});
    }

    public Cursor checkRef(String id){
        return getWritableDatabase().rawQuery("select TRANSCRIPT from user where id=?",new String[]{id});
    }

    public boolean addUserReference(String id, String ref){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL_18,ref);
        long result=db.update(USER_TABLE,cv,"id=?",new String[]{id});

        if(result==-1){
            return false;

        }else{

            return true;
        }

    }

    public boolean addClearance(String matric_no, String alumniFee, String magazinFee, String libraryFee, String facultyFee, String sportFee, String toiletFee, String busarFee, String waterFee, String schoolFee, String departmentalFee, String convocationFee, String graduationFee) {
     SQLiteDatabase db=getWritableDatabase();
     ContentValues contentValues=new ContentValues();
     contentValues.put(COL_2,matric_no);
     contentValues.put(COL_6,alumniFee);
     contentValues.put(COL_7,magazinFee);
     contentValues.put(COL_8,libraryFee);
     contentValues.put(COL_9,facultyFee);
     contentValues.put(COL_10,sportFee);
     contentValues.put(COL_11,toiletFee);
     contentValues.put(COL_12,busarFee);
     contentValues.put(COL_13,waterFee);
     contentValues.put(COL_14,schoolFee);
     contentValues.put(COL_15,departmentalFee);
     contentValues.put(COL_16,convocationFee);
     contentValues.put(COL_17,graduationFee);
       if(db.insert(CLEARANCE_TABLE,null,contentValues)==-1){
           return false;
       }else{
           return true;
       }
    }

}

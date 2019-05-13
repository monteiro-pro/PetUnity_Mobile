package com.example.petunity_mobile.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.petunity_mobile.MyApp;

public class DataBaseOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pet_unity_mobile";
    public static final int VERSION_DB = 1;
    public  static  final String TABLE_USUARIO = "usuario";

    private static DataBaseOpenHelper instance;

    public static DataBaseOpenHelper getInstance(){
        if(instance == null) instance = new DataBaseOpenHelper();
        return instance;
    }

    private DataBaseOpenHelper() {
        super(MyApp.getmContext(), DATABASE_NAME, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

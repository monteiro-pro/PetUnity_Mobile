package com.example.petunity_mobile.DataBase;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Create {

    public void  createTableUsuario(){
        SQLiteDatabase db = DataBaseOpenHelper.getInstance().getWritableDatabase();
        String coluns = "( id INTEGER PRIMARY KEY autoincrement, nome varchar(30) NOT NULL, email varchar(20) NOT NULL, telefone varchar(10) NOT NULL, endereco varchar(50) NOT NULL )";
        String query = "CREATE TABLE IF NOT EXISTS " + DataBaseOpenHelper.TABLE_USUARIO + coluns;
        db.execSQL(query);
    }

    public void  removeTableUsuario(){
        SQLiteDatabase db = DataBaseOpenHelper.getInstance().getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + DataBaseOpenHelper.TABLE_ANIMAL;
        db.execSQL(query);
    }

    public void  createTableAnimal(){
        SQLiteDatabase db = DataBaseOpenHelper.getInstance().getWritableDatabase();
        String coluns = "( id INTEGER PRIMARY KEY autoincrement, nome varchar(30) NOT NULL, especie varchar(10) NOT NULL, raca varchar(20) NOT NULL, sexo varchar(10) NOT NULL, foto varchar(500) )";
        String query = "CREATE TABLE IF NOT EXISTS " + DataBaseOpenHelper.TABLE_ANIMAL + coluns;
        db.execSQL(query);
    }

    public void  removeTableAnimal(){
        SQLiteDatabase db = DataBaseOpenHelper.getInstance().getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + DataBaseOpenHelper.TABLE_ANIMAL;
        db.execSQL(query);
    }
}

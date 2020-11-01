package com.example.diseaseclassification;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";
    public static final String passwordField = "password";
    public static final String usernameField = "password";

    String bcryptHashString;
    public DBHelper (Context context){
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(usernameField,username);
        contentValues.put(passwordField, password);
        long results = MyDB.insert("users",  null, contentValues);
        if (results == -1) return false;
        else return true;

    }

    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        cursor.close();
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean updatePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(passwordField, password);
        long results = MyDB.update("users", contentValues, "username= ?",new String[]{username});
        if (results == -1) return false;
        else return true;
    }

    public Boolean checkUsernamePass(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});

        if (cursor != null && cursor.moveToFirst()) {
            bcryptHashString = cursor.getString(cursor.getColumnIndex(passwordField));
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
            if (result.verified)
                return true;
            else{
                cursor.close();
                return false;
            }
        }else {
            return false;
        }
    }


}

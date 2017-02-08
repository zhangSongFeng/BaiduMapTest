package com.example.zsfandly.baidumaptest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zsfandly on 2017/2/7.
 */

public class DatebaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="track.db";
    private static final String TABLE_TRACK="track";
    private static final  String TABLE_TRACK_DETATL="track_detail";
    public static final String ID="_id";
    //跟踪表
    public static final String TRACK_NAME="track_name";
    public static  final String CREATE_DATE="create_date";
    public static final String START_LOC="start_loc";
    public static final String END_LOC="end_loc";
    //明细表
    public static  final String TID="tid";
    public static final String LAT_REAL="lat_real";
    public static final String  LNG_REAL="lng_real";
    private static  final String CREAT_TABLE_TRACK="create table track(_id integer primary key autoincrement,track_name text,start_loc text,end_loc text)";
    private static final String CREAT_TABLE_TRACK_DETATL="create table track_detail(_id integer primary key autoincrement,tid integer not null,lat_real text,lng_real text)";
    private static final int VERSION=1;


    public DatebaseHelper(Context context) {
        super(context, TRACK_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_TABLE_TRACK);
        db.execSQL(CREAT_TABLE_TRACK_DETATL);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}

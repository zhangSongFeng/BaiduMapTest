package com.example.zsfandly.baidumaptest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.R.attr.id;

/**
 * Created by zsfandly on 2017/2/7.
 */

public class DatabaseAdapter {
    private DatebaseHelper dbHelper;
    private DatabaseAdapter(Context context){
        dbHelper=new DatebaseHelper(context);
    }
    public int addTrack(Track track){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DatebaseHelper.TRACK_NAME,track.getTrack_name());
        values.put(DatebaseHelper.CREATE_DATE,track.getCreate_date());
        values.put(DatebaseHelper.START_LOC,track.getStart_loc());
        values.put(DatebaseHelper.END_LOC,track.getEnd_loc());
        db.close();
        return id;

    }
    public void updateEndLoc(String endLoc, int id) {
        String sql = "update track set end_loc=? where _id=?";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql, new Object[] { endLoc, id });
        db.close();
    }
    public void addTrackDetail(int tid, double lat, double lng) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "insert into track_detail(tid,lat,lng) values(?,?,?)";
        db.execSQL(sql, new Object[] { tid, lat, lng });
        db.close();
    }
    public ArrayList<TrackDetail> getTrackDetails(int id) {
        String sql = "select _id,lat,lng from track_detail where tid=? order by _id desc";
        ArrayList<TrackDetail> list = new ArrayList<TrackDetail>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql, new String[] { String.valueOf(id) });
        if (c != null) {
            TrackDetail detail = null;
            while (c.moveToNext()) {
                detail=new TrackDetail(c.getInt(0),c.getDouble(1),c.getDouble(2));
                list.add(detail);
            }
            c.close();
        }
        return list;
    }
    public ArrayList<Track> getTracks() {
        ArrayList<Track> tracks = new ArrayList<Track>();
        String sql = "select _id,track_name,create_date,start_loc,end_loc from track ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        Track t = null;
        if (c != null) {
            while (c.moveToNext()) {
                t = new Track(c.getInt(0), c.getString(1), c.getString(2),
                        c.getString(3), c.getString(4));
                tracks.add(t);
            }
            c.close();
        }
        db.close();
        return tracks;
    }

}

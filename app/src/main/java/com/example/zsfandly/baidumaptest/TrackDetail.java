package com.example.zsfandly.baidumaptest;

/**
 * Created by zsfandly on 2017/2/7.
 */

public class TrackDetail {
    private int id;
    private double lat;
    private double lng;
    private Track track;

    public TrackDetail() {
    }

    public TrackDetail(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;

    }

    public TrackDetail(int id, double lat, double lng) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;

    }

    public int getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public Track getTrack() {
        return track;
    }



}

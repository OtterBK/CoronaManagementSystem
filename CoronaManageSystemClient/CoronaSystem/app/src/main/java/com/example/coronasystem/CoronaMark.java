package com.example.coronasystem;


import java.util.Calendar;

public class CoronaMark {

    int id; //확진자 id
    Calendar date; //날짜 및 시간
    double latitude; //위도
    double longitude; //경도
    String detail; //추가 설명

    public CoronaMark(int id, Calendar date, double lati, double longi, String detail){

        this.id = id;
        this.date = date;
        this.latitude = lati;
        this.longitude = longi;
        if(detail == null) this.detail = "";
        else this.detail = detail;

    }

    public int getYear(){
        return this.date.get(Calendar.YEAR);
    }

    public int getMonth(){
        return this.date.get(Calendar.MONTH);
    }

    public int getDay(){
        return this.date.get(Calendar.DATE);
    }

    public int getHour(){
        return this.date.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinute(){
        return this.date.get(Calendar.MINUTE);
    }

    public int getSecond(){
        return this.date.get(Calendar.SECOND);
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return  this.longitude;
    }

}
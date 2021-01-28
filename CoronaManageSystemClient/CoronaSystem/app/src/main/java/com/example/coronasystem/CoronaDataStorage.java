package com.example.coronasystem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class CoronaDataStorage {

    private static ArrayList<String[]> coronaMapData = new ArrayList<String[]>(1);
    private static ArrayList<String[]> coronicData = new ArrayList<String[]>(1);
    private static ArrayList<String[]> coronicDataToday = new ArrayList<String[]>(1);;
    private static LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> areaData;

    private static ArrayList<String> cityList = new ArrayList<String>();

    public static void setCoronaMapData(ArrayList<String[]> data){
        coronaMapData = data;
    }

    public static ArrayList<String[]> getCoronaMapData(){
        return coronaMapData;
    }

    public static void setCoronicData(ArrayList<String[]> data){
        coronicData = data;
    }

    public static ArrayList<String[]> getCoronicData(){
        return coronicData;
    }

    public static void setCoronicDataToday(ArrayList<String[]> data){
        coronicDataToday = data;
    }

    public static ArrayList<String[]> getCoronicDataToday(){
        return coronicDataToday;
    }

    public static void setAreaData(LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> data){
        areaData = data;
        cityList.clear();
        cityList.addAll(areaData.keySet());
    }

    public static LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> getAreaData(){
        return areaData;
    }

    public static String[] getArea_cityList(){
       return cityList.toArray(new String[cityList.size()]);
    }

    public static String[] getArea_townList(String cityName){
        Collection<String> townList = areaData.get(cityName).keySet();
        return townList.toArray(new String[townList.size()]);
    }

    public static String[] getArea_sareaList(String cityName, String townName){
        ArrayList<String> sareaList = areaData.get(cityName).get(townName);
        if(sareaList == null) return null;
        return sareaList.toArray(new String[sareaList.size()]);
    }

}

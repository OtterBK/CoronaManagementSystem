package com.example.coronasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapCircle;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;

import LocationManager.GpsTracker;
import network.RmiInterface;


public class MainActivity extends AppCompatActivity {
    private GpsTracker gpsTracker;
    private final String serverIP = "218.48.87.24"; //서버 접근시에 사용

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    private final String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private MapView.MapViewEventListener myMapViewEvent;
    private MapView mapView;
    private boolean isRoadMode = false;
    private ArrayList<CoronaMark> lastMarkList = new ArrayList<CoronaMark>();
    private net.daum.mf.map.api.MapPolyline lastPolyLine;
    private int markIndex = 0;

    public static RmiInterface server;
    public static MainActivity mainActivity;

    private TextView tv_total;
    private TextView tv_today;

    private Button btn_find_date;
    private Button btn_find_location;
    private Button btn_search_prev;
    private Button btn_search_exit;
    private Button btn_search_next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mainActivity = this;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_api_m);

        new Conn().execute();

        mapView = new MapView(this); //맵뷰

        ViewGroup mapViewContainer = findViewById(R.id.map_View);
        mapViewContainer.addView(mapView); //지도 생성

        if (!checkLocationServicesStatus()) { //gps 사용권한 확인

            showDialogForLocationServiceSetting();
        } else {

            checkRunTimePermission(); //런타임 사용권한 확인
        }

        gpsTracker = new GpsTracker(MainActivity.this); //gps 추적기 생성, 앱 꺼질때까지 계속
        double latitude = gpsTracker.getLatitude();
        double longitude = gpsTracker.getLongitude();

        mapView.moveCamera(CameraUpdateFactory.newMapPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), 5.4f)); //지도 중심 설정

        //이벤트
        myMapViewEvent = new MyMapViewEvent();

        mapView.setMapViewEventListener(myMapViewEvent);

        btn_find_date = (Button) findViewById(R.id.btn_find_date);
        btn_find_location = (Button) findViewById(R.id.btn_find_location);
        btn_search_prev = (Button) findViewById(R.id.btn_search_prev);
        btn_search_exit = (Button) findViewById(R.id.btn_search_exit);
        btn_search_next = (Button) findViewById(R.id.btn_search_next);
        tv_today = (TextView) findViewById(R.id.tv_today);
        tv_total = (TextView) findViewById(R.id.tv_total);
        tv_today.setText("명");
        tv_total.setText("명");

        btn_find_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindDateActivity.class);
                startActivity(intent);
            }
        });

        btn_find_location.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindLocationActivity.class);
                startActivity(intent);
            }
        });

        btn_search_prev.setOnClickListener(new RoadButtonClickEvent());

        btn_search_exit.setOnClickListener(new RoadButtonClickEvent());

        btn_search_next.setOnClickListener(new RoadButtonClickEvent());
    }

    private void updateMarks() {
        double radius = (mapView.getZoomLevelFloat() + 1.8); //지도 줌 상태에 따른 원 반지름 계산
        radius *= radius * radius;
        radius += 10;

        if(isRoadMode) { //로드 모드일 때

            mapView.removeAllCircles(); //원 다 치워
            mapView.removeAllPolylines(); //선도 다 치워
            mapView.removeAllPOIItems(); //마커도 다 치워

            net.daum.mf.map.api.MapPolyline polyLine = new net.daum.mf.map.api.MapPolyline(); //import가 안돼...
            polyLine.setTag(1000);

            for(int i = 0; i < lastMarkList.size(); i++){
                CoronaMark mark = lastMarkList.get(i);
                double latitude = mark.getLatitude();
                double longitude = mark.getLongitude();

                MapCircle circle = new MapCircle(MapPoint.mapPointWithGeoCoord(latitude, longitude), // center
                        (int) radius,
                        Color.argb(255, 255, 0, 0), // strokeColor
                        Color.argb(50, 255, 128, 0) // fillColor
                );
                circle.setTag(i);
                mapView.addCircle(circle); //원그리기

                MapPOIItem markerItem = new MapPOIItem(); //마커
                markerItem.setAlpha(0.75f);
                markerItem.setMarkerType(MapPOIItem.MarkerType.BluePin);
                markerItem.setItemName(mark.getMonth() + " 월 " + mark.getDay() + "일\n" + mark.getHour() + "시 " + mark.getMinute() + "분");
                markerItem.setMapPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude));
                markerItem.setShowDisclosureButtonOnCalloutBalloon(false);
                mapView.addPOIItem(markerItem); //마커 그리기기

                polyLine.setLineColor(Color.argb(128, 255, 51, 0));
                polyLine.addPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude)); //선그리기
            }

            mapView.addPolyline(polyLine);
            lastPolyLine = polyLine;


        } else {
            mapView.removeAllCircles();
            //mapView.removeAllPolylines(); //선도 다 치워
            //mapView.removeAllPOIItems(); //마커도 다 치워

            String today_coronic = Integer.toString(CoronaDataStorage.getCoronicDataToday().size());//오늘 확진자 수 나타냄
            String total_coronic = Integer.toString(CoronaDataStorage.getCoronicData().size());//누적 확진자 수 나타냄
            tv_today.setText(today_coronic+"명");
            tv_total.setText(total_coronic+"명");

            ArrayList<String[]> coronaMapData = CoronaDataStorage.getCoronaMapData();

            if (coronaMapData != null) {

                for (String[] data : coronaMapData) {

                    try {
                        double latitude = Double.parseDouble(data[2]);
                        double longitude = Double.parseDouble(data[3]);
                        MapCircle circle = new MapCircle(MapPoint.mapPointWithGeoCoord(latitude, longitude), // center
                                (int) radius,
                                Color.argb(255, 0, 0, 0), // strokeColor
                                Color.argb(50, 255, 0, 0) // fillColor
                        );
                        circle.setTag(1);
                        mapView.addCircle(circle); //원그리기

                        MapPOIItem markerItem = new MapPOIItem();
                        markerItem.setAlpha(0.3f);
                        markerItem.setMarkerType(MapPOIItem.MarkerType.RedPin);
                        markerItem.setItemName(data[0] + "\n" + data[5] + "번 확진자");
                        markerItem.setMapPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude));
                        markerItem.setShowDisclosureButtonOnCalloutBalloon(false);
                        mapView.addPOIItem(markerItem); //마커 그리기기
                    } catch (Exception e) {
                        Log.e("CMS-UpdateCircle1", data.toString() + "데이터 파싱 실패");
                    }
                }
            }
        }
    }

    public void drawCoronicRoad(String coronicId){
        new FindCoronicMapFromID().execute(coronicId);
        isRoadMode = true; //로드 모드 true
        tv_today.setVisibility(View.INVISIBLE);
        tv_today.setEnabled(false);
        tv_total.setVisibility(View.INVISIBLE);
        tv_total.setEnabled(false);
        ImageView countImage = findViewById(R.id.count);
        countImage.setVisibility(View.INVISIBLE);
        countImage.setEnabled(false);

        btn_search_prev.setVisibility(View.VISIBLE);
        btn_search_next.setVisibility(View.VISIBLE);
        btn_search_exit.setVisibility(View.VISIBLE);

        btn_search_prev.setEnabled(true);
        btn_search_next.setEnabled(true);
        btn_search_exit.setEnabled(true);
    }

    private class RoadButtonClickEvent implements View.OnClickListener{
        @Override
        public void onClick(View v){
            int vId = v.getId();
            if(vId == btn_search_prev.getId()){
                markIndex -= 1;
                if(markIndex < 0) {
                    markIndex = 0;
                    Toast.makeText(MainActivity.this, "첫 동선입니다.", Toast.LENGTH_LONG);
                }
                if(lastMarkList.size() > markIndex) {
                    CoronaMark mark = lastMarkList.get(markIndex);
                    mapView.moveCamera(CameraUpdateFactory.newMapPoint(MapPoint.mapPointWithGeoCoord(mark.getLatitude(), mark.getLongitude()))); //지도 중심 설정
                }
            } else if(vId == btn_search_next.getId()){
                markIndex += 1;
                if(markIndex >= lastMarkList.size()) {
                    Toast.makeText(MainActivity.this, "마지막 동선입니다.", Toast.LENGTH_LONG);
                    markIndex = lastMarkList.size()-1;
                }
                if(lastMarkList.size() > markIndex) {
                    CoronaMark mark = lastMarkList.get(markIndex);
                    mapView.moveCamera(CameraUpdateFactory.newMapPoint(MapPoint.mapPointWithGeoCoord(mark.getLatitude(), mark.getLongitude()))); //지도 중심 설정
                }
            } else if(vId == btn_search_exit.getId()){
                btn_search_prev.setVisibility(View.INVISIBLE);
                btn_search_next.setVisibility(View.INVISIBLE);
                btn_search_exit.setVisibility(View.INVISIBLE);

                btn_search_prev.setEnabled(false);
                btn_search_next.setEnabled(false);
                btn_search_exit.setEnabled(false);

                tv_today.setVisibility(View.VISIBLE);
                tv_today.setEnabled(true);
                tv_total.setVisibility(View.VISIBLE);
                tv_total.setEnabled(true);
                ImageView countImage = findViewById(R.id.count);
                countImage.setVisibility(View.VISIBLE);
                countImage.setEnabled(true);

                mapView.removeAllCircles(); //원 다 치워
                mapView.removeAllPolylines(); //선도 다 치워
                mapView.removeAllPOIItems(); //마커도 다 치워

                isRoadMode = false;
                updateMarks();
            }
        }
    }

    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what == HandlerMessageType.SERVER_CONN_SUC.ordinal())
                Toast.makeText(MainActivity.this, "서버 연결 확인", Toast.LENGTH_LONG).show();
            else if(msg.what == HandlerMessageType.SERVER_CONN_FAIL.ordinal())
                Toast.makeText(MainActivity.this, "서버 연결 확인", Toast.LENGTH_LONG).show();
        }
    };

    private class FindCoronicMapFromID extends AsyncTask<String, Void, Boolean>{

        @Override
        protected Boolean doInBackground(String... params){
            boolean isConnected = false;

            String coronicId = params[0];

            isConnected = server.checkConnection();
            if(isConnected){
                ArrayList<String[]> coronaMapData = server.requestCoronaMapDataFromId(coronicId);
                ArrayList<CoronaMark> markList = new ArrayList<CoronaMark>(coronaMapData.size()); //마크 정보

                for(String[] markData : coronaMapData){ //파싱
                    try{
                        String strDate = markData[0]; //날짜값
                        String[] date_splited = strDate.split("-");
                        int year = Integer.parseInt(date_splited[0]);
                        int month = Integer.parseInt(date_splited[1]);
                        int day = Integer.parseInt(date_splited[2]);

                        String strTime = markData[1]; //시간값
                        String[] time_splited = strTime.split(":");
                        int hour = Integer.parseInt(time_splited[0]);
                        int min = Integer.parseInt(time_splited[1]);
                        int sec = Integer.parseInt(time_splited[1]);

                        Calendar date = Calendar.getInstance();
                        date.set(Calendar.YEAR, year);
                        date.set(Calendar.MONTH, month-1);
                        date.set(Calendar.DATE, day);
                        date.set(Calendar.HOUR_OF_DAY, hour);
                        date.set(Calendar.MINUTE, min);
                        date.set(Calendar.SECOND, sec);

                        double lati = Double.parseDouble(markData[2]);
                        double longi = Double.parseDouble(markData[3]);

                        String detail = markData[4];
                        int id = Integer.parseInt(markData[5]);

                        CoronaMark mark = new CoronaMark(id, date, lati, longi, detail); //마크 생성
                        markList.add(mark);

                    }catch(NumberFormatException e){
                        e.printStackTrace();
                        Log.d("CMS-FindCoronicMapFromID_Parsing",markData.toString()+"을 파싱중 에러 발생");
                    }
                }

                for(int i = 1; i < markList.size(); i++){ //마크들 정렬,  날짜 및 시간 순
                    CoronaMark insertMark = markList.get(i); //비교할 것
                    for(int j = 0; j < i; j++){
                        CoronaMark baseMark = markList.get(j); //비교대상
                        if(baseMark.getYear() > insertMark.getYear()){
                            CoronaMark tmpMark = insertMark;
                            markList.remove(i);
                            markList.add(j, tmpMark);
                        } else if(baseMark.getMonth() > insertMark.getMonth()){
                            CoronaMark tmpMark = insertMark;
                            markList.remove(i);
                            markList.add(j, tmpMark);
                        } else if(baseMark.getDay() > insertMark.getDay()){
                            CoronaMark tmpMark = insertMark;
                            markList.remove(i);
                            markList.add(j, tmpMark);
                        } else if(baseMark.getHour() > insertMark.getHour()){
                            CoronaMark tmpMark = insertMark;
                            markList.remove(i);
                            markList.add(j, tmpMark);
                        } else if(baseMark.getMinute() > insertMark.getMinute()){
                            CoronaMark tmpMark = insertMark;
                            markList.remove(i);
                            markList.add(j, tmpMark);
                        } else if(baseMark.getSecond() > insertMark.getSecond()){
                            CoronaMark tmpMark = insertMark;
                            markList.remove(i);
                            markList.add(j, tmpMark);
                        }
                    }
                }

                lastMarkList = markList; //마크 리스트 갱신
                markIndex = 0; //마크 인덱스 초기화
                if(markList.size() > 0) {
                    CoronaMark firstMark = markList.get(0);
                   mapView.moveCamera(CameraUpdateFactory.newMapPoint(MapPoint.mapPointWithGeoCoord(firstMark.getLatitude(), firstMark.getLongitude()), 2.4f)); //지도 중심 설정
                }
                updateMarks(); //마커 업데이트 호출

                /*if(lastPolyLine != null){ //모든 마커 다 보여주는 연출
                    net.daum.mf.map.api.MapPointBounds mapPointBounds = new net.daum.mf.map.api.MapPointBounds(lastPolyLine.getMapPoints());
                    int padding = 100; // px
                    mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding));
                }*/

            } else {
                Message message = handler.obtainMessage(HandlerMessageType.SERVER_CONN_FAIL.ordinal());
                handler.sendMessage(message);
            }


            return isConnected;
        }

    }

    private class Conn extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            boolean isConnected = false;

            try {
                CallHandler callHandler = new CallHandler();
                Client client = new Client(serverIP, 3099, callHandler);
                server = (RmiInterface) client.getGlobal(RmiInterface.class); //RMI연결
                isConnected = server.checkConnection();
                //client.close(); //닫기
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (isConnected) { //성공시

                Message message = handler.obtainMessage(HandlerMessageType.SERVER_CONN_SUC.ordinal());
                handler.sendMessage(message);

                ArrayList<String[]> coronaMapData = server.requestCoronaMapData();
                CoronaDataStorage.setCoronaMapData(coronaMapData); //코로나 동선 정보

                LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> areaData = server.requestAreaData();
                CoronaDataStorage.setAreaData(areaData); //지역 정보

                ArrayList<String[]>  coronicData = server.requestCoronicData(); //코로닉 정보
                CoronaDataStorage.setCoronicData(coronicData);

                Calendar today = Calendar.getInstance();
                String todayDate = today.get(Calendar.YEAR) + "-" + (today.get(Calendar.MONTH) +1) + "-" + today.get(Calendar.DATE);

                ArrayList<String[]>  coronicDataToday = server.requestCoronicDataFromDate(todayDate); //오늘자 코로닉 정보
                CoronaDataStorage.setCoronicDataToday(coronicDataToday);

                updateMarks(); //데이터 불러오면 다시 서클 업데이트
            } else { //서버 연결 실패시
                Message message = handler.obtainMessage(HandlerMessageType.SERVER_CONN_FAIL.ordinal());
                handler.sendMessage(message);
            }
            return isConnected;
        }
    }

    private class MyMapViewEvent implements MapView.MapViewEventListener {

        @Override
        public void onMapViewInitialized(MapView mapView) {

        }

        @Override
        public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewZoomLevelChanged(MapView mapView, int i) {
            Log.d("CMS-zoomlevel", mapView.getZoomLevelFloat() + "");
            updateMarks();
        }

        @Override
        public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

        }
    }


    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if (check_result) {

                //위치 값을 가져올 수 있음
                ;
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(MainActivity.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();


                } else {

                    Toast.makeText(MainActivity.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission() {

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음


        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(MainActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자에게 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }

    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}
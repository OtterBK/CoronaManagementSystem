package com.example.coronasystem;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import static com.example.coronasystem.MainActivity.*;

public class CoronicListActivity extends AppCompatActivity {
    ListView coronicListView;
    ArrayList<String[]> coronicList = new ArrayList<String[]>();
    ArrayAdapter<String> coronicListView_Apdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coronic_list);
        coronicListView = (ListView)findViewById(R.id.list_info);
        coronicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] coronic = coronicList.get(position);
                String coronicId = coronic[0];
                mainActivity.drawCoronicRoad(coronicId); //동선 그리기 요청
                finish();
            }
        });
        coronicListView_Apdater = new ArrayAdapter<String>(CoronicListActivity.this, android.R.layout.simple_list_item_1);
        /*Button btn_home = (Button)findViewById(R.id.btn_home);//홈버튼
        btn_home.setOnClickListener(new View.OnClickListener() {//이벤트처리, 홈이동

            @Override
            public void onClick(View view) { //홈으로

                finish();
            }
        });*/

        Intent intent = getIntent();

        String searchType = intent.getStringExtra("searchType");
        String searchParam = intent.getStringExtra("searchParam");
        if(searchType.equals("date")){ //만약 날짜로 찾기라면
            new FindDateConn().execute(searchParam); //찾을 날짜 값과 함께 서버에 요청 시도
        } else if(searchType.equals("address")) { //만약 주소로 찾기라면
            new FindAddressConn().execute(searchParam); //찾을 주소값과 함께 서버에 요청 시도
        } else {
            Toast.makeText(CoronicListActivity.this, "잘못된 요청 값", Toast.LENGTH_LONG).show();
            finish();
        }
        //어댑터 초기화
    }

    void listDataSetting() {
        MyAdapter myAdapter = new MyAdapter();
        int gender;
        for (int i = 0; i < coronicList.size(); i++) {//리스트의 끝까지 추가
            String[] coronic = coronicList.get(i);
            if(coronic[2].equals("남"))
                gender = R.drawable.m;
            else
                gender = R.drawable.wm;
            myAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(),gender),coronic[0],coronic[4],coronic[3]);
        }
        coronicListView.setAdapter(myAdapter);
    }

    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what == HandlerMessageType.FIND_CORONIC_FROM_DATE_SUC.ordinal()){ //날짜로 잘 찾았다면
                /*coronicListView_Apdater.notifyDataSetChanged(); //어댑터 변경 알림
                coronicListView_Apdater.clear(); //어탭터 초기화
                coronicListView_Apdater.addAll(coronicList); //어탭터 목록 수정
                coronicListView.setAdapter(coronicListView_Apdater); //listview에 설정*/
                listDataSetting();
            } else if(msg.what == HandlerMessageType.FIND_CORONIC_FROM_ADDRESS_SUC.ordinal()){ //장소로 잘 찾았다면
                /*coronicListView_Apdater.notifyDataSetChanged(); //어댑터 변경 알림
                coronicListView_Apdater.clear(); //어탭터 초기화
                coronicListView_Apdater.addAll(coronicList); //어탭터 목록 수정
                coronicListView.setAdapter(coronicListView_Apdater); //listview에 설정*/
                listDataSetting();
            } else if(msg.what == HandlerMessageType.SERVER_CONN_FAIL.ordinal()){
                Toast.makeText(CoronicListActivity.this, "서버 연결 실패", Toast.LENGTH_LONG).show();
                finish(); //액티비티 닫기
            }

        }
    };

    private class FindDateConn extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... searchDate) {
            boolean isConnected = false;

            isConnected = MainActivity.server.checkConnection();

            if (isConnected) { //성공시
                String date = searchDate[0]; //찾을 날짜값
                ArrayList<String[]> coronicData = server.requestCoronicDataFromDate(date); //서버에 데이터 요청
                coronicList =  coronicData;
                /*coronicList.clear(); //목록 초기화
                for(String[] coronic : coronicData){
                    String strFormat = ""; // 데이터를 1줄로
                    for(int i = 0; i < coronic.length; i++){
                        strFormat += coronic[i] + " ";
                    }
                    coronicList.add(strFormat); //목록에 추가
                }*/
                Message message = handler.obtainMessage(HandlerMessageType.FIND_CORONIC_FROM_DATE_SUC.ordinal()); //데이터 받기 성공했으니 목록 생성
                handler.sendMessage(message);
            } else { //서버 연결 실패시
                Message msg = handler.obtainMessage(HandlerMessageType.SERVER_CONN_FAIL.ordinal()); //실패했으니 창 닫아
                handler.sendMessage(msg);
            }
            return isConnected;
        }
    }

    private class FindAddressConn extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... searchAddress) {
            boolean isConnected = false;

            isConnected = MainActivity.server.checkConnection();


            if (isConnected) { //성공시
                String address = searchAddress[0]; //찾을 주소값
                ArrayList<String[]> coronicData = server.requestCoronicDataFromAddress(address); //서버에 데이터 요청
                coronicList =  coronicData;
                /*coronicList.clear(); //목록 초기화
                for(String[] coronic : coronicData){
                    String strFormat = ""; // 데이터를 1줄로
                    for(int i = 0; i < coronic.length; i++){
                        strFormat += coronic[i] + " ";
                    }
                    coronicList.add(strFormat); //목록에 추가
                }*/
                Message message = handler.obtainMessage(HandlerMessageType.FIND_CORONIC_FROM_ADDRESS_SUC.ordinal()); //데이터 받기 성공했으니 목록 생성
                handler.sendMessage(message);
            } else { //서버 연결 실패시
                Message msg = handler.obtainMessage(HandlerMessageType.SERVER_CONN_FAIL.ordinal()); //실패했으니 창 닫아
                handler.sendMessage(msg);
            }
            return isConnected;
        }
    }
}

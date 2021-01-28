package com.example.coronasystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FindDateActivity extends AppCompatActivity {
    String date = "";
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> lk = new ArrayList<String>();
    ArrayList<String[]> coronicData = new ArrayList<String[]>();
    CalendarView calendarView;
    TextView tv_date;
    ListView listinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_date);
        Button btn_confirm_date = (Button)findViewById(R.id.btn_confirm);//해당정보로 찾기 버튼
        tv_date = findViewById(R.id.tv_date);//캘린더로부터 받아온 날짜
        calendarView = findViewById(R.id.calendarView);//캘린터
        listinfo = (ListView)findViewById(R.id.list_info);

        Date selectDate = new Date(calendarView.getDate());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormat.format(selectDate); //선택한 날짜값 형식에 맞춰 구성, 초기화
        tv_date.setText(date); //초기 텍스트 표시

        btn_confirm_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //서버연결 후 확인
                date = tv_date.getText().toString();
                Intent intent = new Intent(getApplicationContext(), CoronicListActivity.class); //목록 생성
                intent.putExtra("searchType", "date"); //목록 생성 액티비티에 날짜로 찾을거라고 알림
                intent.putExtra("searchParam", date); //찾을 날짜값
                startActivity(intent);
                finish();

            }
        });

        Button btn_home = (Button)findViewById(R.id.btn_home);//홈버튼
        btn_home.setOnClickListener(new View.OnClickListener() {//이벤트처리, 홈이동

            @Override
            public void onClick(View view) { //홈으로

                finish();
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            private CalendarView view;
            private int year;
            private int month;
            private int dayOfMonth;

            @SuppressLint("DefaultLocale")
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                this.view = view;
                this.year = year;
                this.month = month;
                this.dayOfMonth = dayOfMonth;
                month+=1;
                if(month<10)
                    if (dayOfMonth<10)
                        tv_date.setText(String.format("%d-0%d-0%d",year, month, dayOfMonth));
                    else
                        tv_date.setText(String.format("%d-0%d-%d",year, month, dayOfMonth));
                else
                    if(dayOfMonth<10)
                        tv_date.setText(String.format("%d-%d-0%d",year, month, dayOfMonth));
                    else
                        tv_date.setText(String.format("%d-%d-%d",year, month, dayOfMonth));
                date = (String) tv_date.getText();

            }
        } );

    }

}

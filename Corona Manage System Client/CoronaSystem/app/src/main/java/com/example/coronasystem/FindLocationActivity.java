package com.example.coronasystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class FindLocationActivity extends AppCompatActivity {
    @SuppressLint("ResourceType")
    private Spinner city;
    private ArrayAdapter<String> city_Adapter;
    private Spinner town;
    private ArrayAdapter<String> town_Adapter;
    private Spinner sarea;
    private ArrayAdapter<String> sarea_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.find_location);

        Button btn_home = (Button)findViewById(R.id.btn_home);//홈버튼
        btn_home.setOnClickListener(new View.OnClickListener() {//이벤트처리, 홈이동

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //어댑터 초기화
        city_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        town_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        sarea_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        city = (Spinner)findViewById(R.id.spinner_city); //city 콤보박스
        town = (Spinner)findViewById(R.id.spinner_town); //town 콤보박스
        sarea = (Spinner)findViewById(R.id.spinner_dis); //dis 콤보박스

        city_Adapter.addAll(CoronaDataStorage.getArea_cityList());
        //도시 목록으로 spinner 세팅
        city.setAdapter(city_Adapter);
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//city 아이템을 눌렀을때
                //town목록 갱신해야함
                String cityName = city.getSelectedItem().toString(); //선택한 도시명
                town_Adapter.clear();
                town_Adapter.add(""); //빈칸
                town_Adapter.addAll(CoronaDataStorage.getArea_townList(cityName));
                town.setAdapter(town_Adapter);
            }
            public void onNothingSelected(AdapterView<?> arg0) {//아무것도 안눌렀을때?

            }
        });

        town.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//town 아이템을 눌렀을때
                //dis목록 갱신해야함
                String cityName = city.getSelectedItem().toString(); //선택한 도시명
                String townName = town.getSelectedItem().toString(); //선택한 도시명
                sarea_Adapter.clear();
                sarea_Adapter.add(""); //빈칸
                String[] sareaList = CoronaDataStorage.getArea_sareaList(cityName, townName);
                if(sareaList != null) sarea_Adapter.addAll(sareaList);
                sarea.setAdapter(sarea_Adapter);
            }
            public void onNothingSelected(AdapterView<?> arg0) {//아무것도 안눌렀을때?

            }
        });

        final TextView test=(TextView)findViewById(R.id.test);

        Button btn_confirm=(Button)findViewById(R.id.btn_confirm_location);//확인 버튼
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName =(String) city.getSelectedItem();//city을 저장할 변수, confirm누를 때 변수 사용
                String townName =(String) town.getSelectedItem();//town를 저장할 변수, confirm누를 때 변수 사용
                String disName = (String) sarea.getSelectedItem();;//dis를 저장할 변수, confirm누를 때 변수 사용

                String searchAddress = cityName + " " + townName + " " + disName; //찾을 주소값

                Intent intent = new Intent(getApplicationContext(), CoronicListActivity.class); //목록 생성
                intent.putExtra("searchType", "address"); //목록 생성 액티비티에 주소로 찾을거라고 알림
                intent.putExtra("searchParam", searchAddress); //찾을 주소값
                startActivity(intent);
                finish();
            }
        });
    }
}

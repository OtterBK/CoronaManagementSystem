
<div align=center>
  
![header](https://capsule-render.vercel.app/api?type=soft&color=3C3530&fontColor=F16B6F&height=130&section=header&text=%20Corona　Management　System%20&animation=scaleIn&fontSize=40&fontAlign=50&fontAlignY=50)

</div>

## 코로나 확진자 동선 관리 및 조회 시스템
#### [COVID 19 확진자 동선 파악] 에 중점을 둔 시스템으로 서버와 클라이언트로 구성됩니다.
![image](https://user-images.githubusercontent.com/28488288/106213659-824cc480-6210-11eb-8b68-3352b69762d3.png)


### 자료흐름도 (Data Flow Diagrams)
![image](https://user-images.githubusercontent.com/28488288/106213512-3863de80-6210-11eb-8357-61395710e329.png)

#### 동작 방식
```
DB <-> 서버 <-> 클라이언트
구조로 동작하며 DB는 MySql을 사용하여 테스트하였습니다.
```

#### 서버, 클라이언트는 각각의 기능을 제공합니다.
```
* 서버
  * 서버 접근 제한을 위한 로그인 관련 기능
  * 코로나 환자의 신상 정보 및 동선 정보를 입력하기 위한 기능
  * DB 접속 및 제어 기능
  * 네트워크 사용한 데이터 전송 등의 서버 기능
  

* 클라이언트
  * 서버와의 네트워크 통신 기능
  * 정보 조회 기능
  * 지도를 사용한 정보 표시 기능
```

#### 사용 API
```
* 카카오 지도 API - 클라이언트, 지도 표시
* Jcalendar - 서버, 날짜 선택 기능
* JTimeChooser - 서버, 시간 선택 기능
* JDBC - 서버, DB 접속, 제어 기능
* Naver 주소 검색 - 서버, 동선 정보 입력시 행선지 선택 기능
* LipeRMI - 서버, 클라이언트간 네트워크 통신
```

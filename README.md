#  **FIFTYFIFTY FAN PAGE (Vue3.0 + REST API 프로젝트)**

<h3>K-POP 그룹 FIFTYFIFTY의 팬 메이드 페이지입니다.</h3>

![localhost_8081_ (1)](https://github.com/user-attachments/assets/14c9485e-d60f-48f0-bcea-bbdfc4fc0b9b)

# 📚 **프로젝트 목차**

### 1. 프로젝트 소개
### 2. 프로젝트 ERD
### 3. 학습포인트와 보완점

# :gear: 1. **프로젝트 소개**

### :bulb: 프로젝트 인원                 
- 1명                             

### :bulb: 프로젝트 기간
- 2025-05-14 ~ 진행중

### :bulb: 주요기능

- **테마 전환**
- ![2025-06-05_18-21-45](https://github.com/user-attachments/assets/2fd657ba-4973-4c51-837c-bfaf5a9a0fd3)
  <p>
    로고, 배경 아이콘을 모두 테마별 리소스로 분리
  </p>
  
- **카테고리 별 일정 캘린더**
- ![2025-06-05_18-21-45_2](https://github.com/user-attachments/assets/9e1d4958-d4b6-45a6-9156-4d9a4c39e0ec)
- ![bandicam 2025-05-31 01-12-50-787](https://github.com/user-attachments/assets/ac35d3c0-71d4-4038-8398-f81d8a4260d4)

  <p>
음방, 컴백 등 crt 필드를 가진 이벤트 컬렉션 FullCalendar.js 사용 및 카테고리 별 색상 표시

일정 데이터를 조회하기 전, 백엔드에서 crt 필드를 기준으로 각 일정의 카테고리를 구분하고, 프론트에는 색상 정보와 함께 전달되도록 하여 사용자가 일정을 직관적으로 구분할 수 있도록 했습니다.
  </p>
  
- **유튜브 TOP 10 자동 업데이트**
- ![2025-06-05_18-21-45_4](https://github.com/user-attachments/assets/066c9aa2-352e-4d72-9b55-dbe9803fad7c)

  <p>
    @Scheduled 을 사용해 매일 오전 9시마다 피프티로 영상 검색 후 
    필터 처리 후 DB에 탑 10 영상 썸네일, 주소, view, 제목 저장 및 조회
  </p>
![bandicam 2025-05-31 01-24-48-185](https://github.com/user-attachments/assets/e5666ea9-da88-41b4-bcf9-7bceb1e4cd6b)
![bandicam 2025-05-31 01-24-56-994](https://github.com/user-attachments/assets/cbab4beb-0e5a-4b76-90a5-c5b143749cc2)


<p>Spring에서 제공하는 @Scheduled 기능을 활용하여 매일 오전 9시 마다 Youtube API를 활용해 조회수 탑 10개 영상을 가져오도록 설계했으며 필터처리를 통해 특정 날짜 이후 특정 단어를 제외한 검색기록만 가져오도록 설계하여 잘못된 정보가 들어오지 않도록 구현하였습니다.</p>

- **DB 데이터 자동 삭제**
- ![bandicam 2025-06-10 16-41-38-923](https://github.com/user-attachments/assets/c3c66b36-d21d-4827-b1ba-4e5d72315060)
![bandicam 2025-06-10 16-41-51-029](https://github.com/user-attachments/assets/4e437ecd-2718-4e02-9462-1841d31e8d38)
  <p>
    DB에 이미지를 체크하여 DB에는 있지만 경로에 이미지가 없는 경우 DB를 자동으로 삭제하도록 구현하여 불필요한 용량이 차지하지 않도록 설계하였습니다.
  </p>

  - **이미지 갤러리**
  - ![2025-06-05_18-21-45_5](https://github.com/user-attachments/assets/0dbe44f9-e9f5-457f-acad-2951ebff93c2)
![2025-06-05_18-21-45_6](https://github.com/user-attachments/assets/b8af6da0-5c44-4e52-b681-b595805802b2)

  <p>
    backend 에서 페이징 처리를 진행하여 접속시 과도한 리소스 방지
  </p>
  
- **어드민 페이지**
- ![bandicam 2025-05-30 19-11-03-897](https://github.com/user-attachments/assets/fab41c6c-3870-421e-be31-4bc9b10ef3a7)

  <p>
    Spring Security와 JWT 토큰을 활용한 로그인 기능 구현해 ADMIN 권한이 있는 유저만 접속 가능
  </p>


** Swagger api**
![image](https://github.com/user-attachments/assets/c7cee12f-8471-47f5-85dd-326728e93869)


서버 문제 발생시 비즈니스로직 확인을 위해 swagger api를 활용해 테스트하며 문제점을 찾아 진행하였습니다.
  

### :bulb: 사용 기술
<p>사용 언어 : HTML5, CSS3, Java, SQL, TypeScript(5.0v) </p>

<p>프론트엔드 : Vue.js 3.0, Axios(1.9v), fullCalendar.js(6.1.17v), SweetAlert(11.22v)</p>

<p>백엔드 : Spring Boot(3.5.0v), Spring Security, MyBatis(3.0.4v), Lombok, JWT(0.12.6v), Springdoc(2.7v), Cron</p>

<p>서버 런타임 환경 : Node.js(npm 10.9.2v)</p>

<p>개발도구 : isual Studio Code, MySQL Workbench, Spring Boot Devtools</p>

<p>DB : MySQL</p>

<p>협업툴 : GitHub, ERDCloud</p>



### :bulb: ⚙ 프로젝트 설계 ERD
![bandicam 2025-06-05 15-43-24-836](https://github.com/user-attachments/assets/83e51002-ad9b-443d-b3f2-e6722d2646dc)



### :bulb: ⚙ 프로젝트 보완 및 아쉬운점

- 추후 WebSoket을 활용해 캘린더 일정이 다가올 경우 실시간으로 페이지 알람 요청 예정
- 하루 접속자 today 를 통해 트래픽 계산 필요(오픈 이후)
- Youtube 탑 10 영상 조회시 Redis 캐시를 활용한 속도 향상 작업 필요
- **Papago API를 활용한 다국어 번역 기능 추가 예정**

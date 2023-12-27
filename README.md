# DNoljaServer

## 개요 
<img width="581" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/58e4e370-c01f-4751-9591-72ffd05b4cd5">
- 반려견과 함께 갈 수 있는 여행지를 기반으로 여행계획과 후기를 공유할 수 있는 사이트입니다. 


## System Architecture 
<img width="1210" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/1ad0a3f1-ee19-473e-b9ca-854a4f795f21">


- gitAction 및 엘라스틱 빈스톡을 활용해 CI/CD 환경을 구축하였습니다.



## 기능 소개 
### 1. 회원가입 및 로그인
<img width="1005" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/333acc61-1fe0-478a-b46a-697184c9fb4f">

<img width="521" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/03793cbc-461c-4ffc-8c41-9195e33e41ca">

- jwt활용 로그인 로그아웃 구현
- 회원 가입시 메일 인증 로직 구현

### 2. 시설 조회 및 여행 계획 생성 
<img width="1055" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/b53435da-5a0e-4416-a561-da5f6cbbd594">
- Harvarsine공식 활용 현재 지도 내 최단 거리 조회 기능
- 검색어 및 카테고리 혼합 검색 기능
- 드래그 기반으로 원하는 목적지를 원하는 일차에 설정 가능합니다. 

### 3. 여행 계획 기반 후기 생성 가능 
<img width="1111" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/fd0fe57a-82ce-4b6d-9ffd-64f1e7e50ebb">


### 4. 메인 페이지 내 뉴스 크롤링 기능 
<img width="1216" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/bd5bb4a9-4295-488e-8141-dbae43a2ff24">



## 구현 시 고민한 부분 
### 1. 메일 인증시 멀티 스레딩을 활용해 응답 시간 단축 

#### a. 전체 메일 로직 
<img width="1131" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/dcf5df68-49fa-47a2-8dda-27789e364303">


메일 전송까지 하나의 스레드에서 처리하므로 전체시간 평균 6.47초를 소요하여 성능저하가 있었습니다. 

#### b. 스레딩 활용 성능 개선 
<img width="421" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/22ac2208-07a1-432d-9ab7-80b4055ad1c8">

- 스레딩을 활용하여 메일 발송부를 다른 스레드에게 위임하도록 코드를 수정하였습니다.
- 평균 500ms 대로 성능을 개선하였습니다.

### 2. 현재 위치 기준 최단거리 순 시설물 조회 구현 
<img width="424" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/7b2b6c7b-4897-491b-80c7-28025b0dbf6a">

- 대부분의 관련 외부 api는 하루 사용량제한이 있어서 실 서비스에서 사용하기 어렵습니다.

  
- 때문에 Harversine 공식 활용하여 로우 레벨에서 최단거리 조회 기능을 구현하였습니다.

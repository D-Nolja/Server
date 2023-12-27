# DNoljaServer

## System Architecture 
<img width="474" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/dea8101c-729c-4229-bf0b-7e0f7d957c86">

- gitAction 및 엘라스틱 빈스톡을 활용해 CI/CD 환경을 구축하였습니다.



## 기능 소개 
### 1. 회원가입 및 로그인
<img width="452" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/e15fd9c3-e2f6-4d09-8b75-bc510b284fcf">
<img width="521" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/03793cbc-461c-4ffc-8c41-9195e33e41ca">

- jwt활용 로그인 로그아웃 구현
- 회원 가입시 메일 인증 로직 구현

### 2. 시설 조회 및 여행 계획 생성 
<img width="448" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/8e66bd52-6e53-4217-8934-b6aa7c72caa7">

- Harvarsine공식 활용 현재 지도 내 최단 거리 조회 기능
- 검색어 및 카테고리 혼합 검색 기능
- 드래그 기반으로 원하는 목적지를 원하는 일차에 설정 가능합니다. 

### 3. 여행 계획 기반 후기 생성 가능 
<img width="455" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/1bb5d68d-cbb9-42be-8e73-79db5fa801a8">

### 4. 메인 페이지 내 뉴스 크롤링 기능 
<img width="456" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/f087877a-4cb4-45bb-8eb8-8783147a6568">


## 구현 시 고민한 부분 
### 1. 메일 인증시 멀티 스레딩을 활용해 응답 시간 단축 

#### a. 전체 메일 로직 
<img width="417" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/62093d01-fd68-48a9-9131-5e61597ddcd1">

메일 전송까지 하나의 스레드에서 처리하므로 전체시간 평균 6.47초를 소요하여 성능저하가 있었습니다. 

#### b. 스레딩 활용 성능 개선 
<img width="421" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/22ac2208-07a1-432d-9ab7-80b4055ad1c8">

- 스레딩을 활용하여 메일 발송부를 다른 스레드에게 위임하도록 코드를 수정하였습니다.
- 평균 500ms 대로 성능을 개선하였습니다.

### 2. 현재 위치 기준 최단거리 순 시설물 조회 구현 
<img width="424" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/7b2b6c7b-4897-491b-80c7-28025b0dbf6a">

- 대부분의 관련 외부 api는 하루 사용량제한이 있어서 실 서비스에서 사용하기 어렵습니다.

  
- 때문에 Harversine 공식 활용하여 로우 레벨에서 최단거리 조회 기능을 구현하였습니다.

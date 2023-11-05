# DNoljaServer


## 1. 엘라스틱 빈스톡 및 Github Action 활용 무중단 ci / cd 구축 
![image](https://github.com/D-Nolja/Server/assets/119592507/dcd3ba1b-0751-436b-8483-4100078c30f7)

### 1-1. 배포 스크립트 
![image](https://github.com/D-Nolja/Server/assets/119592507/59e1fb29-5bc5-44a8-bc2d-982edd0ce5a3)


### 1-2. 결과
![image](https://github.com/D-Nolja/Server/assets/119592507/4482f2f0-070b-4ac2-9928-109660c28f23)
<img width="906" alt="image" src="https://github.com/D-Nolja/Server/assets/119592507/40d5f827-5ead-4e55-806d-7d473c162a20">


## 2. 현재 좌표 기준 최단거리 시설 조회
실제 url : D-nolja-prod.eba-pk7qjxkg.ap-northeast-2.elasticbeanstalk.com/recommendation/spot?x=126.53&y=33.49&limit=10&maxCount=10
![image](https://github.com/D-Nolja/Server/assets/119592507/77357fbb-6c61-4b5b-b169-2b052021cd1b)

### 2-1. 관련 소스 코드 및 활용 알고리즘 
- 하버 사인 공식 활용 두 지점 사이의 거리 측정
  ![image](https://github.com/D-Nolja/Server/assets/119592507/d9a0fb57-d1fc-4942-a339-0ce3fda72fef)
  ![image](https://github.com/D-Nolja/Server/assets/119592507/00e52e48-b3e4-47cb-8473-9cfa5a30aee3)

- 측정 거리 바탕으로 정렬 알고리즘 활용
  ![image](https://github.com/D-Nolja/Server/assets/119592507/6b2146e0-9d57-4288-95ff-12a0b61d9ee3)

## 3. 회원가입 (+유저 조회, 유저 상태 업데이트 )

### 3-1. AOP 및 Validation 어노테이션 활용 put, post 메서드 실행시 자동 dto 유효성 검사 
![image](https://github.com/D-Nolja/Server/assets/119592507/5bc7d213-3ccf-4aac-9636-1174bfc85636)
![image](https://github.com/D-Nolja/Server/assets/119592507/c37bf413-38d3-4da9-88d5-4c426a2dc8ea)

- 유저명 작성이 정확한 양식 및 null 체크 지켰는지 정규 표현식을 통해 확인 
- 이메일에서는 null 체크 및 정확한 이메일 양식을 지켰는지 정규 표현식을 통해 확인
- 이후 공통 응답 객체를 통해 어디서 오류가 발생했는지 명확하게 표시함

### goolge mail api 활용 인증 메일 (유저 조회 및 업데이트 로직이 여기서 사용됩니다.) 
![image](https://github.com/D-Nolja/Server/assets/119592507/749702f1-212d-45cd-8670-a14fa2af3081)
![image](https://github.com/D-Nolja/Server/assets/119592507/17b3200f-b575-4691-996d-b5bc06859546)

1. 회원가입시 uuid를 생성하여 EmailValidation 테이블의 유저 이메일과 함께 저장
2. 동시에 ApplicationEventPublisher로 등록 이벤트를 발생시킴 
3. 등록 이벤트 발생과 동시에 EventListener에서 uuid를 생성한 후 uuid를 활용해 valid check url을 생성하여 유저에게 이메일 발송
4. 유저가 클릭시 url 내 uuid를 활용해 EmailValidation 테이블 내 칼럼을 찾은 후 email을 바탕으로 유저를 찾아 유저 도메인 내 verified 업데이트 됨 

소스코드 1. EmailVerificaionService 
![image](https://github.com/D-Nolja/Server/assets/119592507/fa211c00-7933-4b30-ae4b-a8b2698af48e)

소스코드 2. EmailVeirifcatio Controller 
![image](https://github.com/D-Nolja/Server/assets/119592507/d7ee2292-f42d-4d0e-b364-45edbc2c81e8)

소스코드 3. EmailVerification Listener
![image](https://github.com/D-Nolja/Server/assets/119592507/0a183e03-0d43-4224-9cfa-0f8a23a1ba7a)

## 4. jwt 기반 로그인

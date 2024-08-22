<div align="center">
  <img src="https://github.com/user-attachments/assets/45a607a7-0800-47f7-8860-ed5b91b49b69">
</div>

------

<div align="center">
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white">
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
  <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
  <img src="https://img.shields.io/badge/postgresql-4169E1?style=for-the-badge&logo=postgresql&logoColor=white">
  <img src="https://img.shields.io/badge/apachetomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black"><br>
  <img src="https://img.shields.io/badge/jpa-181717?style=for-the-badge">
  <img src="https://img.shields.io/badge/jsonwebtokens-181717?style=for-the-badge&logo=jsonwebtokens&logoColor=white">
  <img src="https://img.shields.io/badge/intellijidea-181717?style=for-the-badge&logo=intellijidea&logoColor=white">
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
</div>

----
### **🙌 프로젝트 명**

- GYUWANGSA
- 도움 받은 개발자 지인들의 이니셜과 자신의 이니셜을 통해 무신사와 비슷하게 프로젝트 명 설정.
----
### **🙋‍♀️ 프로젝트 소개**

- 무신사와 크림을 참고하여 제작한 전자상거래 사이트.(Back-End)
- 2018년도 단품 쇼핑몰 제작 이후 실력 점검 및 프로젝트 확장, 보편적인 언어 경험을 위해 제작.
- Back-End 와 [Front-End](https://github.com/jangkuok/Gyuwangsa_Front-End.git) 분리.
----
### **🗓 개발 기간**

- 24.05.01 ~ 24.07.22
----
### **👨‍👨‍👦‍👦 맴버 구성**

- 김장규
----

### **🛠 프로젝트 확장**

- 한명의 관리자가 단품 상품을 팔고 관리하는 것이 아닌 브랜드가 입점하여 여러가지 상품을 판매하고 관리하는 방식으로 확장.
- session 방식으로 사용자 정보와 권한을 관리하는 것이 아닌 jwt을 통해 토큰을 발급하고 cookie에 저장하여 사용자 정보와 권한을 확인하고 발급 시간이 지나면 재발급을 통해 사용자 정보 관리.
- 사용자의 편리한 회원가입을 위해 카카오 oAuth를 사용.
- 아임포트를 통해 결제와 취소 기능을 추가.
- 확장성과 보안성, 유지보수성을 높이기 위해 코드를 따로 나누어 관리 했으며 Rest API를 사용하여 서버와 클라이언트 통신.
- 개발 툴은 Intellij 사용.(전 프로젝트에서는 Eclipse 사용.)
----

### **✏ 테스트 방법**

- SpringBootTest를 사용하여 Repository와 Service의 sql 테스트와 데이터 관련 테스트를 했으며 Log4j2를 사용하여 오류 유무, 결과값 확인.
- PostMan 사용으로 Controller의 주소 확인 데이터 이동, JWT 테스트.
----

### **🗂 패키지 구성**

- domain
- dto
- repository
- service
   - lmpl
- controller
   - advice : 사용자 예외 처리 설정
   - config : csrf 설정 / session 미사용 설정 / 로그인 성공/실패 설정 / JWT 체크 설정 / 사용자 비밀번호 암호화 설정 / CORS 설정
   - formatter : LocalDateTime 사용자 설정
- security
   - filter : JWT 체크 설정
   - hander : 로그인 성공/실패 설정 / JWT 에러 설정
   - user 권한 설정
- paging : 페이징 처리 설정
- util : 파일 업로드 설정 / JWT Exception 추가 / JWT 제작 설정

----

### **📌 상세 기능**
- 공통 기능
   - 메인/브랜드/상품/목록 페이지 확인
   - 상품 / 브랜드 검색
   - 브랜드/회원 가입(카카오 가입)
   - 아이디 찾기

- 일반 사용자
   - 로그인 / 로그아웃 
   - 마이페이지
   - 회원 정보 수정 / 탈퇴
   - 주문 하기 / 주문 취소
   - 카트 담기
   - 상품 좋아요 / 좋아요 목록 확인

- 브랜드 관리자
   - 로그인 / 로그아웃
   - 브랜드 정보 수정
   - 브랜드 사용자 정보 수정 / 탈퇴
   - 상품 등록 / 수정 / 삭제
   - 주문 상태 확인 및 수정
  
----

### **❓ 참고 자료**

- 인터넷 자료 / 유튜브 / 인프런 강의를 통해 JAVA/SPRING 복기
- 개발자 지인들을 통해 전체적인 프로젝트 조언, 언어 추천, 에러 관련 도움.
- 막히는 에러와 알고리즘은 서치를 통해 해결.
----
### **➕ 추가 사항(보안점)**

- 상품 리스트에 관한 다양한 선택 검색 제공.
- 공지사항, QnA, 리뷰 관련 커뮤니티 게시판 추가.
- 택배 API를 사용하여 택배 관련 기능 추가.
- 네이버 로그인 기능 추가.
- 최고 관리자의 기능 추가.
- 브랜드 별 광고 기능 추가.
- 크롤링을 통해 데이터 수집.
- AWS를 통해 서버 구축.
----

### **🏞 상세 이미지**
|메인 페이지|브랜드 페이지|상품 페이지|
|-----------|-----------|-----------|
|![전체화면](https://github.com/user-attachments/assets/a2d34b7f-41c5-4363-a0ae-6e133f70c0bf)|![브랜드](https://github.com/user-attachments/assets/2cff97cb-127d-4f67-bd34-6b80bd5a8a59)|![상품](https://github.com/user-attachments/assets/c3137ac9-07b9-444b-9c2b-64778a9dbafa)|



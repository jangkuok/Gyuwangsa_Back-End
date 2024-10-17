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
- [2018년도 단품 쇼핑몰](https://github.com/jangkuok/outer_shopping_project.git)([동영상](https://drive.google.com/drive/folders/1HrA6EeozBnJtGje6JHk7giiH3asLYbvx?usp=drive_link)) 제작 이후 실력 점검 및 프로젝트 확장, 보편적인 언어 경험을 위해 제작.
- Back-End 와 [Front-End](https://github.com/jangkuok/Gyuwangsa_Front-End.git) 분리.
- [기능 관련 영상 ](https://drive.google.com/drive/folders/16WGca8_N_nMLHFN5m-tMiSX9GuglpTeP?usp=drive_link)
- [프로젝트 개발 전](https://github.com/jangkuok/Gyuwangsa.git) Spring으로 로그인 및 로그아웃, JWT 토큰 발급까지 연습 후 진행.
----
### **🗓 개발 기간**

- 24.05.01 ~ 24.07.31
----
### **👨‍👨‍👦‍👦 맴버 구성**

- 김장규
----

### **🛠 프로젝트 확장**

- 한명의 관리자가 단품 상품을 팔고 관리하는 것이 아닌 브랜드가 입점하여 여러가지 상품을 판매하고 관리하는 방식으로 확장.
- session 방식으로 사용자 정보와 권한을 관리하는 것이 아닌 jwt을 통해 토큰을 발급하고 cookie에 저장하여 사용자 정보와 권한을 확인하고 발급 시간이 지나면 재발급을 통해 사용자 정보 관리.
- 사용자의 편리한 회원가입을 위해 카카오 oAuth를 사용.
- 아임포트를 통해 결제와 취소 기능을 추가.
- 확장성과 보안성, 유지보수성을 높이기 위해 코드를 따로 나누어 관리 했으며 RESTful API를 사용하여 서버와 클라이언트 통신.
- 개발 툴은 Intellij 사용.(전 프로젝트에서는 Eclipse 사용.)
----

### **🗳️ 테스트 방법**

- SpringBootTest를 사용하여 Repository와 Service의 sql 테스트와 데이터 관련 테스트를 했으며 Log4j2를 사용하여 오류 유무, 결과값 확인.
- PostMan 사용으로 Controller의 주소 확인 데이터 이동, JWT 테스트.
----

### **✏ 기술 스택 사용 이유**
[ Back-End ]
- Java : 주 사용 언어로 사용.
- Spring Boot : 사용 경험 및 간편한 설정과 빠른 개발 환경을 제공하여 선택했으며 Spring Security를 활용해 사용자 인증 및 권한 부여 기능을 구현.
- JPA: 객체와 데이터베이스 간의 매핑을 지원하여 코드 기반의 데이터 관리가 용이하다고 판단했으며 인터넷 서치를 통해
대중적으로 MyBatis와 JPA가 점유율이 높았고 JPA의 개발 경험을 쌓기 위해 선택.
- Gradle: 의존성 관리 및 빌드 속도 면에서 우수한 성능을 제공하여 대규모 프로젝트에 적합하다고 판단했으며 대중적으로 Maven과 Gradle의 점유율이 높았고 Gradle의 개발 경험을 쌓기 위해 선택.

[ DataBase ]
- PostgreSQL : 오픈 소스 DBMS라는 큰 장점과 대량의 데이터를 효율적으로 처리할 수 있어 전자상거래 플랫폼의 데이터 처리에 적합하다고 생각하여 선택.

[ Tool ]
- IntelliJ IDEA : 백엔드 코드 작성 및 관리.
- Github : 버전 관리 및 협업을 위해 사용.
- PostMan : RESTful API 테스트에 사용.

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
### **❗ 느낀점**

- Spring Boot의 편리함과 설정의 중요성
   - Spring에 비해 Spring Boot는 간편한 설정과 빠른 개발 환경을 제공해주어 개발 생산성을 크게 향상 했으나 프로젝트를 진행하면서 Spring의 다양한 설정 옵션에 대해 더 깊이 이해하고 이를 적절히 활용하는 것이 중요하다고 생각 했으며 앞으로는 Spring의 설정에 대해 더 많이 학습하고 연습해야겠다고 느낌.
- JPA와 MyBatis의 차이점 이해
   - JPA는 별도의 SQL 작성 없이도 객체와 데이터베이스 간의 매핑을 지원하여 편리함을 제공했으나 복잡한 SQL 쿼리 작업에서는 MyBatis가 더 적합하다는 것을 느꼈고 이 과정에서 JPA와 MyBatis의 차이점에 대해 깊이 공부할 수 있었으며 상황에 맞는 선택의 중요성을 깨달음.
- N+1 문제와 Fetch Join 사용
   - JPA 사용 시 발생할 수 있는 N+1 문제에 대해 학습하게 되었고 이를 해결하기 위해 Fetch Join을 사용하여 문제를 해결했으며 이를 통해 JPA의 한계와 기능에 관한 학습 할 수 있는 경험을 가짐.
- 단위 테스트의 중요성
   - 개발 과정에서 단위 테스트를 통해 기능별로 검증을 수행하면서 단위 테스트가 코드의 안정성과 품질을 유지하는 데 얼마나 중요한지 깨달았으며 이를 통해 테스트 코드를 작성하는 습관과 중요성을 인식하게 됨.
   - 
----

### **🏞 상세 이미지**
|메인 페이지|브랜드 페이지|상품 페이지|
|-----------|-----------|-----------|
|![전체화면](https://github.com/user-attachments/assets/a2d34b7f-41c5-4363-a0ae-6e133f70c0bf)|![브랜드](https://github.com/user-attachments/assets/2cff97cb-127d-4f67-bd34-6b80bd5a8a59)|![상품](https://github.com/user-attachments/assets/c3137ac9-07b9-444b-9c2b-64778a9dbafa)|



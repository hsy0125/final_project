# 서버 회원 관리 시스템

---

## 개요

서버 회원 관리 시스템은 **회원**, **게임 서버**, **게임 회사** 간의 관계를 효율적으로 관리하는 데이터베이스입니다.  
회원 정보 관리, 게임 서버 관리, 게임 회사 정보 관리, **회원과 서버 간의 접속 정보 저장**, **가입일자에 따른 등급 계산**,  
각 릴레이션의 수정, 삽입, 삭제 관리 기능을 제공합니다.

---
## 구성원 역할
### 이현경
   - sql 코드를 자바코드로 변환 후 릴레이션의 삽입, 수정, 삭제기능 구현
   - 서버 접속자 수 제한 기능 구현
   - 발표


### 허소영
   - 전체적인 README파일 작성
   - sql 코드 완성
   - 등급 체제 구현
---

## 데이터베이스 요구사항 명세서

### 게임 회원 관리 시스템

1. **회원**
   - 회원 아이디, 비밀번호, 이름, 나이, 이메일, 전화번호를 저장합니다.
   - 회원은 `회원 아이디`로 식별합니다.

2. **게임 서버**
   - 서버명과 접속 회원 수를 저장합니다.
   - 서버는 `서버명`으로 식별합니다.

3. **게임 회사**
   - 위치, 대표자명, 전화번호, 회사명을 저장합니다.
   - 게임 회사는 `회사명`으로 식별합니다.

4. **회원과 서버 관계**
   - 회원은 여러 서버에 접속할 수 있고, 하나의 게임 서버에 여러 회원이 접속할 수 있습니다.
   - 회원이 서버에 접속할 때 다음 정보를 저장합니다:
     - 닉네임, 가입 날짜, 등급, 게임 머니
   - 등급은 가입 날짜에 따라 자동 계산됩니다.  
     예: 
     - 가입 1년 미만: **브론즈회원**
     - 가입 1년 이상: **실버회원**

5. **게임 회사와 서버 관계**
   - 각 게임 서버는 한 게임 회사가 관리합니다.
   - 게임 회사는 여러 게임 서버를 관리할 수 있습니다.
   - 관리 정보에는 **점검 일자**와 **서버 유지 비용**이 포함됩니다.

---

## 데이터베이스 ERD

<img src="https://github.com/user-attachments/assets/efd0d412-b963-4b3f-99f6-65aca3f1f76b" alt="ERD Diagram" width="500" />

---

## 테이블 명세서

<img src="https://private-user-images.githubusercontent.com/131340738/397928434-c8247040-9c4e-4cbf-bc0b-0038627a9bef.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MzQ3ODYyNzksIm5iZiI6MTczNDc4NTk3OSwicGF0aCI6Ii8xMzEzNDA3MzgvMzk3OTI4NDM0LWM4MjQ3MDQwLTljNGUtNGNiZi1iYzBiLTAwMzg2MjdhOWJlZi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMjIxJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTIyMVQxMjU5MzlaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT04MjkyNmViMTUzNWNjYTljNjFkNjQyMWMyYTkwZjFhNTg3YjU4Mjc4OWFlZTIyYTk5MWNiNDU4MGUwNmQ0MmRkJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.V36IwZcRru7p8L4Kl_spsR8k1syfdMgOMIrz6JOeFyU" alt="Table1" width="600" />
<img src="https://private-user-images.githubusercontent.com/131340738/397928499-f6f16537-f53c-4e8a-992d-4f3f50c494db.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MzQ3ODY1MjYsIm5iZiI6MTczNDc4NjIyNiwicGF0aCI6Ii8xMzEzNDA3MzgvMzk3OTI4NDk5LWY2ZjE2NTM3LWY1M2MtNGU4YS05OTJkLTRmM2Y1MGM0OTRkYi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMjIxJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTIyMVQxMzAzNDZaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT05MDM4YzBiYTRkMThiMTIwMWFkMGY4NTkxN2NlNTM4ZjUyOWZiNWYwMGEzMmU1ZGIzZDk0YWJjNTY5ZGYwNDJhJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.CbX-1R3cCOvQCZW4vNKAJD9M8HIsHrmPSHBGYHR47Wo" alt="Table2" width="600" />

---
## 등급 체계

### 등급은 **현재 날짜 기준**으로 가입한 날짜를 자동으로 계산하며 calculateGrade 메서드를 통해 브론즈, 실버, 골드, 플래티넘 등급을 부여:
1. 가입 **1년 미만**: **브론즈** 등급
2. 가입 **1년 이상 ~ 5년 미만**: **실버** 등급
3. 가입 **5년 이상 ~ 10면 미만**: **골드** 등급
4. 가입 **10년 이상**: **플래티넘** 등급

---

### **서버접속자 수 제한**
- 서버에 접속할 수 있는 회원수를 제한합니다.(110명)
- 서버에 새로운 회원을 추가할 때 접속이 가능한지 여부를 (110명과 비교) 판별합니다.
- 110명이 넘지않으면 추가되고, 110명이 넘으면 추가 불가능이라는 안내가 뜨며 추가를 실패합니다.
- 서버에 새로운 회원이 추가될 때 getConnCount()를 호출하여 접속 회원 수를 확인하고 제한을 초과하면 접속 불가 메시지를 출력합니다.

---

## 모든 릴레이션에 관한 삽입, 수정, 삭제 기능 (회원정보, 게임회사정보, 접속정보)
- 각각 Main클래스에서 정보를 삽입, 수정, 삭제할 수 있도록 구현
- Service 클래스에서 각 데이터베이스의 테이블과 연동해 Create, Read, Update, Delete 작업을 수행


### **삽입 기능**
- 각 개체에 대한 속성을 차례대로 삽입해 추가할 수 있습니다.
- 각각 속성의 순서에 맞춰 사용자에게 입력받아 삽입합니다.
- 삽입완료된 전체 회원 내역을 출력합니다.

### **수정 기능**
- 각 개체의 정보들을 수정할 수 있습니다.
- 수정하기 전 수정 여부를 물어보며 진행합니다.
- 수정을 한다고 했을 시 수정할 속성을 사용자로 부터 입력받아서 수정을 진행합니다.

### **삭제 기능**
- 각 개체의 정보를 삭제합니다.
- 삭제 전 삭제 여부를 물어보며 진행합니다.
- 삭제를 한다고 했을 시 삭제할 속성을 사용자로 부터 입력받아서 삭제를 진행합니다. 
- 삭제완료 시 삭제를 완료했다는 메세지를 출력합니다.


#### 삽입, 수정, 삭제 순으로 진행합니다.
#### MemberMain, CompanyMain, AccessMain 에서 동작을 수행합니다.

---

## 기대 효과
1. **효율적인 회원 관리**

2. **게임 서버 운영 최적화**
   
서버 접속 회원 수를 제한하는 기능을 통해 서버 과부하를 방지하고, 안정적인 게임 환경을 유지합니다.

각 서버의 접속자 수와 게임 회사와의 관계를 명확히 관리하여 서버 유지보수 및 비용 관리를 체계화할 수 있습니다.

3. **게임 회사 간의 관계 관리 강화**
   
게임 회사와 서버 간의 관계를 구조화하여, 회사별 서버 점검일자와 유지 비용 관리를 효율적으로 수행할 수 있습니다.

여러 게임 서버를 운영하는 회사의 정보를 통합 관리하여, 운영 효율성을 극대화할 수 있습니다.

4. **자동화된 등급 시스템**
   
회원의 가입 날짜를 기준으로 등급을 자동으로 계산함으로써, 관리자의 수작업 부담을 줄이고 정확도를 향상시킵니다.

5. **데이터 무결성 및 안정성 확보**
   
데이터베이스 설계를 기반으로 모든 릴레이션의 수정, 삽입, 삭제가 명확히 정의되어 있어 데이터의 무결성을 유지할 수 있습니다.

관계형 데이터베이스를 사용함으로써 데이터 중복을 최소화하고, 검색 및 관리 속도를 향상시킵니다.


  


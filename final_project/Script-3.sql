--게임 회사 릴레이션
CREATE TABLE company
(
    company_name  VARCHAR(20) NOT NULL,
    location VARCHAR(20),
    ceo_name VARCHAR(20),
    phoneNumber VARCHAR(20),
    PRIMARY KEY(company_name)
);

INSERT INTO company(company_name,location,ceo_name,phoneNumber)
VALUES ('회사1','서울','김병철','010-0001-0001');
INSERT INTO company(company_name,location,ceo_name,phoneNumber)
VALUES ('회사2','부산','김점프','010-0002-0002');
INSERT INTO company(company_name,location,ceo_name,phoneNumber)
VALUES ('회사3','대전','박현경','010-0003-0003');
INSERT INTO company(company_name,location,ceo_name,phoneNumber)
VALUES ('회사4','청주','허여름','010-0004-0004');

SELECT *
FROM company;


-- 게임 릴레이션
CREATE TABLE game
(
    server_id VARCHAR(20) NOT NULL,
    conn_count   INTEGER NOT NULL,
    company_name  VARCHAR(20) NOT NULL,
    
    inspection_date DATE,
    maintenance_cost INTEGER,
    PRIMARY KEY(server_id),
    FOREIGN KEY (company_name) REFERENCES company (company_name)
);

INSERT INTO game (server_id, conn_count,company_name,inspection_date,maintenance_cost)
VALUES ('서버1', 101, '회사1', '2024-01-01' , 100001);
INSERT INTO game (server_id, conn_count,company_name,inspection_date,maintenance_cost)
VALUES ('서버2', 102, '회사2', '2024-01-02' , 100002);
INSERT INTO game (server_id, conn_count,company_name,inspection_date,maintenance_cost)
VALUES ('서버3', 103, '회사1', '2024-01-03' , 100003);
INSERT INTO game (server_id, conn_count,company_name,inspection_date,maintenance_cost)
VALUES ('서버4', 104, '회사4', '2024-01-04' , 100004);

SELECT *
FROM game;

-- 회원 릴레이션
CREATE TABLE member
(
    member_id    VARCHAR(20)       NOT NULL,
    password     VARCHAR(50) NOT NULL,
    name          VARCHAR(10) NOT NULL,
    age        INTEGER,
    email      VARCHAR(100),       
    phonenum    VARCHAR(20),            
    PRIMARY KEY (member_id)
);

INSERT INTO member(member_id, password, name, age, email, phonenum)
VALUES ('001', 'sdaofd', '이현경', 22, 'lhk0864@naver.com', '010-8547-1224');
INSERT INTO member(member_id, password, name, age, email, phonenum)
VALUES ('002', 'sdadtyrtd', '허소영', 22, 'thdud@naver.com', '010-3345-7890');
INSERT INTO member(member_id, password, name, age, email, phonenum)
VALUES ('003', 'ojmijkl', '홍길동', 30, 'rlfehd@naver.com', '010-1234-5678');
INSERT INTO member(member_id, password, name, age, email, phonenum)
VALUES ('004', 'sdaofd', '아무개', 27, 'dkanro@naver.com', '010-4326-9876');
INSERT INTO member(member_id, password, name, age, email, phonenum)
VALUES ('005', 'werty', '강아지', 20, 'dog@naver.com', '010-8899-0055');

SELECT *
FROM member;

-- 접속 릴레이션
CREATE TABLE access (
    member_id VARCHAR(20) NOT NULL,
    server_id VARCHAR(20) NOT NULL,
    nickname VARCHAR(20) NOT NULL,
    join_date DATE,
    grade VARCHAR(10),
    game_money INTEGER,
    PRIMARY KEY (member_id, server_id), -- 복합 키로 설정
    FOREIGN KEY (member_id) REFERENCES member (member_id),
    FOREIGN KEY (server_id) REFERENCES game (server_id)
);


INSERT INTO access (member_id, server_id, nickname, join_date, grade, game_money)
VALUES ('001', '서버1', 'dog', '2022-07-11', NULL, 1001);
INSERT INTO access (member_id, server_id, nickname, join_date, grade, game_money)
VALUES ('002', '서버2', '뿡', '2024-07-11', NULL, 1002);
INSERT INTO access (member_id, server_id, nickname, join_date, grade, game_money)
VALUES ('003', '서버3', '고양이', '2023-05-01', NULL, 1003);
INSERT INTO access (member_id, server_id, nickname, join_date, grade, game_money)
VALUES ('004', '서버4', '흥', '2013-05-25', NULL, 1004);
INSERT INTO access (member_id, server_id, nickname, join_date, grade, game_money)
VALUES ('005', '서버4', '으르렁', '2024-03-11', NULL, 1000);

SELECT *
FROM access ;


-- 가입 날짜를 기준으로 등급 업데이트
-- 1년 미만은 '브론즈', 1년~5년은 '실버', 10년 이상은 '플래티넘', 나머지는 '골드'
UPDATE access
SET grade = CASE
    WHEN AGE(CURRENT_DATE, join_date) < INTERVAL '1 year' THEN '브론즈' -- 1년 미만
    WHEN AGE(CURRENT_DATE, join_date) BETWEEN INTERVAL '1 year' AND INTERVAL '5 years' THEN '실버' -- 1~5년
    WHEN AGE(CURRENT_DATE, join_date) >= INTERVAL '10 years' THEN '플래티넘' -- 10년 이상
    ELSE '골드' -- 5~10년
END;

-- 결과 확인
SELECT member_id, server_id, nickname, join_date, grade
FROM access;

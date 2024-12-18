CREATE TABLE department(
	deptno INTEGER NOT NULL,
	deptname VARCHAR(10),
	floor INTEGER,
	PRIMARY KEY (deptno)
	);
	
INSERT INTO department VALUES(1, '영업', 8);
INSERT INTO department VALUES(2, '기획', 10);

SELECT *
	FROM department;
	
-- 릴레이션 생성하기 

-- 고객 릴레이션 생성
-- 고객 릴레이션은 고객아이디, 고객이름, 나이, 등급, 직업, 적립금 속성으로 구성된다.
-- 고객아이디 속성이 기본키이다.
-- 적립금 속성은 입력하지 않으면 0을 입력된다.

CREATE TABLE customer(
	customer_id   VARCHAR(20) NOT NULL,  --스네이크 표기법, 대소문자 구분x, = CUSTOMER_ID
	customer_name VARCHAR(10) NOT NULL,
	age           INTEGER,
	grade         VARCHAR(10) NOT NULL,
	job_title     VARCHAR(20),
	saved_money   INTEGER DEFAULT 0,
	PRIMARY KEY (customer_id)
);

INSERT INTO customer 
VALUES ('apple', '정소화', 20, 'gold' ,'학생' ,1000);
INSERT INTO customer
VALUES ('banana', '김선우', 25, 'vip', '간호사', 2500);
INSERT INTO customer
VALUES ('carrot', '고명석', 28, 'gold', '교사', 4500);
INSERT INTO customer
VALUES ('orange', '김용욱', 22, 'silver', '학생', 0);
INSERT INTO customer
VALUES ('melon', '성원용', 35, 'gold', '회사원', 5000);
INSERT INTO customer
VALUES ('peach', '오형준', NULL, 'silver', '의사', 300);
INSERT INTO customer
VALUES ('pear', '채광주', 31, 'silver', '회사원', 500);

SELECT *
	FROM customer;

-- 제품 릴레이션 생성
-- 제품 릴레이션은 제품 번호, 제품명, 재고량, 단가, 제조업체 속성으로 구성된다.
-- 제품번호 속성이 기본키이다.
-- 재고량은 0개 이상, 10000개 이하이다.


CREATE TABLE product (
	product_no   VARCHAR(5) NOT NULL,
	product_name VARCHAR(20),
	stock		 INTEGER,
	unit_price   INTEGER,
	manufacturer VARCHAR(20),
	PRIMARY KEY (product_no),
	CHECK (stock >= 0 AND stock <= 10000) --제약 조건
);
INSERT INTO product
VALUES ('p01', '그냥만두', 5000, 4500, '대한식품');
INSERT INTO product
VALUES ('p02', '매운쫄면', 2500, 5500, '민국푸드');
INSERT INTO product
VALUES ('p03', '쿵떡파이', 3600, 2600, '한밭제과');
INSERT INTO product
VALUES ('p04', '맛난초콜릿', 1250, 2500, '한밭제과');
INSERT INTO product
VALUES ('p05', '얼큰라면', 2200, 1200, '대한식품');
INSERT INTO product
VALUES ('p06', '통통우동', 1000, 1550, '민국푸드');
INSERT INTO product
VALUES ('p07', '달콤비스킷', 1650, 1500, '한밭제과');

SELECT *
	FROM product ;

-- 주문 릴레이션 생성
-- 주문 릴레이션은 주문번호 , 주문 고객아이디, 주문제품번호, 수량,배송지, 주문일자 속성으로 구성되고,
-- 주문번호 속성이 기본키이다.

CREATE TABLE porder (
	order_no    VARCHAR(5) NOT NULL,
	customer_id VARCHAR(20),
	product_no  VARCHAR(5),
	quantity    INTEGER,
	destination VARCHAR(30),
	order_date  DATE,
	PRIMARY KEY (order_no),
	FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
	FOREIGN KEY (product_no) REFERENCES product(product_no)
);
INSERT INTO porder
VALUES ('o01', 'apple', 'p03', 10, '서울시 마포구', '2022-01-01');
INSERT INTO porder
VALUES ('o02', 'melon', 'p01', 5, '인천시 계양구', '2022-01-10');
INSERT INTO porder
VALUES ('o03', 'banana', 'p06', 45, '경기도 부천시', '2022-01-11');
INSERT INTO porder
VALUES ('o04', 'carrot', 'p02', 8, '부산시 금정구', '2022-02-01');
INSERT INTO porder
VALUES ('o05', 'melon', 'p06', 36, '경기도 용인시', '2022-02-20');
INSERT INTO porder
VALUES ('o06', 'banana', 'p01', 19, '충청북도 보은군', '2022-03-02');
INSERT INTO porder
VALUES ('o07', 'apple', 'p03', 22, '서울시 영등포구', '2022-03-15');
INSERT INTO porder
VALUES ('o08', 'pear', 'p02', 50, '강원도 춘천시', '2022-04-10');
INSERT INTO porder
VALUES ('o09', 'banana', 'p04', 15, '전라남도 목포시', '2022-04-11');
INSERT INTO porder
VALUES ('o10', 'carrot', 'p03', 20, '경기도 안양시', '2022-05-22');

--INSERT INTO porder
--VALUES ('o11', 'carrot', 'p11', 20, '경기도 안양시', '2022-05-22');

SELECT *
	FROM porder ;

-- 릴레이션 삭제하기
DROP TABLE department ;


-- 릴레이션 변경하기

-- 고객 릴레이션 가입날짜 추가하기.
ALTER TABLE customer ADD join_date DATE;

-- 고객 릴레이션 가입날짜 삭제 
ALTER TABLE customer DROP COLUMN join_date;

-- 새로운 제약 조건 추가
ALTER TABLE customer ADD CONSTRAINT chk_age CHECK (age >= 18); 

-- 기존 제약 조건 삭제
ALTER TABLE customer DROP CONSTRAINT chk_age;


-- 데이터 검색하기 : SELECT

-- 기본 검색

-- 1. 고객 릴레이션에서 고객아이디, 고객이름, 등급 속성을 검색하시오.

SELECT customer_id ,customer_name, grade 
  FROM customer; 
 
-- 2. 고객 릴레이션에서 모든 속성을 검색하시오.
 
 SELECT customer_id , customer_name , age , grade , job_title , saved_money 
   FROM customer;
   
 SELECT *
   FROM customer;
   
-- 3. 제품 테이블에서 제조업체를 검색하시오. -> 중복 제거
-- 릴레이션 -> 집합 -> 중복이 없음 => 릴레이션 (연산)-> 릴레이션
  
  SELECT manufacturer 
    FROM product;
   --중복 허용 (ALL은 생략 가능,거의 생략)
  SELECT ALL manufacturer  
    FROM product;
   --중복 제거
  SELECT DISTINCT manufacturer 
    FROM product;
    
-- 4. 제품 테이블에서 제품명과 단가를 검색하시오.
-- 단가(unit_price)를 가격이라는 새 이름으로 출력하시오.
   
SELECT product_name , unit_price AS "가격"
  FROM product; 
  
-- 5. 제품 테이블에서 제품명과 단가를 검색하시오.
-- unit_price를 단가라는 새 이름으로 출력하시오.
-- 단가에 500원을 더한 "조정 단가" 라는 새이름으로 출력하시오.
 
 SELECT product_name , unit_price AS "단가",  unit_price  + 500 AS "조정 단가"
  FROM product; 
  
 
-- 조건 검색
 
-- 6. 제품 테이블에서 한밭제과가 제조한 제춤의 제품명, 제고량, 단가를 검색하시오.
 
 SELECT *
   FROM product;
 
 SELECT product_name , stock , unit_price 
   FROM product
  WHERE manufacturer = '한밭제과';
 
-- 7. apple 고객이 15개 이상 주문한 주문제품번호, 수량, 주문일자를 검색하시오.
 
 SELECT product_no , quantity , order_date 
   FROM porder
  WHERE customer_id = 'apple' AND quantity >= 15;
 
 -- 7.2. apple 고객이 주문했거나, 15개 이상 주문한 고객아이디, 주문제품번호, 수량, 주문일자를 검색하시오.

 SELECT customer_id , product_no , quantity , order_date 
   FROM porder
  WHERE customer_id = 'apple' OR quantity >= 15;
 
-- 8. 단가가 2000원 이상, 3000원 이하인 제품의 제품명, 단가, 제조업체를 검색하시오.
 
 SELECT product_name , unit_price , manufacturer 
   FROM product
  WHERE unit_price >= 2000 AND unit_price <= 3000;  -- 범위 질의, RANGE query
 
-- BETWEEN
 SELECT product_name , unit_price , manufacturer 
   FROM product
  WHERE unit_price BETWEEN 2000 AND 3000;
 
 
-- 9. 고객 테이블에서 '김선우' 고객의 고객이름, 나이, 등급, 적립금을 검색하세요.
 
 SELECT customer_name , age, grade ,saved_money 
   FROM customer
  WHERE customer_name = '김선우';
  
-- LIKE를 이용한 검색
-- 9. 고객 테이블에서 성이 김씨인 고객의 고객이름, 나이, 등급, 적립금을 검색하세요.

 SELECT customer_name , age, grade ,saved_money 
   FROM customer
  WHERE customer_name LIKE '김%'; -- % = 김 뒤에 아무거나 와도 가능, 김이 들어간 모든 글자 검색
 
 SELECT customer_name , age, grade ,saved_money 
   FROM customer
  WHERE customer_name LIKE '김__'; -- '김_'이름이 외 자
  
--  이름에 용이 들어가는 고객
  
 SELECT customer_name , age, grade ,saved_money 
   FROM customer
  WHERE customer_name LIKE '%용%'; --성능 문제가 있음, 사용 자제
  
 SELECT *  -- 전체 검색
   FROM customer;
   
--  10. 고객아이디가 5자인 고객의 아이디, 이름, 등급을 검색하시오.
  
  SELECT customer_id , customer_name , grade 
    FROM customer
   WHERE customer_id LIKE '_____';
   
--  NULL 을 이용한 검색 : ISNULL , IS NOT NULL
--  11. 고객 테이블에서 나이가 입력되지 않은 고객의 이름을 검색하시오.
--  NULL : 값없음, 아직 입력되지 않음, 모름, ...
  
  SELECT customer_name 
    FROM customer
   WHERE age IS NULL; 
  
--  12. 고객 테이블에서 나이가 입력된 고객의 이름을 검색하시오.
  
  SELECT customer_name 
    FROM customer
   WHERE age IS NOT NULL; --(NOTNULL은 사용하지마(틀린답)- 다른 dbms에서 실행 안될수도있음)
   
--   정렬 검색
   
  
--   13. 고객 테이블에서 이름, 등급, 나이를 검색하시오.
--   나이의 내림차순으로 정렬하시오.
   
  SELECT customer_name , grade,  age   
    FROM customer
   ORDER BY age DESC; --ASC:오름차순
   
--   14. 수량이 10개 이상인 주문의 주문고객아이디, 주문제품번호, 수량, 주문일자를 검색하시오.
--   제품번호 순으로 정렬하시오.
--   동일 제품의 경우 주문수량의 내림차순으로 정렬하시오.
   
 SELECT customer_id , product_no , quantity , order_date 
   FROM porder
  WHERE quantity >= 10
  ORDER BY product_no ASC , quantity DESC ; --추가정렬은 , 하고 뒤에 순서대로 나열 차순은 원소 뒤에 각각 써줘야함
    

-- 집계함수
  
--  15. 제품테이블에서 모든 제품의 평균 단가를 구하시오.
  SELECT AVG(unit_price)  
   FROM product;
  
--  16. 한밭제과가 제조한 제품의 재고량을 검색하시오.
--  SELECT SUM(stock) AS "재고량합계"
  
  SELECT SUM(stock) AS "재고량합계"  
   FROM product
  WHERE manufacturer = '한밭제과';
 
-- 17. 고객 테이블에 고객이 몇 명 등록되어 있는지 검색하시오. -> count
 SELECT COUNT(customer_id) 
 	FROM customer;
 
 SELECT COUNT(customer_name) --기본키가 null이면 안됨-> 기본키는 다 나옴.
 	FROM customer;
 
 SELECT COUNT(age) 
 	FROM customer;
 
 SELECT COUNT(*)  --count는 튜플이 몇개인지
 	FROM customer;
 
 SELECT COUNT(1)  --가운트*대체
 	FROM customer;
 	
-- 18. 제품테이블에서 제조업체 수를 검색하시오.
 SELECT COUNT(DISTINCT manufacturer) AS "제조업체 수"
	FROM product;
 
-- 그룹별 집계
-- 19. 주문테이블에서 주문제품별 주문 수량의 합계를 검색하시오.
 SELECT  product_no, SUM(quantity)
	FROM porder
  GROUP BY product_no --GROUP BY뒤에오는 집계함수 속성 사용 가능.
  ORDER BY product_no; --정렬
  
-- 20. 제품 테이블에서 제조업체별로(group by) 제조한 제품의 개수와 제품의 최고가를 검색하시오.
  SELECT  manufacturer, COUNT(*) AS "제품수", MAX(unit_price) AS "최고가"
    FROM product
    GROUP BY manufacturer;
-- 21. 제품 테이블에서 제조업체별로(group by) 제조한 제품의 개수와 제품의 최고가를 검색하시오.
-- 제조한 제품이 3개이상인 것만 포함. =>  HAVING 조건문
  SELECT  manufacturer, COUNT(*) AS "제품수", MAX(unit_price) AS "최고가" 
    FROM product
    GROUP BY manufacturer
    HAVING COUNT(*) >= 3; --계산에 대한 결과에대한 조건일 때 씀(GROUP BY,HAVING 같이), "별명은 못 씀"="제품수"
  
-- 22. 고객테이블에서 적립금이 평균 1000원 이상인 등급에 대해
-- 등급별 고객수와 적립금 평균을 검색하시오.
    
  SELECT grade , COUNT(*) AS  "고객 수", AVG(saved_money) AS "적립금 평균"
    FROM customer
    GROUP BY grade 
    HAVING AVG(saved_money) >= 1000;
   
--  23. 주문테이블에서 각 고객이 주문한 제품별의 총 주문수량을 검색하시오.  특정고객이 같은 제품을 몇개나 샀나.
  SELECT customer_id,product_no, SUM(quantity) AS "총 주문수량"
  	FROM porder
  	GROUP BY customer_id, product_no 
    ORDER BY customer_id, product_no ; --정렬
    
  SELECT product_no, customer_id, SUM(quantity) AS "총 주문수량"
  	FROM porder
  	GROUP BY product_no, customer_id  --변수 순서 상관없음
    ORDER BY product_no, customer_id ; --정렬: 제품 번호로 먼저 정렬, 정렬된거에서 고객아이디 정렬

--  조인 검색
-- 24. 바나나 고객이 주문한 제품의 이름을 검색하시오.
    
   SELECT product_name
     FROM porder, product
    WHERE porder.product_no = product.product_no --조인 조건 (튜플 합함)
      AND  customer_id = 'banana';
    
-- 25. 나이가 30세 이상인 고객이 주문한 제품의 주문제품번호와 주문일자를 검색하시오.
     
   SELECT customer.customer_name , porder.product_no, porder.order_date  --테이블명 다 씀
     FROM customer, porder
    WHERE customer.customer_id = porder.customer_id --조인 조건
      AND customer.age >= 30;
     
   SELECT C.customer_name , O.product_no, O.order_date
     FROM customer C, porder O
    WHERE C.customer_id = O.customer_id --조인 조건
      AND C.age >= 30;
    
-- 26. 나이가 30세 이상인 고객이 주문한 제품의 
--고객명, 주문제품명과 주문일자를 검색하시오.

   SELECT C.customer_name , P.product_name, O.order_date
     FROM customer C, porder O, product P 
    WHERE C.customer_id = O.customer_id 
      AND O.product_no = P.product_no --조인 조건
      AND C.age >= 30;
      
-- 자체조인(셀프조인,self join)
     
     ALTER TABLE customer ADD recommender VARCHAR(20);
     ALTER TABLE customer ADD CONSTRAINT chk_recommender
     		FOREIGN KEY (recommender) REFERENCES customer(customer_id);
     	
    UPDATE customer 
       SET recommender = 'orange'
     WHERE customer_id = 'apple';
   
--  27. 고객과 추천인의 이름을 검색하시오.(자체조인)
    SELECT C.customer_name AS "고객이름", R.customer_name AS "추천인이름" 
      FROM customer C, customer R
     WHERE C.recommender = R.customer_id ;

    
    
  SELECT *
	FROM customer ;


-- 
ALTER TABLE customer DROP CONSTRAINT chk_recommender;
ALTER TABLE customer DROP COLUMN recommender;

-- INNER JOIN
-- 28. 나이가 30세 이상인 고객이 주문한 제품의 주문제품번호와 주문일자를 검색하시오.
     
SELECT C.customer_name , O.product_no , O.order_date 
  FROM customer C INNER JOIN porder O ON C.customer_id = O.customer_id 
 WHERE C.age >= 30;
 
-- 외부 조인(OUTER JOIN)
-- 29.주문하지 않은 고객을 포함해서 
-- 주문 고객이름, 주문제품번호, 주문일자를 검색하시오.
-- LEFT OUTER JOIN를 많이씀!
SELECT C.customer_name , O.product_no , O.order_date 
  FROM customer C LEFT OUTER JOIN porder O ON C.customer_id = O.customer_id ; --왼쪽 테이블 기준으로 조인(고객-주문)에서 연결 안된거는 null로 채움
 
SELECT C.customer_name , O.product_no , O.order_date 
  FROM porder O RIGHT OUTER JOIN customer C ON C.customer_id = O.customer_id ; --왼쪽 테이블 기준으로 조인(고객-주문)에서 연결 안된거는 null로 채움
 

--SELECT C.customer_name , O.product_no , O.order_date 
--  FROM porder O FULL OUTER JOIN customer C ON C.customer_id = O.customer_id ; --왼쪽 테이블 기준으로 조인(고객-주문)에서 연결 안된거는 null로 채움
 
     
--  부속질의 (subquery)
  
--  30. 달콤비스킷을 생산한 제조업체가 만든 제품드르이 제품명과 단가를 검색하시오.
  SELECT  product_name , unit_price 
    FROM  product P
   WHERE manufacturer = (SELECT manufacturer 
    					FROM product P 
   						WHERE product_name = '달콤비스킷');
--  달콤비스킷을 생산한 제조업체 명을 검색하시오.
  
  SELECT manufacturer 
    FROM product P 
   WHERE product_name = '달콤비스킷';
 
-- 한밭제과가 제조한 제품들의 제품명과 단가를 검색하시오.
 SELECT  product_name , unit_price 
   FROM  product P
  WHERE manufacturer = '한밭제과';
  
-- 31. 적립금이 가장 많은 고객의 고객 이름과 적립금을 검색하시오.
 SELECT customer_name, saved_money 
   FROM customer C
  WHERE saved_money = (SELECT MAX(saved_money) 
   						FROM customer)	
-- 가장 많은 적립금을 검색하시오.
 
 SELECT MAX(saved_money) 
   FROM customer C; 
 
 
 SELECT customer_name ,saved_money 
   FROM customer C
  WHERE 
  
--  32. 바나나 고객이 주문한 제품의 제품명과 제조업체를 검색하시오.
--  부속질의를 사용할 것.
  
SELECT product_name , manufacturer 
  FROM product
 WHERE product_no IN (SELECT product_no 
  					FROM porder
  					WHERE customer_id = 'banana');
  				
--  앞으로 고객이 추가주문하면 값이 달라짐 -> 바꿔야함				
SELECT product_name , manufacturer 
  FROM product
 WHERE product_no IN (SELECT product_no 
  					FROM porder
  					WHERE customer_id = 'pear');
  				
SELECT product_name ,manufacturer 
  FROM product
 WHERE product_no IN ('p01','p04','p06');
  				
--  바나나 고객이 주문한 제품의 제품번호를 검색하시오.
  SELECT product_no 
  FROM porder
  WHERE customer_id = 'banana';
 
 
-- 33. 바나나 고객이 주문하지 않은 제품의 제품명과 제조업체를 검색하시오.
--  부속질의를 사용할 것.
 
 SELECT product_name ,manufacturer 
   FROM product
  WHERE product_no NOT IN (SELECT product_no 
  							 FROM porder
  							WHERE customer_id = 'banana');
  				
-- 34. 대한식품이 제조한 모든 제품의 단가보다 비싼 제품의 
-- 제품명, 단가, 제조업페를 검색하시오.
-- 대한 식품이 제조한 제품의 단가를 구하시오.
  						
SELECT product_name ,unit_price ,manufacturer 
  FROM product
 WHERE unit_price > ALL(SELECT unit_price 
   				 		  FROM product
						 WHERE manufacturer = '대한식품');

SELECT unit_price 
  FROM product
 WHERE manufacturer = '대한식품';

-- 35. 2022년 3월 15일에 제품을 주문한 고객의 고객이름을 검색하시오.
-- 상관 중첩 질의 (correlated nested query)
SELECT *
  FROM porder 
 WHERE order_date = '2022-03-15';

-- 밖에있는 결과를 하나씩 넣어서 수행 / WHERE EXISTS = 결과가 있으면 고객에 대한 정보가 출력/WHERE NOT EXISTS = 3-15일에 주문하지 않은 고객
SELECT customer_name 
FROM customer 
WHERE EXISTS (SELECT *
  				FROM porder 
 			   WHERE order_date = '2022-03-15'
 				 AND porder.customer_id = customer.customer_id); --porder_id에 customer_id를 하나씩 넣어서 주문한 사람 찾음
 				
SELECT *
  	FROM porder 
 	WHERE order_date = '2022-03-15'
 	AND porder.customer_id = 'apple';


  SELECT *
  FROM customer c  ;
  
 
-- 집합 연산
 
-- 고객테이블에서 고객아이디가 5자이거나
-- 나이가 아직 입력되지 않은 고객의 고객이름을 검색하시오.
-- union 중복제거 -> 성능 저하 가능
-- union all 중복이 제거 안함 (위 아래 수행결과 붙임(빠름))
-- EXCEPT (차집합 a-b = a에는있는데 b에는 없는것)
 
 SELECT customer_name 
   FROM customer
  WHERE customer_id LIKE '_____' 
UNION ALL 
  SELECT customer_name 
   FROM customer
  WHERE age IS NULL;
 
 
-- 데이터 삽입 : INSERT
 
-- 고객 테이블에 아이디가 raspberry, 이름이 산딸기,나이가 21세, 등급이 vip
-- 직업은 학생, 적립금이 1000원인 새로운 고객을 추가하시오.
 
INSERT 
  INTO customer 
VALUES ('raspberry','산딸기',21,'vip','학생',1000);

INSERT 
INTO customer (customer_id, customer_name, age, grade , job_title , saved_money)
VALUES ('raspberry2','산딸기',21,'vip','학생',1000);

-- 아이디가 pi, 이름은 라즈베리, 나이는 36세, 등급은 gold, 적립금은 4000원
-- 직업은 모름(NULL) 고객을 추가하시오.
INSERT -- 이 구조에서는 꼭 순서를 지켜야함, 내용을 알아야함
  INTO customer 
VALUES ('pi','라즈베리',36,'gold',NULL ,4000);

INSERT -- 생략가능(직업)
  INTO customer(customer_id, customer_name, saved_money, age , grade) --colnum은 순서와 상관없이 입력하고 싶은 순서대로 가능 
VALUES	('pi2','라즈베리', 4000, 36,'gold');

INSERT -- 생략가능(직업) / 적립금은 입력이 안되면 0으로 채운다는 default값이 있음
  INTO customer(customer_id, customer_name, age , grade) --colnum은 순서와 상관없이 입력하고 싶은 순서대로 가능 
VALUES	('pi3','라즈베리', 36,'gold');

-- 부속 질의를 이용한 데이터 삽입

CREATE TABLE hanbat_product (
	product_name VARCHAR(20),
	stock INTEGER,
	unit_price INTEGER,
	PRIMARY KEY (product_name)
	);

-- 한밭제과가 제조한 제품의 이름,재고량,단가를 검색하시오.

SELECT product_name , stock ,unit_price 
FROM product
WHERE manufacturer = '한밭제과';


INSERT 
  INTO hanbat_product (product_name,stock ,unit_price)
SELECT product_name , stock ,unit_price 
  FROM product
 WHERE manufacturer = '한밭제과';


SELECT *
FROM hanbat_product ;

-- 데이터 수정 : UPDATE


-- 제품 테이블에서 제춤번호가 'p03'인 제품을 검색하시오
-- 제품 테이블에서 제춤번호가 'p03'인 제품의 제품명을 '통큰파이'로 수정하시오

SELECT *
  FROM product
 WHERE product_no = 'p03';

UPDATE product
   SET product_name = '통큰파이'
 WHERE product_no = 'p03';

-- 제품 테이블에 있는 모든 제품의 단가를 10% 인상하시오. 

 UPDATE product 
   SET unit_price = unit_price * 1.1;
  
--  부속 질의문을 이용한 	UPDATE
--  정소화 고객이 주문한 제품의 주문수량을 5개로 수정하시오.
 
--  정소화 고객이 주문한 제품의 주문정보를 검색하시오.
  
  SELECT customer_id 
  FROM customer 
  WHERE customer_name = '정소화'
  
  SELECT *
    FROM porder 
   WHERE customer_id = (SELECT customer_id 
  						FROM customer 
  						WHERE customer_name = '정소화');	
 UPDATE porder					
    SET quantity = 5
  WHERE customer_id = (SELECT customer_id 
  						FROM customer 
  						WHERE customer_name = '정소화');	
  					
-- 데이터 삭제 : DELETE
DELETE 
  FROM 테이블 이름
 WHERE 조건;
  
-- 주문 테이블에서 2022년 5월 22일 주문내역을 삭제하시오.

DELETE 
  FROM porder
 WHERE order_date = '2022-05-22';

-- 정소화 고객이 주문한 내역을 주문 테이블에서 삭제하시오.

-- 부속질의를 이용한 데이터 삭제

DELETE 
  FROM porder 
 WHERE customer_id = (SELECT customer_id 
  						FROM customer 
  					   WHERE customer_name = '정소화');	

DELETE 
  FROM porder ;
  
 SELECT *
 FROM porder ;
 

-- 뷰 
-- 고객 테이블에서 등급이 vip인 고객의 아이디,, 이름, 나이를 검색
-- 이 내용으로 vip_customer라는 뷰를 생성하시오.
CREATE VIEW vip_customer
AS 
SELECT customer_id,customer_name , age 
  FROM customer
 WHERE grade = 'vip';

SELECT *
FROM vip_customer;

CREATE VIEW vip_customer2(id, name, age)
AS 
SELECT customer_id,customer_name , age 
  FROM customer
 WHERE grade = 'vip';
-- colnum name change
SELECT *
FROM vip_customer2;

-- 제조업체별로 제품수를 검색하시오.

CREATE VIEW m_product(manufacturer, product_count)
AS	
SELECT manufacturer ,count(*) 
FROM product  
GROUP BY manufacturer;

SELECT *
FROM m_product;

-- select문을 저장해 뒀다가 고객테이블에 vip를 추가하고 다시 출력을 하면 추가한 내용도 보임


-- 

CREATE VIEW product_1
AS
SELECT product_no, product_name, stock
FROM product p ;

SELECT *
FROM product_1;

-- INSERT INTO product_1 -> 새로운 내용을 추가
-- 가능할까여? y / 실제로는 product에 인서트가 됨

INSERT INTO product_1
VALUES ('p08', '새로운제품',2000);

CREATE VIEW product_2
AS
SELECT product_name, stock
FROM product;

SELECT *
FROM product_2 ;

INSERT INTO product_2
VALUES ('새로운 제품2', 3000);
-- 뷰는 무조건 변경이 가능한 것은 아님, pk 널 안됨
-- 뷰는 insert, update, delete 가능? (제한적 가능) - 왠만하면 뷰는 수정의 대상이 아님(대부분 검색용으로 만듬)
-- 뷰가 기본키를 포함해야 가능, NOT NULL로 지정한 속성이 있으면 포함해야함

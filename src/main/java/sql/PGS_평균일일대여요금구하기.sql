/*
 CAR_RENTAL_COMPANY_CAR 테이블
 CAR_ID, CAR_TYPE, DAILY_FEE, OPTIONS
 -> CAR_TYPE이 SUV인 자동차들의 평균 일일 대여 요금을 출력
 */
 -- ROUND(x,y) 함수 -> x의 소수 y번째 자리에서 반올림 -> y매개변수가 없으면 소수 첫 번째 자리에서 반올림(default)
 SELECT ROUND(AVG(DAILY_FEE),0) FROM CAR_RENTAL_COMPANY_CAR
 WHERE CAR_TYPE = 'SUV';
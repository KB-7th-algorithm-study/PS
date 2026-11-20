-- https://school.programmers.co.kr/learn/courses/30/lessons/284529
-- [프로그래머스] 부서별 평균 연봉 조회하기 : SQL
SELECT e.DEPT_ID,
       d.DEPT_NAME_EN,
       ROUND(AVG(SAL)) AS AVG_SAL
FROM HR_EMPLOYEES e
    JOIN HR_DEPARTMENT d
        ON e.DEPT_ID = d.DEPT_ID
GROUP BY e.DEPT_ID
ORDER BY AVG_SAL DESC;
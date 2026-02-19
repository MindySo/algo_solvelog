-- 코드를 작성해주세요
SELECT e.EMP_NO, EMP_NAME, GRADE, 
        CASE WHEN GRADE = "S" THEN SAL * 0.2
             WHEN GRADE = "A" THEN SAL * 0.15
             WHEN GRADE = "B" THEN SAL * 0.1
             ELSE 0
             END AS BONUS
FROM HR_DEPARTMENT d
JOIN HR_EMPLOYEES e
ON d.DEPT_ID = e.DEPT_ID
JOIN (SELECT EMP_NO,
            CASE WHEN AVG(SCORE) >= 96 THEN "S"
                 WHEN AVG(SCORE) >= 90 THEN "A"
                 WHEN AVG(SCORE) >= 80 THEN "B"
                 ELSE "C"
                 END AS GRADE
      FROM HR_GRADE 
      GROUP BY EMP_NO) g
on e.EMP_NO = g.EMP_NO
ORDER BY e.EMP_NO;

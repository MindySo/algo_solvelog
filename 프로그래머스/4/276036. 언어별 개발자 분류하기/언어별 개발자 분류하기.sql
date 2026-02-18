-- 코드를 작성해주세요
WITH skills AS (SELECT ID, NAME, CATEGORY, CODE
                FROM SKILLCODES s
                JOIN DEVELOPERS d
                ON d.SKILL_CODE & s.CODE > 0),
    fe AS (SELECT ID
           FROM skills
           WHERE CATEGORY = "Front End"),
    py AS (SELECT ID 
           FROM skills
           WHERE NAME = "Python"),
    c AS (SELECT ID
          FROM skills
          WHERE NAME = "C#"),
    grade AS (SELECT ID, EMAIL, CASE WHEN ID IN (SELECT * FROM fe)
                                        AND ID IN (SELECT * FROM py) THEN "A"
                                     WHEN ID IN (SELECT * FROM c) THEN "B"
                                     WHEN ID IN (SELECT * FROM fe) THEN "C"
                                     END AS GRADE
              FROM DEVELOPERS)
SELECT GRADE, ID, EMAIL
FROM grade
WHERE GRADE IS NOT NULL
ORDER BY GRADE, ID;
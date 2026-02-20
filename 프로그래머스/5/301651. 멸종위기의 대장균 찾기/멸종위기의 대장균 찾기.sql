-- 코드를 작성해주세요
WITH RECURSIVE gen AS (SELECT ID, PARENT_ID, (1) AS GENERATION 
                       FROM ECOLI_DATA 
                       WHERE PARENT_ID IS NULL
                       
                       UNION ALL 
                       
                       SELECT e.ID, e.PARENT_ID, (GENERATION + 1) AS GENERATION 
                       FROM gen g
                       JOIN ECOLI_DATA e
                       ON g.ID = e.PARENT_ID)
SELECT COUNT(*) AS COUNT, GENERATION
FROM gen
WHERE ID NOT IN (SELECT p.ID
                 FROM ECOLI_DATA p
                 JOIN ECOLI_DATA c
                 ON p.ID = c.PARENT_ID
                 GROUP BY p.ID)
GROUP BY GENERATION
ORDER BY GENERATION;
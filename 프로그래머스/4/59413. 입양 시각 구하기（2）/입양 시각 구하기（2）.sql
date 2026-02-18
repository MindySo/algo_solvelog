-- 코드를 입력하세요
WITH RECURSIVE hours AS (SELECT 0 AS HOUR 
                         UNION ALL 
                         SELECT HOUR + 1 
                         FROM hours 
                         WHERE HOUR < 23)
SELECT HOUR, COUNT(ANIMAL_ID) AS COUNT
FROM hours h
LEFT JOIN ANIMAL_OUTS o
ON h.HOUR = HOUR(o.DATETIME)
GROUP BY HOUR
ORDER BY HOUR;
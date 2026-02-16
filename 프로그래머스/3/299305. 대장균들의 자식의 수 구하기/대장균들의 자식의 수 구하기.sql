-- 코드를 작성해주세요
SELECT parent.ID, COUNT(child.ID) AS CHILD_COUNT
FROM ECOLI_DATA parent
LEFT JOIN ECOLI_DATA child
ON parent.ID = child.PARENT_ID
GROUP BY parent.ID
ORDER BY parent.ID;
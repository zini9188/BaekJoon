-- 코드를 작성해주세요
WITH FIND AS (
    SELECT *
FROM FISH_INFO
WHERE LENGTH IS NULL
    )
SELECT COUNT(*) AS FISH_COUNT
FROM FIND;
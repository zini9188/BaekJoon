-- 코드를 작성해주세요
SELECT ID, EMAIL, FIRST_NAME, LAST_NAME FROM DEVELOPERS D
JOIN SKILLCODES S ON S.CODE & D.SKILL_CODE
WHERE S.NAME = 'Python' OR S.NAME = 'C#'
ORDER BY ID;
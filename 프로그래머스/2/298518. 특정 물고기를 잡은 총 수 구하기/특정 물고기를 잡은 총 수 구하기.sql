-- 코드를 작성해주세요
SELECT count(*) AS FISH_COUNT
FROM FISH_INFO FI
LEFT JOIN FISH_NAME_INFO FNI
ON FI.FISH_TYPE = FNI.FISH_TYPE
WHERE FNI.FISH_NAME in ('BASS', 'SNAPPER')
;

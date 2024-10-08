-- 코드를 작성해주세요

SELECT C.ID AS ID, C.GENOTYPE AS GENOTYPE, P.GENOTYPE AS PARENT_GENOTYPE FROM ECOLI_DATA C
JOIN ECOLI_DATA P
ON P.ID = C.PARENT_ID
WHERE P.GENOTYPE & C.GENOTYPE = P.GENOTYPE
ORDER BY C.ID;
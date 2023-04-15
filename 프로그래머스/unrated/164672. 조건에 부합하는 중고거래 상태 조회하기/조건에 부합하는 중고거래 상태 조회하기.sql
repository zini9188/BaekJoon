SELECT BOARD_ID, WRITER_ID, TITLE, PRICE, 
    CASE STATUS 
     WHEN 'DONE' THEN "거래완료"
     WHEN 'SALE' THEN "판매중"
     WHEN 'RESERVED' THEN "예약중"
     END AS STATUS
FROM USED_GOODS_BOARD AS b
WHERE CREATED_DATE = '2022-10-05'
ORDER BY BOARD_ID DESC;
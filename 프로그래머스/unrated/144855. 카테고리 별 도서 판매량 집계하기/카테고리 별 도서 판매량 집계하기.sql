select b.category, sum(s.sales) as TOTAL_SALES from BOOK_SALES as s
JOIN BOOK as b on s.BOOK_ID = b.BOOK_ID
WHERE s.SALES_DATE BETWEEN '2022-01-01' AND '2022-01-31'
GROUP BY CATEGORY
ORDER BY CATEGORY;
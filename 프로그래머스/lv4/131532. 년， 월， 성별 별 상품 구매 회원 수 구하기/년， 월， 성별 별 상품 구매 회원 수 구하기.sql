SELECT YEAR(SALES_DATE) as YEAR, MONTH(SALES_DATE) as MONTH, GENDER, count(DISTINCT i.USER_ID) AS USERS FROM USER_INFO as i
join online_sale as s
on i.user_id = s.user_id
WHERE GENDER is not null
group by YEAR, MONTH, GENDER
order by YEAR, MONTH, GENDER;
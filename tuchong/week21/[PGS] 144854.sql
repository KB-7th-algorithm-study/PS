-- // https://school.programmers.co.kr/learn/courses/30/lessons/144854
-- [프로그래머스] 조건에 맞는 도서와 저자 리스트 출력하기 : SQL

SELECT BOOK_ID, AUTHOR_NAME, PUBLISHED_DATE
FROM BOOK JOIN AUTHOR
WHERE BOOK.AUTHOR_ID = AUTHOR.AUTHOR_ID
AND CATEGORY = '경제'
ORDER BY PUBLISHED_DATE ASC;
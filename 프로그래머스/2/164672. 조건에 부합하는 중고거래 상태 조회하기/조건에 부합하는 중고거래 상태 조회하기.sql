-- 코드를 입력하세요
SELECT BOARD_ID, WRITER_ID, TITLE, PRICE, 
        CASE WHEN status = 'SALE' THEN '판매중'
        WHEN status = 'RESERVED' THEN '예약중' 
        WHEN status = 'DONE' THEN '거래완료' END 'STATUS'
    FROM USED_GOODS_BOARD
    WHERE board_id IN(SELECT board_id FROM used_goods_board WHERE created_date = '2022-10-05')
    ORDER BY BOARD_ID DESC
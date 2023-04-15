select concat('/home/grep/src/', b.board_id, '/', f.file_id, f.file_name, f.file_ext) as FILE_PATH 
from 
    (select * 
    from used_goods_board 
    order by views desc 
    limit 1) as b
join used_goods_file as f on b.board_id = f.board_id
order by file_id desc;
select i.animal_id, i.name from animal_ins as i 
join animal_outs as o on i.animal_id = o.animal_id
order by(o.datetime - i.datetime) desc
limit 2;
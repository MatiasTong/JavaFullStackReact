select * from World2.countrylanguage;

select * 
from World2.countrylanguage 
where Language like ("F%"); -- "F__"

-- good for dates
select * 
from World2.countrylanguage 
where Percentage between 5.0 and 7.5 
order by CountryCode, Percentage; 

-- inner join - intersection
-- left join is default outer join all the data in the left circle and not in the intersection
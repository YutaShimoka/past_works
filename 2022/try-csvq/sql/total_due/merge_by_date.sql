select date,
    sum(price) as merged
from (
    select datetime_format(date, '%Y-%m-%d') as date,
        counts * ceil((price + ceil(price * tax_rate)) * percentage_paid) as price
    from stdin
    )
group by date;

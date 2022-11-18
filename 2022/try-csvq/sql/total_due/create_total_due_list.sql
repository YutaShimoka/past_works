select distinct shop_name || store_name as store_name,
    datetime_format(receipt.date, '%Y-%m-%d') as date,
    total_due
from stdin as receipt, (
    select date,
        sum(price) as total_due
    from (
        select date,
            counts * ceil((price + ceil(price * tax_rate)) * percentage_paid) as price
        from stdin
        )
    group by date
    ) as tmp
where receipt.date = tmp.date;

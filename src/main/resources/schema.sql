create table customer (customer_id int, card_number varchar2(20), customer_name varchar2(50));
create table transaction (transaction_id int,customer_id int ,transaction_date date,amount int);
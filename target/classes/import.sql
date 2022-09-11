insert into customer(customer_id,card_number,customer_name) values (101,'PAN1234','customer1');
insert into customer(customer_id,card_number,customer_name) values (102,'PAS1234','customer2');
insert into customer(customer_id,card_number,customer_name) values (103,'ADR1234','customer3');

insert into transaction(transaction_id,customer_id,transaction_date,amount) values (111,101,'2022-09-09',120);
insert into transaction(transaction_id,customer_id,transaction_date,amount) values (112,101,'2022-08-12',85);
insert into transaction(transaction_id,customer_id,transaction_date,amount) values (113,101,'2022-07-17',160);
insert into transaction(transaction_id,customer_id,transaction_date,amount) values (114,101,'2022-07-01',90);
insert into transaction(transaction_id,customer_id,transaction_date,amount) values (115,102,'2022-09-01',113);
insert into transaction(transaction_id,customer_id,transaction_date,amount) values (116,102,'2022-08-10',80);
insert into transaction(transaction_id,customer_id,transaction_date,amount) values (117,102,'2022-07-30',102);
insert into transaction(transaction_id,customer_id,transaction_date,amount) values (118,103,'2022-09-05',102);
insert into transaction(transaction_id,customer_id,transaction_date,amount) values (119,103,'2022-07-25',84);
commit;
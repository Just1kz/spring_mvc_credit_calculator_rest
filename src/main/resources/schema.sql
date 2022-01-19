create schema calculator;

create table credit(
                       id int AUTO_INCREMENT primary key,
                       periodCredit int,
                       amount double,
                       costPercentage double,
                       status varchar(50) default 'Payments is not calculated'
);

create table payment(
                        id int AUTO_INCREMENT primary key,
                        month int,
                        debtBeforePayment numeric(10, 2),
                        percentPayment numeric(10, 2),
                        debtPayment numeric(10, 2),
                        payment numeric(10, 2),
                        debtAfterPayment numeric(10, 2),
                        credit_id int references credit(id)
);

insert into credit(periodCredit, amount, costPercentage, status) values (12, 500000, 17.5, 'Calculate is Done!');
insert into credit(periodCredit, amount, costPercentage) values (20, 500000, 17.5);
insert into credit(periodCredit, amount, costPercentage) values (30, 500000, 17.5);

insert into payment(month, debtBeforePayment, percentPayment, debtPayment, payment, debtAfterPayment, credit_id)
VALUES (1, 51, 51, 51, 51, 51, 1);
insert into payment(month, debtBeforePayment, percentPayment, debtPayment, payment, debtAfterPayment, credit_id)
VALUES (2, 52, 52, 52, 52, 52, 1);
insert into payment(month, debtBeforePayment, percentPayment, debtPayment, payment, debtAfterPayment, credit_id)
VALUES (3, 53, 53, 53, 53, 53, 1);

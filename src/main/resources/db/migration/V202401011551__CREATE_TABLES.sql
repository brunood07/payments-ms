create schema if not exists ecomm_payments;

create table ecomm_payments.tb_payments(
    payment_id serial primary key,
    order_id bigint not null,
    value numeric(15, 2),
    payment_type varchar(10),
    payment_status varchar(20) default 'pending',
    description varchar(200),
    installments integer default 1,
    client_document varchar(20) not null,
    created_at timestamp without time zone not null,
    updated_at timestamp without time zone not null
);

alter table ecomm_payments.tb_payments owner to admin;
grant all on table ecomm_payments.tb_payments to admin;
grant select,insert,update,delete on table ecomm_payments.tb_payments to admin;
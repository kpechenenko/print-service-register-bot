create table registration_history(
    id serial primary key,
    user_email varchar(255) not null,
    company_email varchar(255) not null,
    email_send_to_service boolean not null,
    registration_date timestamp
 );

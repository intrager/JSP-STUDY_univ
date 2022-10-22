create sequence seq_writer201712046 increment by 1 start with 1;

create table writer201712046
(
    id      number(11)   not null primary key,
    email   varchar2(30) not null unique,
    pw      varchar2(30) not null,
    name    varchar2(30) not null,
    phone   varchar2(50),
    address varchar2(100)
);


insert into writer201712046 values(seq_writer201712046.nextval, 'root@induk.ac.kr' , 'cometrue', 'admin', '01012345678', 'seoul');

update writer201712046 set name='admin' where name='관리자';

select * from writer201712046;

drop sequence seq_writer201712046;
drop table writer201712046;

commit;
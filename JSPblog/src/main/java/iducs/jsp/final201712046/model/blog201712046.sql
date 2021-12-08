create sequence seq_blog201712046 increment by 1 start with 1;

create table blog201712046
(
    id number(11) not null primary key,
    name varchar2(30) not null,
    email varchar2(30) not null,
    title varchar2(50),
    content varchar2(100)
);

insert into blog201712046 values(seq_blog201712046.nextval, 'comso1', 'comso1@induk.ac.kr', 'comso1', 'comso1');
insert into blog201712046 values(seq_blog201712046.nextval, 'comso2', 'comso2@induk.ac.kr', 'comso2', 'comso2');
insert into blog201712046 values(seq_blog201712046.nextval, 'comso3', 'comso3@induk.ac.kr', 'comso3', 'comso3');
insert into blog201712046 values(seq_blog201712046.nextval, 'comso4', 'comso4@induk.ac.kr', 'comso4', 'comso4');
insert into blog201712046 values(seq_blog201712046.nextval, 'comso5', 'comso5@induk.ac.kr', 'comso5', 'comso5');
insert into blog201712046 values(seq_blog201712046.nextval, 'comso6', 'comso6@induk.ac.kr', 'comso6', 'comso6');
insert into blog201712046 values(seq_blog201712046.nextval, 'comso7', 'comso7@induk.ac.kr', 'comso7', 'comso7');
insert into blog201712046 values(seq_blog201712046.nextval, 'comso8', 'comso8@induk.ac.kr', 'comso8', 'comso8');

update blog201712046 set name='홍길동' , email='comso@induk.ac.kr', title='인덕대학', content='JSP Programming' where id=2;

/*
update blog201712046 set name=? , email=?, title=?, content=? where id=?;
*/

select * from blog201712046;

drop sequence seq_blog201712046;
drop table blog201712046;

commit;

select * from (select * from blog201712046 order by id desc) where id between 1 and 3;
select * from (select A.*, rownum as rnum from (select * from blog201712046 order by id desc) A) where rnum >= 4 and rnum <= 6;

select * from (select A.*, rownum as rnum from (select * from blog201712046 order by id) A) order by rnum desc;
select * from (select A.*, rownum as rnum from (select * from blog201712046 order by id desc) A);

create sequence seq_member201712046 increment by 1 start with 1;

create table member201712046
(
    id      number(11)   not null primary key,
    email   varchar2(30) not null unique,
    pw      varchar2(30) not null,
    name    varchar2(30) not null,
    phone   varchar2(50),
    address varchar2(100)
);


insert into member201712046 values(seq_member201712046.nextval, 'root@induk.ac.kr' , 'cometrue', 'admin', '01012345678', 'seoul');

update member201712046 set name='admin' where name='관리자';

select * from member201712046;

drop sequence seq_member201712046;
drop table member201712046;

commit;
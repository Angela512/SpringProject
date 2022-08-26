-- 회의실 예약하기
create table reservation(
 reserve_num number not null,
 mem_num number not null,
 reserve_title varchar2(90) not null,
 reserve_date date,
 reserve_room varchar2(30) not null,
 reserve_room_seat number not null,
 constraint reservation_pk primary key (reserve_num),
 constraint reservation_fk foreign key (mem_num) references member (mem_num)
);

create sequence reservation_seq;






 mem_num number not null,
 id varchar2(12) unique not null,
 nick_name varchar2(30),
 auth number(1) default 2 not null, -- 0탈퇴회원, 1정지회원, 2일반회원, 3관리자
 constraint spmember_pk primary key (mem_num)
);
create table spmember_detail(
 mem_num number not null,
 name varchar2(30) not null,
 passwd varchar2(12) not null,
 phone varchar2(15) not null,
 email varchar2(50) not null,
 zipcode varchar2(5) not null,
 address1 varchar2(90) not null,
 address2 varchar2(90) not null,
 photo blob,
 photo_name varchar2(100),
 reg_date date default sysdate not null,
 modify_date date,
 constraint spmember_detail_pk primary key (mem_num),
 constraint spmember_detail_fk1 foreign key (mem_num) references spmember (mem_num)
);

create sequence spmember_seq;

--게시판
create table spboard(
 board_num number not null,
 title varchar2(90) not null,
 content clob not null,
 hit number(8) default 0 not null,
 reg_date date default sysdate not null,
 modify_date date,
 uploadfile blob,
 filename varchar2(100),
 ip varchar2(40) not null,
 mem_num number not null,
 constraint spboard_pk primary key (board_num),
 constraint spboard_fk2 foreign key (mem_num) references spmember (mem_num)
 
);

create sequence spboard_seq;



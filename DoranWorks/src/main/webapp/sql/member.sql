--회원 정보 저장
create table member(
	mem_num number not null,
	mem_id varchar2(20) not null,
	auth number(1) default 1 not null, --0)탈퇴(휴직) 1)일반 2)관리자
	constraint member_pk primary key (mem_num)
);

--회원 상세 정보 저장
create table member_detail(
	mem_num number not null,
	mem_name varchar2(15) not null,
	mem_pw varchar2(35) not null,
	mem_phone varchar2(15) not null,
	mem_email varchar2(50) not null,
	mem_zipcode varchar2(5) not null,
	mem_addr1 varchar2(100) not null,
	mem_addr2 varchar2(100) not null,
	mem_date date default sysdate not null,
	mem_modify_date date,
	mem_dpt_num number not null,
	mem_rank_num number not null,
	mem_birthdate date not null,
	mem_photo varchar2(100),
	mem_type varchar2(50) not null,
	constraint member_detail_pk primary key (mem_num),
	constraint member_detail_fk1 foreign key(mem_num) references member(mem_num),
	constraint member_detail_fk2 foreign key(mem_dpt_num) references mem_dpt(mem_dpt_num),
	constraint member_detail_fk3 foreign key(mem_rank_num) references mem_rank(mem_rank_num)
);
create sequence member_seq;

create table mem_dpt(
	mem_dpt_num number not null,
	mem_dpt varchar2(50) not null,
	constraint mem_dpt_pk primary key (mem_dpt_num)
);
create sequence dpt_seq;

create table mem_rank(
	mem_rank_num number not null,
	mem_rank varchar2(30) not null,
	constraint mem_rank_pk primary key(mem_rank_num)
);
create sequence rank_seq;





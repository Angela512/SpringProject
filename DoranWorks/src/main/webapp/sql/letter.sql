--보낸쪽지함
create table letter_send(
	send_num number not null,
	send_title varchar2(50) not null,
	send_sender_num number not null,
	send_receiver_num varchar2(20) not null,
	send_reference_num number,
	send_date date default sysdate not null,
	send_content varchar2(500) not null,
	send_read number default 0 not null,
	send_important number default 0 not null,
	send_uploadfile1 blob,
	send_uploadfile2 blob,
	send_filename1 varchar2(100),
	send_filename2 varchar2(100),
	send_sender_id varchar2(50) not null,
	send_receiver_id varchar2(50) not null,
	send_reference_id varchar2(50),
	constraint send_pk primary key (send_num),
	constraint send_fk1 foreign key (send_sender_num) references member (mem_num),
);

create sequence send_seq;

--받는쪽지함
create table letter_receive(
	rec_num number not null,
	rec_title varchar2(50) not null,
	rec_sender_num number not null,
	rec_receiver_num number not null,
	rec_reference_num number,
	rec_date date default sysdate not null,
	rec_content varchar2(500) not null,
	rec_read number default 0 not null,
	rec_important number default 0 not null,
	rec_uploadfile1 blob,
	rec_uploadfile2 blob,
	rec_filename1 varchar2(100),
	rec_filename2 varchar2(100),
	rec_sender_id varchar2(50) not null,
	rec_receiver_id varchar2(50) not null,
	rec_reference_id varchar2(50),
	constraint rec_pk primary key (rec_num),
	constraint rec_fk1 foreign key (rec_sender_num) references member (mem_num),
	constraint rec_fk2 foreign key (rec_receiver_num) references member (mem_num)
);

create sequence rec_seq;

--공지테이블
create table notice(
	notice_num number not null,
	mem_num number not null,
	notice_title varchar2(50) not null,
	notice_date date default sysdate not null,
	notice_count number default 0 not null,
	notice_content clob not null,
	notice_uploadfile1 blob,
	notice_uploadfile2 blob,
	notice_filename1 varchar2(100),
	notice_filename2 varchar2(100),
	notice_head number default 0 not null,
	constraint notice_pk primary key (notice_num),
	constraint notice_fk foreign key (mem_num) references member (mem_num)
);

create sequence notice_seq;

--공지 읽음/안읽음
create table notice_read(
	read_num number not null,
	mem_num number not null,
	notice_num number not null,
	constraint read_pk primary key (read_num),
	constraint read_fk1 foreign key (mem_num) references member (mem_num),
	constraint read_fk2 foreign key (notice_num) references notice (notice_num)
);

create sequence notice_read_seq;

--공지 댓글
create table notice_reply(
	reply_num number not null,
	mem_num number not null,
	notice_num number not null,
	reply_content varchar2(1000) not null,
	reply_date date default sysdate not null,
	constraint reply_pk primary key (reply_num),
	constraint reply_fk1 foreign key (mem_num) references member (mem_num),
	constraint reply_fk2 foreign key (notice_num) references notice (notice_num)
);

create sequence notice_reply_seq;
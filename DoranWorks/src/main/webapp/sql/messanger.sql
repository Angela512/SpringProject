create table message(
	msg_num number not null,
	mem_num number not null,
	chatroom_num number not null,
	msg_content varchar2(4000) not null,
	msg_sendtime date default sysdate not null,
	msg_opentime date,
	msg_filename varchar2(100),
	msg_uploadfile blob,
	constraint message_pk primary key(msg_num),
	constraint message_fk1 foreign key(mem_num) references member(mem_num),
	constraint message_fk2 foreign key(chatroom_num) references chatroom(chatroom_num)
);
create sequence message_seq;

create table chatread(
	chatread_num number not null,
	msg_num number not null,
	mem_num number not null,
	constraint chatread_pk primary key(chatread_num),
	constraint chatread_fk1 foreign key(msg_num) references message(msg_num),
	constraint chatread_fk2 foreign key(mem_num) references member(mem_num)
);
create sequence chatread_seq;

create table chatroom(
	chatroom_num number not null,
	chatroom_name varchar2(50),
	constraint chatroom_pk primary key(chatroom_num)
);
create sequence chatroom_seq;

create table chatmem(
	chatroom_num number not null,
	mem_num number not null,
	constraint chatmem_fk1 foreign key(chatroom_num) references chatroom(chatroom_num),
	constraint chatmem_fk2 foreign key(mem_num) references member(mem_num),
);
create sequence chatmem_seq;




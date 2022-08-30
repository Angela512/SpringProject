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
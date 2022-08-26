create table workflow_form(
form_num number not null,
mem_num number not null,
form_date date default SYSDATE not null,
form_modify date,
form_sort varchar(30) not null,
form_uploadfile blob,
form_no varchar(30) not null,
form_start varchar(30) not null,
form_end varchar(30) not null,
form_reason clob not null,
constraint workflow_form_pk primary key (form_num),
constraint workflow_form_fk1 foreign key (mem_num) references member (mem_num)
);

create table workflow_main(
flow_num number not null,
form_num number not null,
mem_num number not null,
flow_title varchar2(100) not null,
flow_date date default SYSDATE not null,
flow_modify date,
flow_sort varchar2(30) not null,
flow_state varchar2(30) not null,
flow_no varchar2(30) not null,
constraint workflow_main_pk primary key (flow_num),
constraint workflow_main_fk1 foreign key (form_num) REFERENCES workflow_form (form_num),
constraint workflow_main_fk2 foreign key (mem_num) REFERENCES member (mem_num)
);

create table workflow_sign(
sign_num number not null,
form_num number not null,
mem_num number not null,
sign_no  varchar2(30) not null,
mem_name varchar2(30) not null,
constraint workflow_sign_pk primary key (sign_num),
constraint worklfow_sign_fk1_ foreign key (form_num) REFERENCES workflow_form (form_num),
constraint worklfow_sign_fk2_ foreign key (mem_num) REFERENCES member (mem_num)
);



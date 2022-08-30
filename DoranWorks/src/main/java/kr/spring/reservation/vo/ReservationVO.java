package kr.spring.reservation.vo;

import java.sql.Date;

public class ReservationVO {
	private int reserve_num;  //회의실 번호
	private int mem_num;    //회원번호
	private String reserve_title;  //회의제목
	private Date reserve_date;  //예약일
	private String reserve_room; //회의실명
	private int reserve_room_seat;  //수용인원
	
	public int getReserve_num() {
		return reserve_num;
	}
	public void setReserve_num(int reserve_num) {
		this.reserve_num = reserve_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getReserve_title() {
		return reserve_title;
	}
	public void setReserve_title(String reserve_title) {
		this.reserve_title = reserve_title;
	}
	public Date getReserve_date() {
		return reserve_date;
	}
	public void setReserve_date(Date reserve_date) {
		this.reserve_date = reserve_date;
	}
	public String getReserve_room() {
		return reserve_room;
	}
	public void setReserve_room(String reserve_room) {
		this.reserve_room = reserve_room;
	}
	public int getReserve_room_seat() {
		return reserve_room_seat;
	}
	public void setReserve_room_seat(int reserve_room_seat) {
		this.reserve_room_seat = reserve_room_seat;
	}
	
	@Override
	public String toString() {
		return "ReservationVO [reserve_num=" + reserve_num + ", mem_num=" + mem_num + ", reserve_title=" + reserve_title
				+ ", reserve_date=" + reserve_date + ", reserve_room=" + reserve_room + ", reserve_room_seat="
				+ reserve_room_seat + "]";
	}

}

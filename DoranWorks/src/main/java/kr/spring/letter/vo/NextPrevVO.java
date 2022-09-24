package kr.spring.letter.vo;

import java.sql.Date;

public class NextPrevVO {
	private String prev_num;
	private String prev_title;
	private String prev_send_id;
	private String prev_receiver_id;
	private String prev_mem_name;
	private Date prev_date;
	private int prev_read;
	private String next_num;
	private String next_title;
	private String next_send_id;
	private String next_receiver_id;
	private String next_mem_name;
	private Date next_date;
	private int next_read;
	
	
	public String getPrev_num() {
		return prev_num;
	}
	public void setPrev_num(String prev_num) {
		this.prev_num = prev_num;
	}
	public String getPrev_title() {
		return prev_title;
	}
	public void setPrev_title(String prev_title) {
		this.prev_title = prev_title;
	}
	public String getPrev_send_id() {
		return prev_send_id;
	}
	public void setPrev_send_id(String prev_send_id) {
		this.prev_send_id = prev_send_id;
	}
	public String getPrev_receiver_id() {
		return prev_receiver_id;
	}
	public void setPrev_receiver_id(String prev_receiver_id) {
		this.prev_receiver_id = prev_receiver_id;
	}
	public String getNext_num() {
		return next_num;
	}
	public void setNext_num(String next_num) {
		this.next_num = next_num;
	}
	public String getNext_title() {
		return next_title;
	}
	public void setNext_title(String next_title) {
		this.next_title = next_title;
	}
	public String getNext_send_id() {
		return next_send_id;
	}
	public void setNext_send_id(String next_send_id) {
		this.next_send_id = next_send_id;
	}
	public String getNext_receiver_id() {
		return next_receiver_id;
	}
	public void setNext_receiver_id(String next_receiver_id) {
		this.next_receiver_id = next_receiver_id;
	}
	
	
	public String getPrev_mem_name() {
		return prev_mem_name;
	}
	public void setPrev_mem_name(String prev_mem_name) {
		this.prev_mem_name = prev_mem_name;
	}
	public String getNext_mem_name() {
		return next_mem_name;
	}
	public void setNext_mem_name(String next_mem_name) {
		this.next_mem_name = next_mem_name;
	}
	
	
	
	public Date getPrev_date() {
		return prev_date;
	}
	public void setPrev_date(Date prev_date) {
		this.prev_date = prev_date;
	}
	public int getPrev_read() {
		return prev_read;
	}
	public void setPrev_read(int prev_read) {
		this.prev_read = prev_read;
	}
	public Date getNext_date() {
		return next_date;
	}
	public void setNext_date(Date next_date) {
		this.next_date = next_date;
	}
	public int getNext_read() {
		return next_read;
	}
	public void setNext_read(int next_read) {
		this.next_read = next_read;
	}
	
	@Override
	public String toString() {
		return "NextPrevVO [prev_num=" + prev_num + ", prev_title=" + prev_title + ", prev_send_id=" + prev_send_id
				+ ", prev_receiver_id=" + prev_receiver_id + ", prev_mem_name=" + prev_mem_name + ", prev_date="
				+ prev_date + ", prev_read=" + prev_read + ", next_num=" + next_num + ", next_title=" + next_title
				+ ", next_send_id=" + next_send_id + ", next_receiver_id=" + next_receiver_id + ", next_mem_name="
				+ next_mem_name + ", next_date=" + next_date + ", next_read=" + next_read + "]";
	}

	
	
	
	
	
}

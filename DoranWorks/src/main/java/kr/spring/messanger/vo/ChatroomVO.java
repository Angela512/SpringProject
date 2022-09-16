package kr.spring.messanger.vo;

import java.sql.Date;
import java.util.Arrays;

public class ChatroomVO {
	private int chatroom_num;
	private String chatroom_name;
	
	private String mem_name;
	private Date msg_sendtime;
	private byte[] mem_photo;
	private String mem_photo_name;
	private int msg_num;
	private String msg_content;
	
	private int[] members;
	
	public int getMsg_num() {
		return msg_num;
	}
	public void setMsg_num(int msg_num) {
		this.msg_num = msg_num;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public byte[] getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(byte[] mem_photo) {
		this.mem_photo = mem_photo;
	}
	public String getMem_photo_name() {
		return mem_photo_name;
	}
	public void setMem_photo_name(String mem_photo_name) {
		this.mem_photo_name = mem_photo_name;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public Date getMsg_sendtime() {
		return msg_sendtime;
	}
	public void setMsg_sendtime(Date msg_sendtime) {
		this.msg_sendtime = msg_sendtime;
	}
	public int getChatroom_num() {
		return chatroom_num;
	}
	public void setChatroom_num(int chatroom_num) {
		this.chatroom_num = chatroom_num;
	}
	public String getChatroom_name() {
		return chatroom_name;
	}
	public void setChatroom_name(String chatroom_name) {
		this.chatroom_name = chatroom_name;
	}
	public int[] getMembers() {
		return members;
	}
	public void setMembers(int[] members) {
		this.members = members;
	}
	@Override
	public String toString() {
		return "ChatroomVO [chatroom_num=" + chatroom_num + ", chatroom_name=" + chatroom_name + ", mem_name="
				+ mem_name + ", msg_sendtime=" + msg_sendtime + ", mem_photo_name=" + mem_photo_name + "]";
	}
	
}

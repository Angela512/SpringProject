package kr.spring.messanger.vo;

public class ChatreadVO {
	private int chatread_num;
	private int msg_num;
	private int mem_num;
	private int chatroom_num;
	
	public int getChatroom_num() {
		return chatroom_num;
	}
	public void setChatroom_num(int chatroom_num) {
		this.chatroom_num = chatroom_num;
	}
	public int getChatread_num() {
		return chatread_num;
	}
	public void setChatread_num(int chatread_num) {
		this.chatread_num = chatread_num;
	}
	public int getMsg_num() {
		return msg_num;
	}
	public void setMsg_num(int msg_num) {
		this.msg_num = msg_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	@Override
	public String toString() {
		return "ChatreadVO [chatread_num=" + chatread_num + ", msg_num=" + msg_num + ", mem_num=" + mem_num + "]";
	}
	
}

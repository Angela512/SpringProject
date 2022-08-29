package kr.spring.messanger.vo;

public class ChatmemVO {
	private int chatmem_num;
	private int chatroom_num;
	private int mem_num;
	
	public int getChatmem_num() {
		return chatmem_num;
	}
	public void setChatmem_num(int chatmem_num) {
		this.chatmem_num = chatmem_num;
	}
	public int getChatroom_num() {
		return chatroom_num;
	}
	public void setChatroom_num(int chatroom_num) {
		this.chatroom_num = chatroom_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	@Override
	public String toString() {
		return "ChatmemVO [chatmem_num=" + chatmem_num + ", chatroom_num=" + chatroom_num + ", mem_num=" + mem_num
				+ "]";
	}
	
}

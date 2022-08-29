package kr.spring.messanger.vo;

public class ChatroomVO {
	private int chatroom_num;
	private String chatroom_name;
	
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
	@Override
	public String toString() {
		return "ChatroomVO [chatroom_num=" + chatroom_num + ", chatroom_name=" + chatroom_name + "]";
	}
}

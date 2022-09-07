package kr.spring.messanger.vo;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class ChatmemVO {
	private int chatroom_num;
	private int mem_num;
	
	private String chatroom_name;
	private String mem_name;
	private byte[] mem_photo;
	private String mem_photo_name;
	private String mem_dpt;
	private String mem_rank;
	
	
	//=======================이미지 BLOB 처리=========================//
	//(주의)폼에서 파일 업로드 파라미터네임은 반드시 upload로 지정해야 함
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[]
		setMem_photo(upload.getBytes());
		//파일이름
		setMem_photo_name(upload.getOriginalFilename());
	}
	
	public String getChatroom_name() {
		return chatroom_name;
	}
	public void setChatroom_name(String chatroom_name) {
		this.chatroom_name = chatroom_name;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
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
	public String getMem_dpt() {
		return mem_dpt;
	}
	public void setMem_dpt(String mem_dpt) {
		this.mem_dpt = mem_dpt;
	}
	public String getMem_rank() {
		return mem_rank;
	}
	public void setMem_rank(String mem_rank) {
		this.mem_rank = mem_rank;
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
		return "ChatmemVO [chatroom_num=" + chatroom_num + ", mem_num=" + mem_num + ", chatroom_name=" + chatroom_name
				+ ", mem_name=" + mem_name + ", mem_photo_name=" + mem_photo_name + ", mem_dpt=" + mem_dpt
				+ ", mem_rank=" + mem_rank + "]";
	}
	

	
}

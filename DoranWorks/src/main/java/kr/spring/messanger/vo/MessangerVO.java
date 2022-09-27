package kr.spring.messanger.vo;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class MessangerVO {
	private int msg_num;
	private int mem_num;
	private int chatroom_num;
	private String msg_content;
	private String msg_sendtime; //2022-09-09 17:12
	private String msg_opentime;
	private String msg_filename;
	private byte[] msg_uploadfile;
	
	private String mem_name;
	private int total_cnt;
	private byte[] mem_photo;
	private String mem_photo_name;
	
	//이미지 BLOB 처리
	//(주의)폼에서 파일 업로드 파라미터네임은 반드시 upload로 지정해야 함
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[]
		setMsg_uploadfile(upload.getBytes());
		//파일이름
		setMsg_filename(upload.getOriginalFilename());
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
	public int getChatroom_num() {
		return chatroom_num;
	}
	public void setChatroom_num(int chatroom_num) {
		this.chatroom_num = chatroom_num;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	
	public String getMsg_sendtime() {
		return msg_sendtime;
	}

	public void setMsg_sendtime(String msg_sendtime) {
		this.msg_sendtime = msg_sendtime;
	}

	public String getMsg_opentime() {
		return msg_opentime;
	}

	public void setMsg_opentime(String msg_opentime) {
		this.msg_opentime = msg_opentime;
	}

	public String getMsg_filename() {
		return msg_filename;
	}
	public void setMsg_filename(String msg_filename) {
		this.msg_filename = msg_filename;
	}
	public byte[] getMsg_uploadfile() {
		return msg_uploadfile;
	}
	public void setMsg_uploadfile(byte[] msg_uploadfile) {
		this.msg_uploadfile = msg_uploadfile;
	}
	
	public int getTotal_cnt() {
		return total_cnt;
	}

	public void setTotal_cnt(int total_cnt) {
		this.total_cnt = total_cnt;
	}

	@Override
	public String toString() {
		return "MessangerVO [msg_num=" + msg_num + ", mem_num=" + mem_num + ", chatroom_num=" + chatroom_num
				+ ", msg_content=" + msg_content + ", msg_sendtime=" + msg_sendtime + ", msg_opentime=" + msg_opentime
				+ ", msg_filename=" + msg_filename + "]";
	}
	
	
}

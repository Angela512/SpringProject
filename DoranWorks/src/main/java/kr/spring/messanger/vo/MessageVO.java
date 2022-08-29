package kr.spring.messanger.vo;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class MessageVO {
	private int msg_num;
	private int mem_num;
	private int chatroom_num;
	private String msg_content;
	private Date msg_sendTime;
	private Date msg_openTime;
	private String msg_filename;
	private byte[] msg_uploadfile;
	private int msg_count;
	
	//이미지 BLOB 처리
	//(주의)폼에서 파일 업로드 파라미터네임은 반드시 upload로 지정해야 함
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[]
		setMsg_uploadfile(upload.getBytes());
		//파일이름
		setMsg_filename(upload.getOriginalFilename());
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
	public Date getMsg_sendTime() {
		return msg_sendTime;
	}
	public void setMsg_sendTime(Date msg_sendTime) {
		this.msg_sendTime = msg_sendTime;
	}
	public Date getMsg_openTime() {
		return msg_openTime;
	}
	public void setMsg_openTime(Date msg_openTime) {
		this.msg_openTime = msg_openTime;
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
	public int getMsg_count() {
		return msg_count;
	}
	public void setMsg_count(int msg_count) {
		this.msg_count = msg_count;
	}
	@Override
	public String toString() {
		return "MessageVO [msg_num=" + msg_num + ", mem_num=" + mem_num + ", chatroom_num=" + chatroom_num
				+ ", msg_content=" + msg_content + ", msg_sendTime=" + msg_sendTime + ", msg_openTime=" + msg_openTime
				+ ", msg_filename=" + msg_filename + ", msg_count=" + msg_count + "]";
	}
	
	
}

package kr.spring.notice.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class NoticeVO {
	private int notice_num; //공지글번호
	private int mem_num; //회원번호
	@NotEmpty
	private String notice_title; // 공지제목
	private Date notice_date; // 등록일
	@NotEmpty
	private String notice_content; // 공지내용
	private byte[] notice_uploadfile1;
	private byte[] notice_uploadfile2;
	private String notice_filename1;
	private String notice_filename2;
	private int notice_head; // 말머리
	private int notice_replyagree;
	
	private String mem_id;//회원아이디
	private String mem_name;//회원이름
	private byte[] mem_photo; // 프로필 사진
	private String mem_photo_name;//프로필 사진 이름
	
	private int next_num; //다음글번호
	private int prev_num; //이전글번호
	
	//=======================이미지 BLOB 처리=========================//
		//(주의)폼에서 파일 업로드 파라미터네임은 반드시 upload로 지정해야 함
		public void setUpload1(MultipartFile upload1)throws IOException{
			//MultipartFile -> byte[]
			setNotice_uploadfile1(upload1.getBytes());
			//파일이름
			setNotice_filename1(upload1.getOriginalFilename());
		}

	//=======================이미지 BLOB 처리=========================//
	//(주의)폼에서 파일 업로드 파라미터네임은 반드시 upload로 지정해야 함
	public void setUpload2(MultipartFile upload2)throws IOException{
		//MultipartFile -> byte[]
		setNotice_uploadfile2(upload2.getBytes());
		//파일이름
		setNotice_filename2(upload2.getOriginalFilename());
	}
	
	public int getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public Date getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public byte[] getNotice_uploadfile1() {
		return notice_uploadfile1;
	}
	public void setNotice_uploadfile1(byte[] notice_uploadfile1) {
		this.notice_uploadfile1 = notice_uploadfile1;
	}
	public byte[] getNotice_uploadfile2() {
		return notice_uploadfile2;
	}
	public void setNotice_uploadfile2(byte[] notice_uploadfile2) {
		this.notice_uploadfile2 = notice_uploadfile2;
	}
	public String getNotice_filename1() {
		return notice_filename1;
	}
	public void setNotice_filename1(String notice_filename1) {
		this.notice_filename1 = notice_filename1;
	}
	public String getNotice_filename2() {
		return notice_filename2;
	}
	public void setNotice_filename2(String notice_filename2) {
		this.notice_filename2 = notice_filename2;
	}
	public int getNotice_head() {
		return notice_head;
	}
	public void setNotice_head(int notice_head) {
		this.notice_head = notice_head;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
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
	
	
	public int getNext_num() {
		return next_num;
	}

	public void setNext_num(int next_num) {
		this.next_num = next_num;
	}

	public int getPrev_num() {
		return prev_num;
	}

	public void setPrev_num(int prev_num) {
		this.prev_num = prev_num;
	}
	
	

	public String getMem_photo_name() {
		return mem_photo_name;
	}

	public void setMem_photo_name(String mem_photo_name) {
		this.mem_photo_name = mem_photo_name;
	}
	
	

	public int getNotice_replyagree() {
		return notice_replyagree;
	}

	public void setNotice_replyagree(int notice_replyagree) {
		this.notice_replyagree = notice_replyagree;
	}

	@Override
	public String toString() {
		return "NoticeVO [notice_num=" + notice_num + ", mem_num=" + mem_num + ", notice_title=" + notice_title
				+ ", notice_date=" + notice_date + ", notice_content=" + notice_content + ", notice_filename1="
				+ notice_filename1 + ", notice_filename2=" + notice_filename2 + ", notice_head=" + notice_head
				+ ", notice_replyagree=" + notice_replyagree + ", mem_id=" + mem_id + ", mem_name=" + mem_name
				+ ", mem_photo_name=" + mem_photo_name + ", next_num=" + next_num + ", prev_num=" + prev_num + "]";
	}

	
	
	
	
	
	
	
}

package kr.spring.letter.vo;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class LetterVO {
	private int lt_num;
	private String lt_title;
	private int lt_sender_num;
	private String lt_receiver_num;
	private Date lt_date;
	private String lt_content;
	private int lt_read;
	private int lt_important;
	private byte[] lt_uploadfile1;
	private byte[] lt_uploadfile2;
	private String lt_filename1;
	private String lt_filename2;
	private String lt_sender_id;
	private String lt_receiver_id;
	private String lt_reference_id;
	
	private String lt_sender_name;
	private String lt_receiver_name;
	
	private String[] rids;
	private String[] rids2;
	
	private int snum;
	
	private String mem_name;
	
	//=======================이미지 BLOB 처리=========================//
		//(주의)폼에서 파일 업로드 파라미터네임은 반드시 upload로 지정해야 함
		public void setUpload1(MultipartFile upload1)throws IOException{
			//MultipartFile -> byte[]
			setLt_uploadfile1(upload1.getBytes());
			//파일이름
			setLt_filename1(upload1.getOriginalFilename());
		}

	//=======================이미지 BLOB 처리=========================//
	//(주의)폼에서 파일 업로드 파라미터네임은 반드시 upload로 지정해야 함
	public void setUpload2(MultipartFile upload2)throws IOException{
		//MultipartFile -> byte[]
		setLt_uploadfile2(upload2.getBytes());
		//파일이름
		setLt_filename2(upload2.getOriginalFilename());
	}

	public int getLt_num() {
		return lt_num;
	}

	public void setLt_num(int lt_num) {
		this.lt_num = lt_num;
	}

	public String getLt_title() {
		return lt_title;
	}

	public void setLt_title(String lt_title) {
		this.lt_title = lt_title;
	}

	public int getLt_sender_num() {
		return lt_sender_num;
	}

	public void setLt_sender_num(int lt_sender_num) {
		this.lt_sender_num = lt_sender_num;
	}

	public String getLt_receiver_num() {
		return lt_receiver_num;
	}

	public void setLt_receiver_num(String lt_receiver_num) {
		this.lt_receiver_num = lt_receiver_num;
	}

	public Date getLt_date() {
		return lt_date;
	}

	public void setLt_date(Date lt_date) {
		this.lt_date = lt_date;
	}

	public String getLt_content() {
		return lt_content;
	}

	public void setLt_content(String lt_content) {
		this.lt_content = lt_content;
	}

	public int getLt_read() {
		return lt_read;
	}

	public void setLt_read(int lt_read) {
		this.lt_read = lt_read;
	}

	public int getLt_important() {
		return lt_important;
	}

	public void setLt_important(int lt_important) {
		this.lt_important = lt_important;
	}

	public byte[] getLt_uploadfile1() {
		return lt_uploadfile1;
	}

	public void setLt_uploadfile1(byte[] lt_uploadfile1) {
		this.lt_uploadfile1 = lt_uploadfile1;
	}

	public byte[] getLt_uploadfile2() {
		return lt_uploadfile2;
	}

	public void setLt_uploadfile2(byte[] lt_uploadfile2) {
		this.lt_uploadfile2 = lt_uploadfile2;
	}

	public String getLt_filename1() {
		return lt_filename1;
	}

	public void setLt_filename1(String lt_filename1) {
		this.lt_filename1 = lt_filename1;
	}

	public String getLt_filename2() {
		return lt_filename2;
	}

	public void setLt_filename2(String lt_filename2) {
		this.lt_filename2 = lt_filename2;
	}

	public String getLt_sender_id() {
		return lt_sender_id;
	}

	public void setLt_sender_id(String lt_sender_id) {
		this.lt_sender_id = lt_sender_id;
	}

	public String getLt_receiver_id() {
		return lt_receiver_id;
	}

	public void setLt_receiver_id(String lt_receiver_id) {
		this.lt_receiver_id = lt_receiver_id;
	}
	
	
	
	
	
	

	public String[] getRids2() {
		return rids2;
	}

	public void setRids2(String[] rids2) {
		this.rids2 = rids2;
	}

	public String getLt_reference_id() {
		return lt_reference_id;
	}

	public void setLt_reference_id(String lt_reference_id) {
		this.lt_reference_id = lt_reference_id;
	}

	public int getSnum() {
		return snum;
	}

	public void setSnum(int snum) {
		this.snum = snum;
	}

	public String getLt_sender_name() {
		return lt_sender_name;
	}

	public void setLt_sender_name(String lt_sender_name) {
		this.lt_sender_name = lt_sender_name;
	}

	public String getLt_receiver_name() {
		return lt_receiver_name;
	}

	public void setLt_receiver_name(String lt_receiver_name) {
		this.lt_receiver_name = lt_receiver_name;
	}

	public String[] getRids() {
		return rids;
	}

	public void setRids(String[] rids) {
		this.rids = rids;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	@Override
	public String toString() {
		return "LetterVO [lt_num=" + lt_num + ", lt_title=" + lt_title + ", lt_sender_num=" + lt_sender_num
				+ ", lt_receiver_num=" + lt_receiver_num + ", lt_date=" + lt_date + ", lt_content=" + lt_content
				+ ", lt_read=" + lt_read + ", lt_important=" + lt_important + ", lt_filename1=" + lt_filename1
				+ ", lt_filename2=" + lt_filename2 + ", lt_sender_id=" + lt_sender_id + ", lt_receiver_id="
				+ lt_receiver_id + ", lt_reference_id=" + lt_reference_id + ", lt_sender_name=" + lt_sender_name
				+ ", lt_receiver_name=" + lt_receiver_name + ", rids=" + Arrays.toString(rids) + ", rids2="
				+ Arrays.toString(rids2) + ", snum=" + snum + ", mem_name=" + mem_name + "]";
	}

	
	
	
	
	
	
		
		
		
		

		

		
		
		
		
	
}

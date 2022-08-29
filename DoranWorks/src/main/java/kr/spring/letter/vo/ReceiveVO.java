package kr.spring.letter.vo;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class ReceiveVO {
	private int rec_num;
	private String rec_title;
	private int rec_sender_num;
	private int rec_receiver_num;
	private int rec_reference_num;
	private Date rec_date;
	private String rec_content;
	private int rec_read;
	private int rec_important;
	private byte[] rec_uploadfile1;
	private byte[] rec_uploadfile2;
	private String rec_filename1;
	private String rec_filename2;
	private String rec_sender_id;
	private String rec_receiver_id;
	private String rec_reference_id;
	
	
	//=======================이미지 BLOB 처리=========================//
	//(주의)폼에서 파일 업로드 파라미터네임은 반드시 upload로 지정해야 함
	public void setUpload1(MultipartFile upload1)throws IOException{
		//MultipartFile -> byte[]
		setRec_uploadfile1(upload1.getBytes());
		//파일이름
		setRec_filename1(upload1.getOriginalFilename());
	}

	//=======================이미지 BLOB 처리=========================//
	//(주의)폼에서 파일 업로드 파라미터네임은 반드시 upload로 지정해야 함
	public void setUpload2(MultipartFile upload2)throws IOException{
		//MultipartFile -> byte[]
		setRec_uploadfile2(upload2.getBytes());
		//파일이름
		setRec_filename2(upload2.getOriginalFilename());
	}
		
	public int getRec_num() {
		return rec_num;
	}
	public void setRec_num(int rec_num) {
		this.rec_num = rec_num;
	}
	public String getRec_title() {
		return rec_title;
	}
	public void setRec_title(String rec_title) {
		this.rec_title = rec_title;
	}
	public int getRec_sender_num() {
		return rec_sender_num;
	}
	public void setRec_sender_num(int rec_sender_num) {
		this.rec_sender_num = rec_sender_num;
	}
	public int getRec_receiver_num() {
		return rec_receiver_num;
	}
	public void setRec_receiver_num(int rec_receiver_num) {
		this.rec_receiver_num = rec_receiver_num;
	}
	public int getRec_reference_num() {
		return rec_reference_num;
	}
	public void setRec_reference_num(int rec_reference_num) {
		this.rec_reference_num = rec_reference_num;
	}
	public Date getRec_date() {
		return rec_date;
	}
	public void setRec_date(Date rec_date) {
		this.rec_date = rec_date;
	}
	public String getRec_content() {
		return rec_content;
	}
	public void setRec_content(String rec_content) {
		this.rec_content = rec_content;
	}
	public int getRec_read() {
		return rec_read;
	}
	public void setRec_read(int rec_read) {
		this.rec_read = rec_read;
	}
	public int getRec_important() {
		return rec_important;
	}
	public void setRec_important(int rec_important) {
		this.rec_important = rec_important;
	}
	public byte[] getRec_uploadfile1() {
		return rec_uploadfile1;
	}
	public void setRec_uploadfile1(byte[] rec_uploadfile1) {
		this.rec_uploadfile1 = rec_uploadfile1;
	}
	public byte[] getRec_uploadfile2() {
		return rec_uploadfile2;
	}
	public void setRec_uploadfile2(byte[] rec_uploadfile2) {
		this.rec_uploadfile2 = rec_uploadfile2;
	}
	public String getRec_filename1() {
		return rec_filename1;
	}
	public void setRec_filename1(String rec_filename1) {
		this.rec_filename1 = rec_filename1;
	}
	public String getRec_filename2() {
		return rec_filename2;
	}
	public void setRec_filename2(String rec_filename2) {
		this.rec_filename2 = rec_filename2;
	}
	public String getRec_sender_id() {
		return rec_sender_id;
	}
	public void setRec_sender_id(String rec_sender_id) {
		this.rec_sender_id = rec_sender_id;
	}
	public String getRec_receiver_id() {
		return rec_receiver_id;
	}
	public void setRec_receiver_id(String rec_receiver_id) {
		this.rec_receiver_id = rec_receiver_id;
	}
	public String getRec_reference_id() {
		return rec_reference_id;
	}
	public void setRec_reference_id(String rec_reference_id) {
		this.rec_reference_id = rec_reference_id;
	}
	@Override
	public String toString() {
		return "ReceiveVO [rec_num=" + rec_num + ", rec_title=" + rec_title + ", rec_sender_num=" + rec_sender_num
				+ ", rec_receiver_num=" + rec_receiver_num + ", rec_reference_num=" + rec_reference_num + ", rec_date="
				+ rec_date + ", rec_content=" + rec_content + ", rec_read=" + rec_read + ", rec_important="
				+ rec_important + ", rec_filename1=" + rec_filename1 + ", rec_filename2=" + rec_filename2
				+ ", rec_sender_id=" + rec_sender_id + ", rec_receiver_id=" + rec_receiver_id + ", rec_reference_id="
				+ rec_reference_id + "]";
	}
	
	
}

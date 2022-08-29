package kr.spring.letter.vo;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class SendVO {
	private int send_num;
	private String send_title;
	private int send_sender_num;
	private int send_receiver_num;
	private int send_reference_num;
	private Date send_date;
	private String send_content;
	private int send_read;
	private int send_important;
	private byte[] send_uploadfile1;
	private byte[] send_uploadfile2;
	private String send_filename1;
	private String send_filename2;
	private String send_sender_id;
	private String send_receiver_id;
	private String send_reference_id;
	
	//=======================이미지 BLOB 처리=========================//
		//(주의)폼에서 파일 업로드 파라미터네임은 반드시 upload로 지정해야 함
		public void setUpload1(MultipartFile upload1)throws IOException{
			//MultipartFile -> byte[]
			setSend_uploadfile1(upload1.getBytes());
			//파일이름
			setSend_filename1(upload1.getOriginalFilename());
		}

	//=======================이미지 BLOB 처리=========================//
	//(주의)폼에서 파일 업로드 파라미터네임은 반드시 upload로 지정해야 함
	public void setUpload2(MultipartFile upload2)throws IOException{
		//MultipartFile -> byte[]
		setSend_uploadfile2(upload2.getBytes());
		//파일이름
		setSend_filename2(upload2.getOriginalFilename());
	}

		public int getSend_num() {
			return send_num;
		}

		public void setSend_num(int send_num) {
			this.send_num = send_num;
		}

		public String getSend_title() {
			return send_title;
		}

		public void setSend_title(String send_title) {
			this.send_title = send_title;
		}

		public int getSend_sender_num() {
			return send_sender_num;
		}

		public void setSend_sender_num(int send_sender_num) {
			this.send_sender_num = send_sender_num;
		}

		public int getSend_receiver_num() {
			return send_receiver_num;
		}

		public void setSend_receiver_num(int send_receiver_num) {
			this.send_receiver_num = send_receiver_num;
		}

		public int getSend_reference_num() {
			return send_reference_num;
		}

		public void setSend_reference_num(int send_reference_num) {
			this.send_reference_num = send_reference_num;
		}

		public Date getSend_date() {
			return send_date;
		}

		public void setSend_date(Date send_date) {
			this.send_date = send_date;
		}

		public String getSend_content() {
			return send_content;
		}

		public void setSend_content(String send_content) {
			this.send_content = send_content;
		}

		public int getSend_read() {
			return send_read;
		}

		public void setSend_read(int send_read) {
			this.send_read = send_read;
		}

		public int getSend_important() {
			return send_important;
		}

		public void setSend_important(int send_important) {
			this.send_important = send_important;
		}

		public byte[] getSend_uploadfile1() {
			return send_uploadfile1;
		}

		public void setSend_uploadfile1(byte[] send_uploadfile1) {
			this.send_uploadfile1 = send_uploadfile1;
		}

		public byte[] getSend_uploadfile2() {
			return send_uploadfile2;
		}

		public void setSend_uploadfile2(byte[] send_uploadfile2) {
			this.send_uploadfile2 = send_uploadfile2;
		}

		public String getSend_filename1() {
			return send_filename1;
		}

		public void setSend_filename1(String send_filename1) {
			this.send_filename1 = send_filename1;
		}

		public String getSend_filename2() {
			return send_filename2;
		}

		public void setSend_filename2(String send_filename2) {
			this.send_filename2 = send_filename2;
		}

		public String getSend_sender_id() {
			return send_sender_id;
		}

		public void setSend_sender_id(String send_sender_id) {
			this.send_sender_id = send_sender_id;
		}

		public String getSend_receiver_id() {
			return send_receiver_id;
		}

		public void setSend_receiver_id(String send_receiver_id) {
			this.send_receiver_id = send_receiver_id;
		}

		public String getSend_reference_id() {
			return send_reference_id;
		}

		public void setSend_reference_id(String send_reference_id) {
			this.send_reference_id = send_reference_id;
		}

		@Override
		public String toString() {
			return "SendVO [send_num=" + send_num + ", send_title=" + send_title + ", send_sender_num="
					+ send_sender_num + ", send_receiver_num=" + send_receiver_num + ", send_reference_num="
					+ send_reference_num + ", send_date=" + send_date + ", send_content=" + send_content
					+ ", send_read=" + send_read + ", send_important=" + send_important + ", send_filename1="
					+ send_filename1 + ", send_filename2=" + send_filename2 + ", send_sender_id=" + send_sender_id
					+ ", send_receiver_id=" + send_receiver_id + ", send_reference_id=" + send_reference_id + "]";
		}
		
		
		
	
}

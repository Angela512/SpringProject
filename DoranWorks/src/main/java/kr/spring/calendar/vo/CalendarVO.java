package kr.spring.calendar.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class CalendarVO {

	private int cal_num;
	private int mem_num;
	private String cal_name;
	private String cal_date;
	private String cal_member;
	private String cal_location;
	private String cal_memo;
	private String cal_filename;
	private String cal_open;
	private byte[] cal_uploadfile;

	
	//========이미지 BLOB 처리===============//
	//(주의)폼에서 파일업로드 파라미터네임은 반드시 upload로
	//지정해야 함
	public void setUpload(MultipartFile upload)
	                              throws IOException{
		//MultipartFile -> byte[]
		setCal_uploadfile(upload.getBytes());
	}
	
	public int getCal_num() {
		return cal_num;
	}
	public void setCal_num(int cal_num) {
		this.cal_num = cal_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getCal_name() {
		return cal_name;
	}
	public void setCal_name(String cal_name) {
		this.cal_name = cal_name;
	}
	public String getCal_date() {
		return cal_date;
	}
	public void setCal_date(String cal_date) {
		this.cal_date = cal_date;
	}
	public String getCal_member() {
		return cal_member;
	}
	public void setCal_member(String cal_member) {
		this.cal_member = cal_member;
	}
	public String getCal_location() {
		return cal_location;
	}
	public void setCal_location(String cal_location) {
		this.cal_location = cal_location;
	}
	public String getCal_memo() {
		return cal_memo;
	}
	public void setCal_memo(String cal_memo) {
		this.cal_memo = cal_memo;
	}
	public String getCal_filename() {
		return cal_filename;
	}
	public void setCal_filename(String cal_filename) {
		this.cal_filename = cal_filename;
	}

	public byte[] getCal_uploadfile() {
		return cal_uploadfile;
	}

	public void setCal_uploadfile(byte[] cal_uploadfile) {
		this.cal_uploadfile = cal_uploadfile;
	}
	
	public String getCal_open() {
		return cal_open;
	}
	public void setCal_open(String cal_open) {
		this.cal_open = cal_open;
	}
	
	@Override
	public String toString() {
		return "CalendarVO [calendar_num=" + cal_num + ", mem_num=" + mem_num + ", cal_name=" + cal_name + ", cal_date=" + cal_date
				+ ", cal_member =" + cal_member + ", cal_location =" + cal_location + ", cal_memo=" + cal_memo + ", cal_filename=" + cal_filename
				+ ", cal_uploadfile=" + cal_uploadfile+ ", cal_open=" + cal_open + "]";
	}
	

}

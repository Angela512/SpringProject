package kr.spring.member.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
	private int mem_num;
	@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	private String mem_id;
	private int auth;
	@NotEmpty
	private String mem_name;
	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,15}$")
	private String mem_pw;
	@Pattern(regexp="^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})$")
	private String mem_phone;
	@Email
	@NotEmpty
	private String mem_email;
	@Size(min=5,max=5)
	private String mem_zipcode;
	@NotEmpty
	private String mem_addr1;
	@NotEmpty
	private String mem_addr2;
	private Date mem_date;
	private Date mem_modify_date;
	private int mem_dpt_num;
	private int mem_rank_num;
	private Date mem_birthdate;
	private byte[] mem_photo;
	private String mem_photo_name;
	private String mem_type;
	
	
	//=================================비밀번호 일치 여부 체크===================//
	public boolean isCheckedPasswd(String userPasswd) {
		System.out.println("널2 : " + mem_pw);
		if(auth>0 && mem_pw.equals(userPasswd)) {
			return true;
		}
		return false;
	}
	
	
	//=======================이미지 BLOB 처리=========================//
	//(주의)폼에서 파일 업로드 파라미터네임은 반드시 upload로 지정해야 함
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[]
		setMem_photo(upload.getBytes());
		//파일이름
		setMem_photo_name(upload.getOriginalFilename());
	}
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_zipcode() {
		return mem_zipcode;
	}
	public void setMem_zipcode(String mem_zipcode) {
		this.mem_zipcode = mem_zipcode;
	}
	public String getMem_addr1() {
		return mem_addr1;
	}
	public void setMem_addr1(String mem_addr1) {
		this.mem_addr1 = mem_addr1;
	}
	public String getMem_addr2() {
		return mem_addr2;
	}
	public void setMem_addr2(String mem_addr2) {
		this.mem_addr2 = mem_addr2;
	}
	public Date getMem_date() {
		return mem_date;
	}
	public void setMem_date(Date mem_date) {
		this.mem_date = mem_date;
	}
	public Date getMem_modify_date() {
		return mem_modify_date;
	}
	public void setMem_modify_date(Date mem_modify_date) {
		this.mem_modify_date = mem_modify_date;
	}
	
	
	
	public int getMem_dpt_num() {
		return mem_dpt_num;
	}


	public void setMem_dpt_num(int mem_dpt_num) {
		this.mem_dpt_num = mem_dpt_num;
	}


	public int getMem_rank_num() {
		return mem_rank_num;
	}


	public void setMem_rank_num(int mem_rank_num) {
		this.mem_rank_num = mem_rank_num;
	}


	public Date getMem_birthdate() {
		return mem_birthdate;
	}
	public void setMem_birthdate(Date mem_birthdate) {
		this.mem_birthdate = mem_birthdate;
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

	public String getMem_type() {
		return mem_type;
	}
	public void setMem_type(String mem_type) {
		this.mem_type = mem_type;
	}


	@Override
	public String toString() {
		return "MemberVO [mem_num=" + mem_num + ", mem_id=" + mem_id + ", auth=" + auth + ", mem_name=" + mem_name
				+ ", mem_pw=" + mem_pw + ", mem_phone=" + mem_phone + ", mem_email=" + mem_email + ", mem_zipcode="
				+ mem_zipcode + ", mem_addr1=" + mem_addr1 + ", mem_addr2=" + mem_addr2 + ", mem_date=" + mem_date
				+ ", mem_modify_date=" + mem_modify_date + ", mem_dpt_num=" + mem_dpt_num + ", mem_rank_num="
				+ mem_rank_num + ", mem_birthdate=" + mem_birthdate + ", mem_photo_name=" + mem_photo_name
				+ ", mem_type=" + mem_type + "]";
	}
	
	
	
	
}

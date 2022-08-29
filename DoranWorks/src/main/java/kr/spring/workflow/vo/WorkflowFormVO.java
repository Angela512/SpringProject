package kr.spring.workflow.vo;

import java.sql.Date;

public class WorkflowFormVO {

	private int form_num;
	private int mem_num;
	private Date form_date;
	private Date form_modify_date;
	private String form_sort;
	private String form_uploadfile;
	private String form_no;
	private String form_start;
	private String form_end;
	private String form_reason;
	
	
	public int getForm_num() {
		return form_num;
	}
	public void setForm_num(int form_num) {
		this.form_num = form_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public Date getForm_date() {
		return form_date;
	}
	public void setForm_date(Date form_date) {
		this.form_date = form_date;
	}
	public Date getForm_modify_date() {
		return form_modify_date;
	}
	public void setForm_modify_date(Date form_modify_date) {
		this.form_modify_date = form_modify_date;
	}
	public String getForm_sort() {
		return form_sort;
	}
	public void setForm_sort(String form_sort) {
		this.form_sort = form_sort;
	}
	public String getForm_uploadfile() {
		return form_uploadfile;
	}
	public void setForm_uploadfile(String form_uploadfile) {
		this.form_uploadfile = form_uploadfile;
	}
	public String getForm_no() {
		return form_no;
	}
	public void setForm_no(String form_no) {
		this.form_no = form_no;
	}
	public String getForm_start() {
		return form_start;
	}
	public void setForm_start(String form_start) {
		this.form_start = form_start;
	}
	public String getForm_end() {
		return form_end;
	}
	public void setForm_end(String form_end) {
		this.form_end = form_end;
	}
	public String getForm_reason() {
		return form_reason;
	}
	public void setForm_reason(String form_reason) {
		this.form_reason = form_reason;
	}
	
	
	
}

package kr.spring.workflow.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class WorkflowVO {
	
	private int flow_num;
	private int form_num;
	private int mem_num;
	@NotEmpty
	private String flow_title;
	
	private String flow_sort;
	
	private String flow_state;
	private String flow_no;
	@NotEmpty
	private String flow_content;
	
	@NotEmpty
	private String flow_start;
	@NotEmpty
	private String flow_end;
	@NotEmpty
	private String flow_subsort;
	
	private Date flow_date;
	private String flow_modify;
	
	private String flow_line;
	
	private String mem_photo_name;
	
	private int sign_num;

	
	private String sign_no;
	@NotEmpty
	private String sign_name;
	private String mem_name;
	private String mem_dpt;
	private String mem_rank;
	
	public String getFlow_line() {
		return flow_line;
	}
	public void setFlow_line(String flow_line) {
		this.flow_line = flow_line;
	}
	public int getFlow_num() {
		return flow_num;
	}
	public void setFlow_num(int flow_num) {
		this.flow_num = flow_num;
	}
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
	public String getFlow_title() {
		return flow_title;
	}
	public void setFlow_title(String flow_title) {
		this.flow_title = flow_title;
	}
	public String getFlow_sort() {
		return flow_sort;
	}
	public void setFlow_sort(String flow_sort) {
		this.flow_sort = flow_sort;
	}
	public String getFlow_state() {
		return flow_state;
	}
	public void setFlow_state(String flow_state) {
		this.flow_state = flow_state;
	}
	public String getFlow_no() {
		return flow_no;
	}
	public void setFlow_no(String flow_no) {
		this.flow_no = flow_no;
	}
	public String getFlow_content() {
		return flow_content;
	}
	public void setFlow_content(String flow_content) {
		this.flow_content = flow_content;
	}
	public String getFlow_start() {
		return flow_start;
	}
	public void setFlow_start(String flow_start) {
		this.flow_start = flow_start;
	}
	public String getFlow_end() {
		return flow_end;
	}
	public void setFlow_end(String flow_end) {
		this.flow_end = flow_end;
	}
	public String getFlow_subsort() {
		return flow_subsort;
	}
	public void setFlow_subsort(String flow_subsort) {
		this.flow_subsort = flow_subsort;
	}
	public Date getFlow_date() {
		return flow_date;
	}
	public void setFlow_date(Date flow_date) {
		this.flow_date = flow_date;
	}
	public String getFlow_modify() {
		return flow_modify;
	}
	public void setFlow_modify(String flow_modify) {
		this.flow_modify = flow_modify;
	}
	public String getMem_photo_name() {
		return mem_photo_name;
	}
	public void setMem_photo_name(String mem_photo_name) {
		this.mem_photo_name = mem_photo_name;
	}
	public int getSign_num() {
		return sign_num;
	}
	public void setSign_num(int sign_num) {
		this.sign_num = sign_num;
	}
	public String getSign_no() {
		return sign_no;
	}
	public void setSign_no(String sign_no) {
		this.sign_no = sign_no;
	}
	public String getSign_name() {
		return sign_name;
	}
	public void setSign_name(String sign_name) {
		this.sign_name = sign_name;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
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
	
	
}

package kr.spring.workflow.vo;

import java.sql.Date;

public class WorkflowMainVO {
	
	private int flow_num;
	private int form_num;
	private int mem_num;
	private String flow_title;
	
	
	private String flow_sort;
	private String flow_state;
	private String flow_no;
	private String flow_content;
	
	private String flow_start;
	private String flow_end;
	private String flow_subsort;
	
	private Date flow_date;
	private String flow_modify;
	
	private String mem_name;
	private String mem_dpt;
	private String mem_rank;
	private String mem_photo_name;
	
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
	public String getMem_photo_name() {
		return mem_photo_name;
	}
	public void setMem_photo_name(String mem_photo_name) {
		this.mem_photo_name = mem_photo_name;
	}
	@Override
	public String toString() {
		return "WorkflowMainVO [flow_num=" + flow_num + ", form_num=" + form_num + ", mem_num=" + mem_num
				+ ", flow_title=" + flow_title + ", flow_sort=" + flow_sort + ", flow_state=" + flow_state
				+ ", flow_no=" + flow_no + ", flow_content=" + flow_content + ", flow_start=" + flow_start
				+ ", flow_end=" + flow_end + ", flow_subsort=" + flow_subsort + ", flow_date=" + flow_date
				+ ", flow_modify=" + flow_modify + ", mem_name=" + mem_name + ", mem_dpt=" + mem_dpt + ", mem_rank="
				+ mem_rank + ", mem_photo_name=" + mem_photo_name + "]";
	}
	
	
	
	
	
	
	
	
	
	
}

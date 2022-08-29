package kr.spring.workflow.vo;

import java.sql.Date;

public class WorkflowMainVO {
	
	private int flow_num;
	private int form_num;
	private int mem_num;
	private String flow_title;
	private Date flow_date;
	private Date flow_modify;
	private String flow_sort;
	private String flow_state;
	private String flow_no;
	
	
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
	public Date getFlow_date() {
		return flow_date;
	}
	public void setFlow_date(Date flow_date) {
		this.flow_date = flow_date;
	}
	public Date getFlow_modify() {
		return flow_modify;
	}
	public void setFlow_modify(Date flow_modify) {
		this.flow_modify = flow_modify;
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
	
	
	
	
}

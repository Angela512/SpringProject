package kr.spring.letter.vo;

import java.util.Arrays;

public class LetterReadVO {
	private int mem_num;
	private String[] nums;
	private int lt_read;
	private int lt_num;
	private int lt_type;
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String[] getNums() {
		return nums;
	}
	public void setNums(String[] nums) {
		this.nums = nums;
	}
	public int getLt_read() {
		return lt_read;
	}
	public void setLt_read(int lt_read) {
		this.lt_read = lt_read;
	}
	
	
	
	
	public int getLt_type() {
		return lt_type;
	}
	public void setLt_type(int lt_type) {
		this.lt_type = lt_type;
	}
	public int getLt_num() {
		return lt_num;
	}
	public void setLt_num(int lt_num) {
		this.lt_num = lt_num;
	}
	@Override
	public String toString() {
		return "LetterReadVO [mem_num=" + mem_num + ", nums=" + Arrays.toString(nums) + ", lt_read=" + lt_read
				+ ", lt_num=" + lt_num + ", lt_type=" + lt_type + "]";
	}
	
	
}

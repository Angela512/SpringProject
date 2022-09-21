package kr.spring.notice.vo;

public class NoticeReplyVO {
	private int reply_num;//댓글 번호
	private String reply_content;//내용
	private String reply_date;//등록일
	private String reply_mdate;//수정일
	private int notice_num;//부모글 번호
	private int mem_num;//회원 번호
	
	private String mem_id;//아이디
	private String mem_name;//이름
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReply_date() {
		return reply_date;
	}
	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}
	public String getReply_mdate() {
		return reply_mdate;
	}
	public void setReply_mdate(String reply_mdate) {
		this.reply_mdate = reply_mdate;
	}
	public int getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
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
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	@Override
	public String toString() {
		return "NoticeReplyVO [reply_num=" + reply_num + ", reply_content=" + reply_content + ", reply_date="
				+ reply_date + ", reply_mdate=" + reply_mdate + ", notice_num=" + notice_num + ", mem_num=" + mem_num
				+ ", mem_id=" + mem_id + ", mem_name=" + mem_name + "]";
	}
	
	
}

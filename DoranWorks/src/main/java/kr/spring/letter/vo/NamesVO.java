package kr.spring.letter.vo;

public class NamesVO {
	private String rec_name;
	private String sen_name;
	public String getRec_name() {
		return rec_name;
	}
	public void setRec_name(String rec_name) {
		this.rec_name = rec_name;
	}
	public String getSen_name() {
		return sen_name;
	}
	public void setSen_name(String sen_name) {
		this.sen_name = sen_name;
	}
	@Override
	public String toString() {
		return "NamesVO [rec_name=" + rec_name + ", sen_name=" + sen_name + "]";
	}
	
	
}

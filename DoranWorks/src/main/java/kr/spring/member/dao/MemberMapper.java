package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

@Mapper
public interface MemberMapper {
	//회원관리-일반회원
	@Select("SELECT member_seq.nextval FROM dual")
	public int selectMem_num(); 
	
	@Insert("INSERT INTO member (mem_num, mem_id) VALUES (#{mem_num}, #{mem_id})") 
	public void insertMember(MemberVO member);
	
	@Insert("INSERT INTO member_detail(mem_num, mem_name, mem_pw, mem_phone, mem_email, mem_zipcode, mem_addr1, mem_addr2, mem_dpt_num, mem_rank_num, mem_birthdate, mem_type)"
			+ " VALUES(#{mem_num}, #{mem_name}, #{mem_pw}, #{mem_phone}, #{mem_email}, #{mem_zipcode}, #{mem_addr1}, #{mem_addr2}, #{mem_dpt_num}, #{mem_rank_num}, #{mem_birthdate}, #{mem_type})")
	public void insertMember_detail(MemberVO member);
	
	@Select("SELECT * FROM member m JOIN member_detail d "
			+ "ON m.mem_num=d.mem_num "
			+" JOIN mem_rank c "
            +" ON d.mem_rank_num = c.mem_rank_num "
            +" JOIN mem_dpt p "
            +" ON d.mem_dpt_num = p.mem_dpt_num "
			+ "WHERE m.mem_num=#{mem_num}")
	public MemberVO selectMember(Integer mem_num); 
	
	@Select("SELECT m.mem_num,m.mem_id,m.auth,d.mem_pw,d.mem_name,d.mem_photo FROM member m LEFT JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_id=#{mem_id}")
	public MemberVO selectCheckMember(String mem_id);
	
	@Update("UPDATE member SET mem_name=#{mem_name} WHERE mem_num=#{mem_num}")
	public void updateMember(MemberVO member);
	
	@Update("UPDATE member_detail SET mem_name=#{mem_name},"
			+ "mem_phone=#{mem_phone},mem_email=#{mem_email},mem_zipcode=#{mem_zipcode},"
			+ "mem_addr1=#{mem_addr1},mem_addr2=#{mem_addr2},"
			+ "mem_modify_date=SYSDATE WHERE mem_num=#{mem_num}")
	public void updateMember_detail(MemberVO member);
	
	//프로필 이미지 업데이트
	@Update("UPDATE member_detail SET mem_photo=#{mem_photo}, mem_photo_name=#{mem_photo_name} WHERE mem_num=#{mem_num}")
	public void updateProfile(MemberVO member);
	
	//회원관리 - 관리자
	public List<MemberVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void updateByAdmin(MemberVO member);
	
	//메신저 회원 목록 호출
	public List<MemberVO> selectMsgMemberList(Map<String,Object> map);
	public int selectMsgMemberRowCount(Map<String,Object> map);
	
	//전자결재 회원 목록 호출
	public List<MemberVO> selectSignList();

	//회원 비밀번호 변경
	@Update("UPDATE member_detail SET mem_pw=#{mem_pw} "
			+ "WHERE mem_num=#{mem_num}")
	public void updatePassword(MemberVO member);
}

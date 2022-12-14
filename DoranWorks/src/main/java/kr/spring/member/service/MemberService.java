package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import kr.spring.member.vo.MemberVO;

public interface MemberService {
	//회원관리 - 관리자
	public void insertMember(MemberVO member);
	public MemberVO selectMember(Integer mem_num);
	public MemberVO selectCheckMember(String mem_id);
	public void updateMember(MemberVO member);
	public void updatePassword(MemberVO member);
	
	//프로필 이미지 업데이트
	public void updateProfile(MemberVO member);
	
	//회원관리 - 관리자
	public List<MemberVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void updateByAdmin(MemberVO member);
	
	public List<MemberVO> selectMsgMemberList(Map<String,Object> map);
	public int selectMsgMemberRowCount(Map<String,Object> map);
	
	//전자결재 회원 목록 호출
	public List<MemberVO> selectSignList();
	
	public void updateRandomPassword(MemberVO member);
	public void deleteMember(Integer mem_num);
}

package kr.spring.member.service;

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
}

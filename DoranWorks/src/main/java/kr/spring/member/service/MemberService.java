package kr.spring.member.service;

import kr.spring.member.vo.MemberVO;

public interface MemberService {
	//회원관리 - 관리자
	public void insertMember(MemberVO member);
	public MemberVO selectMember(Integer mem_num);
	public MemberVO selectCheckMember(String mem_id);
}

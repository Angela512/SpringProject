package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.member.dao.MemberMapper;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public MemberVO selectCheckMember(String mem_id) {
		return memberMapper.selectCheckMember(mem_id);
	}

	@Override
	public void insertMember(MemberVO member) {
		member.setMem_num(memberMapper.selectMem_num());
		memberMapper.insertMember(member);
		memberMapper.insertMember_detail(member);
		
	}

	@Override
	public MemberVO selectMember(Integer mem_num) {
		return memberMapper.selectMember(mem_num);
	}

	@Override
	public void updateMember(MemberVO member) {
//		memberMapper.updateMember(member);
		memberMapper.updateMember_detail(member);
		
	}

	@Override
	public void updatePassword(MemberVO member) {
		memberMapper.updatePassword(member);
		
	}

	@Override
	public void updateProfile(MemberVO member) {
		memberMapper.updateProfile(member);
		
	}

	@Override
	public List<MemberVO> selectList(Map<String, Object> map) {
		return memberMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return memberMapper.selectRowCount(map);
	}

	@Override
	public void updateByAdmin(MemberVO member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MemberVO> selectMsgMemberList(Map<String, Object> map) {
		return memberMapper.selectMsgMemberList(map);
	}

	@Override
	public int selectMsgMemberRowCount(Map<String, Object> map) {
		return memberMapper.selectMsgMemberRowCount(map);
	}

	//전자결재 회원 목록 호출
	@Override
	public List<MemberVO> selectSignList() {
		return memberMapper.selectSignList();
	}

	@Override
	public void updateRandomPassword(MemberVO member) {
		memberMapper.updateRandomPassword(member);
	}

	public void deleteMember(Integer mem_num) {
		memberMapper.deleteMember(mem_num);
		memberMapper.deleteMember_detail(mem_num);
	}

	
}

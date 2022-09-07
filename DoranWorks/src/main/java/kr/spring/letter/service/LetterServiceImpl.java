package kr.spring.letter.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.letter.dao.LetterMapper;
import kr.spring.letter.vo.LetterVO;
import kr.spring.letter.vo.NextPrevVO;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class LetterServiceImpl implements LetterService{

	@Autowired
	private LetterMapper letterMapper;
	

	@Override
	public MemberVO selectMem_vo(String mem_id) {
		return letterMapper.selectMem_vo(mem_id);
	}
	
	@Override
	public void insertSend(LetterVO letter) {
		String[] rids = letter.getRids();
		String[] rids2 = letter.getRids2();
		String rrecnum="";
		LetterVO sendVV = letter;
		
		for(int i=0;i<rids.length;i++) {
			MemberVO mm = letterMapper.selectMem_vo(rids[i]);
			
			if(i==rids.length-1) {
				rrecnum+=mm.getMem_num();
			}
			else {
				rrecnum+=(mm.getMem_num()+", ");
			}
		}
		int sendNum=letterMapper.sendNum();
		sendVV.setLt_num(sendNum);
		sendVV.setLt_receiver_num(rrecnum);
		System.out.println("sendVV : "+sendVV);
		letterMapper.insertSend(sendVV);
		
		
		for(int i=0;i<rids.length;i++) {
			MemberVO nn = letterMapper.selectMem_vo(rids[i]);
			
			letter.setLt_receiver_num(String.valueOf(nn.getMem_num()));
			letter.setLt_receiver_id(nn.getMem_id());
			letter.setSnum(sendNum);
			
			letterMapper.insertReceive(letter);
		}
		
		for(int i=0;i<rids2.length;i++) {
			MemberVO nn = letterMapper.selectMem_vo(rids2[i]);
			
			letter.setLt_receiver_num(String.valueOf(nn.getMem_num()));
			letter.setLt_receiver_id(nn.getMem_id());
			letter.setSnum(sendNum);
			
			letterMapper.insertReceive(letter);
		}
		
	}

	@Override
	public int selectAllRowCount(Map<String, Object> map) {
		return letterMapper.selectAllRowCount(map);
	}

	@Override
	public List<LetterVO> selectAllList(Map<String, Object> map) {
		return letterMapper.selectAllList(map);
	}

	@Override
	public LetterVO selectLetter(int lt_num) {
		return letterMapper.selectLetter(lt_num);
	}

	@Override
	public NextPrevVO selectAllNP(Map<String, Object> map) {
		return letterMapper.selectAllNP(map);
	}

	@Override
	public int selectRecRowCount(Map<String, Object> map) {
		return letterMapper.selectRecRowCount(map);
	}

	@Override
	public List<LetterVO> selectRecList(Map<String, Object> map) {
		return letterMapper.selectRecList(map);
	}

	@Override
	public NextPrevVO selectRecNP(Map<String, Object> map) {
		return letterMapper.selectRecNP(map);
	}

	@Override
	public int selectSendRowCount(Map<String, Object> map) {
		return letterMapper.selectSendRowCount(map);
	}

	@Override
	public List<LetterVO> selectSendList(Map<String, Object> map) {
		return letterMapper.selectSendList(map);
	}

	@Override
	public NextPrevVO selectSendNP(Map<String, Object> map) {
		return letterMapper.selectSendNP(map);
	}

	

}

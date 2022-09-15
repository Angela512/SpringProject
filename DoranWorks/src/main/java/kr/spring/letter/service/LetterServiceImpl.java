package kr.spring.letter.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.letter.dao.LetterMapper;
import kr.spring.letter.vo.LetterReadVO;
import kr.spring.letter.vo.LetterVO;
import kr.spring.letter.vo.NextPrevVO;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class LetterServiceImpl implements LetterService{

	@Autowired
	private LetterMapper letterMapper;
	

	//회원아이디로 멤버정보 가져오기
	@Override
	public MemberVO selectMem_vo(String mem_id) {
		return letterMapper.selectMem_vo(mem_id);
	}
	
	//쪽지쓰기
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
			
			if(nn!=null) {
				letter.setLt_receiver_num(String.valueOf(nn.getMem_num()));
				letter.setLt_receiver_id(nn.getMem_id());
				letter.setSnum(sendNum);
				
				letterMapper.insertReceive(letter);
			}
		}
		
	}

	//전체쪽지함 카운트
	@Override
	public int selectAllRowCount(Map<String, Object> map) {
		return letterMapper.selectAllRowCount(map);
	}

	//전체쪽지함 리스트
	@Override
	public List<LetterVO> selectAllList(Map<String, Object> map) {
		return letterMapper.selectAllList(map);
	}

	//전체쪽지함 이전다음글
	@Override
	public NextPrevVO selectAllNP(Map<String, Object> map) {
		return letterMapper.selectAllNP(map);
	}

	//받은쪽지함 카운트
	@Override
	public int selectRecRowCount(Map<String, Object> map) {
		return letterMapper.selectRecRowCount(map);
	}

	//받은쪽지함 리스트
	@Override
	public List<LetterVO> selectRecList(Map<String, Object> map) {
		return letterMapper.selectRecList(map);
	}

	//받은쪽지함 이전다음글
	@Override
	public NextPrevVO selectRecNP(Map<String, Object> map) {
		return letterMapper.selectRecNP(map);
	}

	//보낸쪽지함 카운트
	@Override
	public int selectSendRowCount(Map<String, Object> map) {
		return letterMapper.selectSendRowCount(map);
	}

	//보낸쪽지함 리스트
	@Override
	public List<LetterVO> selectSendList(Map<String, Object> map) {
		return letterMapper.selectSendList(map);
	}

	//보낸쪽지함 이전다음글
	@Override
	public NextPrevVO selectSendNP(Map<String, Object> map) {
		return letterMapper.selectSendNP(map);
	}

	//내게쓴쪽지함 카운트
	@Override
	public int selectMyRowCount(Map<String, Object> map) {
		return letterMapper.selectMyRowCount(map);
	}

	//내게쓴쪽지함 리스트
	@Override
	public List<LetterVO> selectMyList(Map<String, Object> map) {
		return letterMapper.selectMyList(map);
	}

	//내게쓴쪽지함 이전다음글
	@Override
	public NextPrevVO selectMyNP(Map<String, Object> map) {
		return letterMapper.selectMyNP(map);
	}
	
	//중요쪽지함 카운트
	@Override
	public int selectImportantRowCount(Map<String, Object> map) {
		return letterMapper.selectImportantRowCount(map);
	}

	//중요쪽지함 리스트
	@Override
	public List<LetterVO> selectImportantList(Map<String, Object> map) {
		return letterMapper.selectImportantList(map);
	}

	//중요쪽지함 이전다음글
	@Override
	public NextPrevVO selectImportantNP(Map<String, Object> map) {
		return letterMapper.selectImportantNP(map);
	}

	//보낸쪽지함 중요 업데이트
	@Override
	public void updateSendImportant(Map<String, Object> map) {
		letterMapper.updateSendImportant(map);
	}
	
	//받는쪽지함 정보 가져오기
	@Override
	public LetterVO selectRecLetter(Map<String, Object> map) {
		return letterMapper.selectRecLetter(map);
	}
	//받은쪽지함 중요 업데이트
	@Override
	public void updateReceiveImportant(Map<String, Object> map) {
		letterMapper.updateReceiveImportant(map);
	}
	
	//쪽지함 리스트 읽음,안읽음처리
	@Override
	public void updateRead(LetterReadVO readVO) {
		String[] nums = readVO.getNums();
		for(int i=0;i<nums.length;i++) {
			int ltnum = Integer.parseInt(nums[i]);
			LetterVO letter = letterMapper.selectLetter(ltnum);
			readVO.setLt_num(ltnum);
			if(letter.getLt_sender_num()==readVO.getMem_num()) {//보낸쪽지함 읽음 업데이트
				if(letter.getLt_sender_num()== Integer.parseInt(letter.getLt_receiver_num())) {//내게쓴쪽지일경우
					if(readVO.getLt_type()==1) 
						letterMapper.updateReceiveRead(readVO);
					else
						letterMapper.updateReceiveRead(readVO);
				}else 
					letterMapper.updateSendRead(readVO);
				
			}else {//받은쪽지함 읽음 업데이트
				letterMapper.updateReceiveRead(readVO);
			}
		}
	}
	
	//상세페이지 기본정보
	@Override
	public LetterVO selectLetter(int lt_num) {
		return letterMapper.selectLetter(lt_num);
	}
	
	//상세페이지 이름정보 가져오기
	@Override
	public List<LetterVO> selectName(String[] rids) {
		return letterMapper.selectName(rids);
	}

	//상세페이지 보낸테이블 읽음 처리
	@Override
	public void updateSendRead(LetterReadVO readVO) {
		letterMapper.updateSendRead(readVO);
	}

	//상세페이지 받는테이블 읽음 처리
	@Override
	public void updateReceiveRead(LetterReadVO readVO) {
		letterMapper.updateReceiveRead(readVO);
	}

	

	

	

	

	

	

	

}

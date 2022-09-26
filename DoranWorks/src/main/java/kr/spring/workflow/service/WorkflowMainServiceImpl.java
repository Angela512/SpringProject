package kr.spring.workflow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.member.vo.MemberVO;
import kr.spring.workflow.dao.WorkflowMainMapper;
import kr.spring.workflow.vo.WorkflowMainVO;
import kr.spring.workflow.vo.WorkflowSignVO;
import kr.spring.workflow.vo.WorkflowVO;

@Service
@Transactional
public class WorkflowMainServiceImpl implements WorkflowMainService{

	@Autowired
	private WorkflowMainMapper flowMapper;
	
	@Override
	public List<WorkflowVO> selectList(Map<String, Object> map) {
		return flowMapper.selectList(map);
	}

	@Override 
	public int selectRowCount(Map<String, Object> map) { 
		return flowMapper.selectRowCount(map); 
	}
	 //결재 승인된 문서 카운트
	@Override
	public int countSign(Map<String, Object> map) {
		return flowMapper.countSign(map); 
	}
	
	 
	
	@Override
	public WorkflowVO selectBoard(Integer flow_num) {
		return flowMapper.selectBoard(flow_num);
	}

	@Override
	public void insertFlow_num(WorkflowVO work) {
		work.setFlow_num(flowMapper.selectFlow_num());
		flowMapper.insertTable_Main(work);
		flowMapper.insertTable_Test(work);
		
	}

	@Override
	public void updateBoard(WorkflowVO work) {
		flowMapper.updateBoard(work);
		
	}

	@Override
	public void updateSign(WorkflowVO work) {
		flowMapper.updateSign(work);
		
	}

	@Override
	public void updateSignOk(WorkflowVO flowVO) {
		flowMapper.updateSignOk(flowVO);
		
	}

	


	


	








	/*
	 * @Override public void updateBoard(WorkflowMainVO flow) {
	 * flowMapper.updateBoard(flow); }
	 */

	/*
	 * @Override public void deleteFile(Integer flow_num) {
	 * flowMapper.deleteFile(flow_num); }
	 */
	

	

}

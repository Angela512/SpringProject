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
	public List<WorkflowMainVO> selectList(Map<String, Object> map) {
		return flowMapper.selectList(map);
	}

	@Override 
	public int selectRowCount(Map<String, Object> map) { 
		return flowMapper.selectRowCount(map); 
	}
	 
	
	
	 
	
	@Override
	public WorkflowVO selectBoard(Integer flow_num) {
		return flowMapper.selectBoard(flow_num);
	}


	
	//테스트
	@Override
	public void insertTest(WorkflowVO work) {
		work.setFlow_num(flowMapper.selectFlow_num());
		flowMapper.insertTest(work);
		
	
		
	}

	@Override
	public void insertBoard(WorkflowVO flowVO) {
		flowVO.setFlow_num(flowMapper.selectCurrent_num());
		flowMapper.insertBoard(flowVO);
		
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

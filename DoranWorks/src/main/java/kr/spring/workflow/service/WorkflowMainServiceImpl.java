package kr.spring.workflow.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.workflow.dao.WorkflowMainMapper;
import kr.spring.workflow.vo.WorkflowMainVO;

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
	 
	/*
	 * @Override public void insertBoard(WorkflowMainVO flow) {
	 * flowMapper.insertBoard(flow); }
	 */
	 

	@Override
	public WorkflowMainVO selectBoard(Integer flow_num) {
		return flowMapper.selectBoard(flow_num);
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

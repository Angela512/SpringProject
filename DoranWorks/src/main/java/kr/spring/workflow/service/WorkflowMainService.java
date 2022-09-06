package kr.spring.workflow.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.spring.member.vo.MemberVO;
import kr.spring.workflow.vo.WorkflowMainVO;
import kr.spring.workflow.vo.WorkflowSignVO;


@Service
public interface WorkflowMainService {
	//부모글
		public List<WorkflowMainVO> selectList(Map<String,Object> map);
	    public int selectRowCount(Map<String,Object> map); 
	    public void insertBoard(WorkflowMainVO flow);
	    public boolean insertSign(WorkflowSignVO sign);
		public WorkflowMainVO selectBoard(Integer flow_num);
//		public void updateHit(Integer flow_num);
		/* public void updateBoard(WorkflowMainVO flow); */
//		public void deleteBoard(Integer flow_num);
//		public void deleteFile(Integer flow_num);
}

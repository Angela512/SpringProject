package kr.spring.workflow.dao;

import java.util.List;
import java.util.Map;

//import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;

import kr.spring.workflow.vo.WorkflowMainVO;
import kr.spring.workflow.vo.WorkflowSignVO;
import kr.spring.workflow.vo.WorkflowVO;



@Mapper
public interface WorkflowMainMapper {
	
		//부모글
		public List<WorkflowVO> selectList(Map<String,Object> map);
		public int selectRowCount(Map<String,Object> map);
		
		@Select("SELECT workflow_main_seq.nextval FROM dual")
		public int selectFlow_num();		
		/*
		@Insert("INSERT INTO workflow_main (flow_num,flow_title,flow_content,flow_sort,flow_start,flow_end,flow_subsort,flow_state,mem_num"
				+ ") "
				+ "VALUES (#{flow_num},#{flow_title},#{flow_content},#{flow_sort},#{flow_start},#{flow_end},#{flow_subsort},#{flow_state},#{mem_num})")
		public void insertBoard(WorkflowMainVO flow);
		*/		
		@Insert("insert into workflow_sign (sign_num, sign_name,mem_num) " 
				+"values(workflow_sign_seq.nextval, #{sign_name}, #{mem_num})")
		public boolean insertSign(WorkflowSignVO sign);
		
		
		/*
		@Select("SELECT * FROM workflow_main b JOIN member m "
				+ "USING(mem_num) JOIN member_detail d "
				+ "USING(mem_num) JOIN mem_rank c "
				+ "USING(mem_rank_num) JOIN mem_dpt s "
				+ "USING(mem_dpt_num) JOIN workflow_test t "
				+ "USING(flow_num) WHERE b.flow_num=#{flow_num}")
		*/
		
		@Select("SELECT * FROM workflow_main b JOIN member_detail m "
                +" ON b.mem_num = m.mem_num "
                +" JOIN mem_rank c "
                +" ON m.mem_rank_num = c.mem_rank_num "
                +" join mem_dpt d "
	          	+" ON m.mem_dpt_num = d.mem_dpt_num "
		        +" left join workflow_test t "
		        +" ON t.flow_num = b.flow_num WHERE b.flow_num=#{flow_num}")
		public WorkflowVO selectBoard(Integer flow_num);
		
		
		
		
		
		/*========테스트============*/
		@Insert("INSERT INTO workflow_main (flow_num,flow_title,flow_content,flow_sort,flow_start,flow_end,flow_subsort,flow_state,mem_num"
				+ ") "
				+ "VALUES (#{flow_num},#{flow_title},#{flow_content},#{flow_sort},#{flow_start},#{flow_end},#{flow_subsort},#{flow_state},#{mem_num})")
		public void insertTable_Main(WorkflowVO work);
		
		
		@Insert("insert into workflow_test (flow_num, sign_name, mem_num) " 
				+"values(#{flow_num}, #{sign_name}, #{mem_num})")
		public void insertTable_Test(WorkflowVO work);
		public void updateBoard(WorkflowVO work);
		
		
		
		
//		@Update("UPDATE spboard SET hit=hit+1 WHERE board_num=#{board_num}")
//		public void updateHit(Integer board_num);
//		public void updateBoard(WorkflowMainVO board);   ////
//		@Delete("DELETE FROM spboard WHERE board_num=#{board_num}")
//		public void deleteBoard(Integer board_num);
//		@Update("UPDATE spboard SET uploadfile='',"
//				+ "filename='' WHERE board_num=#{board_num}")
//		public void deleteFile(Integer board_num);
		
		
		
	
}

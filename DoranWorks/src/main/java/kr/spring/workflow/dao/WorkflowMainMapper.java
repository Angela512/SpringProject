package kr.spring.workflow.dao;

import java.util.List;
import java.util.Map;

//import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;

import kr.spring.workflow.vo.WorkflowMainVO;


@Mapper
public interface WorkflowMainMapper {
	
		//부모글
		public List<WorkflowMainVO> selectList(Map<String,Object> map);
		public int selectRowCount(Map<String,Object> map);
		@Insert("INSERT INTO workflow_main (flow_num,flow_title,flow_content,flow_sort,flow_start,flow_end,flow_subsort,mem_num"
				+ ") "
				+ "VALUES (workflow_main_seq.nextval,#{flow_title},#{flow_content},#{flow_sort},#{flow_start},#{flow_end},#{flow_subsort},#{mem_num})")
		public void insertBoard(WorkflowMainVO flow);
		@Select("SELECT * FROM workflow_main b JOIN member m "
				+ "USING(mem_num) JOIN member_detail d "
				+ "USING(mem_num) JOIN mem_rank c "
				+ "USING(mem_rank_num) JOIN mem_dpt s "
				+ "USING(mem_dpt_num) WHERE b.flow_num=#{flow_num}")
		public WorkflowMainVO selectBoard(Integer flow_num);
//		@Update("UPDATE spboard SET hit=hit+1 WHERE board_num=#{board_num}")
//		public void updateHit(Integer board_num);
//		public void updateBoard(WorkflowMainVO board);   ////
//		@Delete("DELETE FROM spboard WHERE board_num=#{board_num}")
//		public void deleteBoard(Integer board_num);
//		@Update("UPDATE spboard SET uploadfile='',"
//				+ "filename='' WHERE board_num=#{board_num}")
//		public void deleteFile(Integer board_num);
		
		
		
	
}

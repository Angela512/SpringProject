package kr.spring.calendar.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.calendar.vo.CalendarVO;

@Mapper
public interface CalendarMapper {
	//부모글
	public List<CalendarVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	
	  @Insert("INSERT INTO spboard (board_num,title," +
	  "content,uploadfile,filename,ip,mem_num) " +
	  "VALUES (spboard_seq.nextval,#{title}," +
	  "#{content},#{uploadfile},#{filename}," + "#{ip},#{mem_num})") public void
	  insertBoard(CalendarVO board);
	  
	  @Select("SELECT * FROM spboard b JOIN spmember m " +
	  "USING(mem_num) JOIN spmember_detail d " +
	  "USING(mem_num) WHERE b.board_num=#{board_num}") public CalendarVO
	  selectBoard(Integer board_num);
	  
	  @Update("UPDATE spboard SET hit=hit+1 WHERE board_num=#{board_num}") public
	  void updateHit(Integer board_num); public void updateBoard(CalendarVO board);
	  
	  @Delete("DELETE FROM spboard WHERE board_num=#{board_num}") public void
	  deleteBoard(Integer board_num);
	  
	  @Update("UPDATE spboard SET uploadfile=''," +
	  "filename='' WHERE board_num=#{board_num}") public void deleteFile(Integer
	  board_num);
	 
}






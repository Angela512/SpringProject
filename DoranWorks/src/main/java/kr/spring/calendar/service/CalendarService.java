package kr.spring.calendar.service;

import java.util.List;
import java.util.Map;

import kr.spring.calendar.vo.CalendarVO;

public interface CalendarService {
	//부모글
	public List<CalendarVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertCalendar(CalendarVO calendar);
	public CalendarVO selectCalendar(Integer calendar_num);
	
	/*
	 * //부모글 좋아요 public CalendarFavVO selectFav(CalendarFavVO fav); public int
	 * selectFavCount(Integer board_num); public void insertFav(CalendarFavVO
	 * boardFav); public void deleteFav(Integer fav_num);
	 * 
	 * //댓글 public List<CalendarReplyVO> selectListReply( Map<String,Object> map);
	 * public int selectRowCountReply( Map<String,Object> map); public
	 * CalendarReplyVO selectReply(Integer re_num); public void
	 * insertReply(CalendarReplyVO boardReply); public void
	 * updateReply(CalendarReplyVO boardReply); public void deleteReply(Integer
	 * re_num);
	 */

}

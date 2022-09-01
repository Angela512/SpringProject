package kr.spring.calendar.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.calendar.dao.CalendarMapper;
import kr.spring.calendar.vo.CalendarVO;

@Service
@Transactional
public class CalendarServiceImpl implements CalendarService{

	@Autowired
	private CalendarMapper calendarMapper;
	
	@Override
	public List<CalendarVO> selectList(Map<String, Object> map) {
		return calendarMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return calendarMapper.selectRowCount(map);
	}

	@Override
	public void insertCalendar(CalendarVO calendar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CalendarVO selectCalendar(Integer calendar_num) {
		// TODO Auto-generated method stub
		return null;
	}


}

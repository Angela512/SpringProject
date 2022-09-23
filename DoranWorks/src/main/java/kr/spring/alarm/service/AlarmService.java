package kr.spring.alarm.service;

import java.util.List;
import java.util.Map;

import kr.spring.alarm.vo.AlarmVO;

public interface AlarmService {
	public void insertAlarm(Integer alarm_kind, List<Integer> members);
	public int selectAlarmCount(AlarmVO vo);
	public void deleteAlarm(AlarmVO vo);
}

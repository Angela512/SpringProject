package kr.spring.alarm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.alarm.dao.AlarmMapper;
import kr.spring.alarm.vo.AlarmVO;

@Service
@Transactional
public class AlarmServiceImpl implements AlarmService{

	@Autowired
	AlarmMapper alarmMapper;
	
	@Override
	public void insertAlarm(Integer alarm_kind, List<Integer> members) {
		for(int mem_num : members) {
			AlarmVO vo = new AlarmVO();
			vo.setAlarm_kind(alarm_kind);
			vo.setMem_num(mem_num);
			alarmMapper.insertAlarm(vo);
		}
	}

	@Override
	public int selectAlarmCount(AlarmVO vo) {
		return alarmMapper.selectAlarmCount(vo);
	}

	@Override
	public void deleteAlarm(AlarmVO vo) {
		alarmMapper.deleteAlarm(vo);
	}
}

package kr.spring.alarm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.alarm.vo.AlarmVO;

@Mapper
public interface AlarmMapper {
	@Insert("INSERT INTO spalarm (alarm_num,alarm_kind,mem_num) VALUES (spalarm_seq.nextval,#{alarm_kind},#{mem_num})")
	public void insertAlarm(AlarmVO vo);
	@Select("SELECT COUNT(*) FROM spalarm WHERE alarm_kind=#{alarm_kind} AND mem_num=#{mem_num}")
	public int selectAlarmCount(AlarmVO vo);
	@Delete("DELETE FROM spalarm WHERE alarm_kind=#{alarm_kind} AND mem_num=#{mem_num}")
	public void deleteAlarm(AlarmVO vo);
}

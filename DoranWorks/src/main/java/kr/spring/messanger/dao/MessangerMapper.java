package kr.spring.messanger.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import kr.spring.messanger.vo.MessangerVO;

@Mapper
public interface MessangerMapper {
	@Insert("INSERT INTO message (msg_num, chatroom_num, msg_content, msg_uploadfile, msg_filename, msg_sendtime, mem_num, msg_count) "
			+ "VALUES (message_seq.nextval, #{chatroom_num}, #{msg_content}, #{msg_uploadfile}, #{msg_filename}, sysdate, #{mem_num}, 1)")
	public void insertMessage(MessangerVO messanger);
}

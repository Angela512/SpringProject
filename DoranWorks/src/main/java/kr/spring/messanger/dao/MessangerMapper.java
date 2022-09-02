package kr.spring.messanger.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.messanger.vo.ChatmemVO;
import kr.spring.messanger.vo.ChatroomVO;
import kr.spring.messanger.vo.MessangerVO;

@Mapper
public interface MessangerMapper {
	
	//채팅방
	@Insert("INSERT INTO chatroom (chatroom_num, chatroom_name) VALUES (chatroom_seq.nextval, #{chatroom_name})")
	public void insertChatroom(ChatroomVO chatroom);//채팅방 생성(채팅방번호 생성됨)
	
	public List<ChatroomVO> selectChatroomList(Map<String,Object> map);
	public int selectChatroomRowCount(Map<String, Object> map);
	
	//채팅방 생성 시 멤버 선택
	public int selectCheckedMemberCount(Map<String, Object> map);
	public List<ChatroomVO> selectCheckedMemberList(Map<String, Object> map);
	
	//메시지 
	public List<MessangerVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	
	@Insert("INSERT INTO message (msg_num, chatroom_num, msg_content, msg_uploadfile, msg_filename, mem_num) "
			+ "VALUES (message_seq.nextval, 100, #{msg_content}, #{msg_uploadfile}, #{msg_filename}, #{mem_num})")
	public void insertMessage(MessangerVO messanger); //
	
	@Select("SELECT * FROM message msg JOIN member m USING(mem_num) JOIN member_detail d USING (mem_num) "
			+ "WHERE msg.msg_num=#{msg_num}")
	public MessangerVO selectMessage(Integer msg_num); //채팅방 리스트에서 채팅방 눌러서 들어가기(==게시글 상세)
	
	
}

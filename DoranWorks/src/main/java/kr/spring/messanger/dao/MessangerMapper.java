package kr.spring.messanger.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.member.vo.MemberVO;
import kr.spring.messanger.vo.ChatmemVO;
import kr.spring.messanger.vo.ChatroomVO;
import kr.spring.messanger.vo.MessangerVO;

@Mapper
public interface MessangerMapper {
	
//==========채팅방==========//
	//채팅방 생성(채팅방번호 생성됨)
	@Select("SELECT chatroom_seq.nextval FROM dual")
	public int selectChatroom_num();
	
	@Insert("INSERT INTO chatroom (chatroom_num, chatroom_name) VALUES (#{chatroom_num}, #{chatroom_num})")
	public void insertChatroom(ChatroomVO chatroomVO);
	
	//채팅멤버 생성
	@Insert("INSERT INTO chatmem (chatroom_num, mem_num) VALUES (#{chatroom_num}, #{mem_num})")
	public void insertChatmem(ChatmemVO chatmemVO);
	
	//해당 채팅방의 멤버들 정보 가져오기
	@Select("SELECT m.chatroom_num chatroom_num, m.mem_num, r.chatroom_name, d.mem_name, d.mem_photo_name, dpt.mem_dpt, rank.mem_rank "
			+ "FROM chatmem m JOIN chatroom r ON m.chatroom_num = r.chatroom_num "
			+ "JOIN member_detail d ON m.mem_num = d.mem_num "
			+ "JOIN mem_dpt dpt ON dpt.mem_dpt_num = d.mem_dpt_num "
			+ "JOIN mem_rank rank ON rank.mem_rank_num = d.mem_rank_num "
			+ "WHERE m.chatroom_num=#{chatroom_num}")
	public List<ChatmemVO> selectChatmem(Integer chatroom_num);
	
	public List<ChatroomVO> selectChatroomList(Map<String,Object> map);
	public int selectChatroomRowCount(Map<String, Object> map);
	
	//채팅방 생성 시 멤버 선택
	public int selectCheckedMemberCount(Map<String, Object> map);
	public List<ChatroomVO> selectCheckedMemberList(Map<String, Object> map);
	
	//메시지 
	public List<MemberVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	
	@Insert("INSERT INTO message (msg_num, chatroom_num, msg_content, msg_uploadfile, msg_filename, mem_num) "
			+ "VALUES (message_seq.nextval, 100, #{msg_content}, #{msg_uploadfile}, #{msg_filename}, #{mem_num})")
	public void insertMessage(MessangerVO messanger); //
	
	@Select("SELECT * FROM message msg JOIN member m USING(mem_num) JOIN member_detail d USING (mem_num) "
			+ "WHERE msg.msg_num=#{msg_num}")
	public MessangerVO selectMessage(Integer msg_num); //채팅방 리스트에서 채팅방 눌러서 들어가기(==게시글 상세)
	
	
}

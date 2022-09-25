package kr.spring.messanger.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;
import kr.spring.messanger.vo.ChatmemVO;
import kr.spring.messanger.vo.ChatreadVO;
import kr.spring.messanger.vo.ChatroomVO;
import kr.spring.messanger.vo.MessangerVO;

@Mapper
public interface MessangerMapper {
	
//==========채팅방==========//
	//채팅방 번호 생성
	@Select("SELECT chatroom_seq.nextval FROM dual")
	public int selectChatroom_num();
	
	//채팅방 생성
	@Insert("INSERT INTO chatroom (chatroom_num, chatroom_name) VALUES (#{chatroom_num}, #{chatroom_name})")
	public void insertChatroom(ChatroomVO chatroomVO);
	
	//채팅멤버 생성
	@Insert("INSERT INTO chatmem (chatroom_num, mem_num) VALUES (#{chatroom_num}, #{mem_num})")
	public void insertChatmem(ChatmemVO chatmemVO);
	
	//해당 채팅방의 멤버들 정보 가져오기
	@Select("SELECT chatroom_num, mem_num, chatroom_name, mem_name, mem_photo_name, mem_dpt, mem_rank "
			+ "FROM chatmem JOIN chatroom USING(chatroom_num) JOIN member_detail USING(mem_num) "
			+ "JOIN mem_dpt USING(mem_dpt_num) JOIN mem_rank USING(mem_rank_num) WHERE chatroom_num=#{chatroom_num}")
	public List<ChatmemVO> selectChatmem(Integer chatroom_num);
	
	//채팅방 목록
	@Select("SELECT * FROM chatroom c JOIN chatmem USING(chatroom_num) WHERE mem_num=#{mem_num}")
	public List<ChatmemVO> selectChatroomList(Integer mem_num);
	
	//내 채팅방 목록 및 채팅방 멤버 수
	public List<ChatmemVO> selectChatmemCount(Map<String,Object> map);
	
	//해당 채팅방의 가장 최신 메시지 읽어옴
	@Select("SELECT * FROM (SELECT msg_content, chatroom_num, m.msg_sendtime, total_cnt "
			+ "FROM message m LEFT OUTER JOIN (SELECT msg_num, count(*) total_cnt "
			+ "FROM chatread GROUP BY msg_num)r USING(msg_num) "
			+ "WHERE chatroom_num=#{chatroom_num} ORDER BY msg_sendtime DESC) WHERE rownum=1")
	public MessangerVO selectRecentMsg(Integer chatroom_num);
	
	//채팅방 생성 시 멤버 선택
	public int selectCheckedMemberCount(Map<String, Object> map);
	public List<ChatroomVO> selectCheckedMemberList(Map<String, Object> map);
	
//====================메시지========================  
	//대화방에서 메시지 목록들 가져오기
	@Select("SELECT msg_num, mem_num, mem_name, msg_content, chatroom_num, msg_sendtime, total_cnt "
			+ "FROM message m LEFT OUTER JOIN (SELECT msg_num, count(*) total_cnt FROM chatread  GROUP BY msg_num)r "
			+ "USING(msg_num) JOIN member_detail USING(mem_num) WHERE chatroom_num=#{chatroom_num} ORDER BY msg_num ASC")
	public List<MessangerVO> selectMsgList(Integer chatroom_num);
	
	public int selectRowCount(Map<String, Object> map);
	
	//msg_num 생성
	@Select("SELECT message_seq.nextval FROM dual")
	public int selectMsg_num();
	
	//메시지 정보 저장
	@Insert("INSERT INTO message (msg_num, chatroom_num, msg_content, msg_uploadfile, msg_filename, mem_num, msg_sendtime) "
			+ "VALUES (#{msg_num}, #{chatroom_num}, #{msg_content}, #{msg_uploadfile}, #{msg_filename}, #{mem_num}, #{msg_sendtime})")
	public void insertMessage(MessangerVO messanger); 
	
	//메시지 보내면 Chatread에 일단 다 저장하고 읽으면 삭제하는 방식으로 진행
	@Insert("INSERT INTO chatread (chatread_num, msg_num, mem_num, chatroom_num) VALUES (#{chatread_num}, #{msg_num}, #{mem_num}, #{chatroom_num})")
	public void insertChatread(ChatreadVO chatreadVO);
	
	//chatread_num 생성
	@Select("SELECT chatread_seq.nextval FROM dual")
	public int selectChatread_num();
	
	//해당 채팅방에 있는 멤버들의 회원번호(mem_num) 가져옴
	@Select("SELECT mem_num FROM chatroom JOIN chatmem USING(chatroom_num) WHERE chatroom_num=#{chatroom_num}")
	public List<Integer> selectMsgMem_num(Integer chatroom_num);
	
	//메시지 알림용(나를 제외한 채팅방 멤버들의 mem_num 가져옴)
	@Select("SELECT mem_num FROM chatroom JOIN chatmem USING(chatroom_num) WHERE chatroom_num=#{chatroom_num} AND NOT mem_num=#{mem_num}")
	public List<Integer> selectMemberList(MessangerVO messanger);
	
	//타인이 보낸 메시지를 읽으면 읽은 사람의 회원번호로 삭제
	@Delete("DELETE FROM chatread WHERE mem_num=#{mem_num} AND chatroom_num=#{chatroom_num}")
	public void deleteChatread(@Param(value="mem_num") Integer mem_num, @Param(value="chatroom_num") Integer chatroom_num);
	
	@Select("SELECT to_char(SYSDATE,'yyyy-mm-dd hh24:mi:ss') FROM dual")
	public String selectMsgSendtime();
	
//====================채팅방 검색========================
	
	
}

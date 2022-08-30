package kr.spring.reservation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.reservation.vo.ReservationVO;

@Mapper
public interface ReservationMapper {
	//회의실 예약
	public List<ReservationVO> selectList(Map<String,Object> map);
	@Insert("INSERT INTO reservation (reserve_num,reserve_title,"
			+ "reserve_date,reserve_room,reserve_room_seat,mem_num) "
			+ "VALUES (reservation_seq.nextval,#{reserve_title},"
			+ "#{reserve_date},#{reserve_room},#{reserve_room_seat},"
			+ "#{mem_num})")
	public void insertReservation(ReservationVO reservation);
	@Select("SELECT * FROM reservation b JOIN member m "
			+ "USING(mem_num) JOIN member_detail d "
			+ "USING(mem_num) WHERE b.reserve_num=#{reserve_num}")
	public ReservationVO selectReservation(Integer reserve_num);
	/*
	 * @Update("UPDATE spboard SET hit=hit+1 WHERE board_num=#{board_num}") public
	 * void updateHit(Integer board_num); public void updateBoard(BoardVO board);
	 */
	@Delete("DELETE FROM reservation WHERE reserve_num=#{reserve_num}")
	public void deleteReservation(Integer reserve_num);
}

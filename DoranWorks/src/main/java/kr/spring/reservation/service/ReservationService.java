package kr.spring.reservation.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.letter.vo.LetterVO;
import kr.spring.reservation.vo.ReservationVO;

public interface ReservationService {
	//회의실 예약
	public List<ReservationVO> selectList(Map<String,Object> map);
	public void insertReservation(ReservationVO Reservation);
	
	public ReservationVO selectReservation(Integer reserve_num);
	/*
	 * @Update("UPDATE spboard SET hit=hit+1 WHERE board_num=#{board_num}") public
	 * void updateHit(Integer board_num); public void updateBoard(BoardVO board);
	 */
	public void deleteReservation(Integer reserve_num);
}





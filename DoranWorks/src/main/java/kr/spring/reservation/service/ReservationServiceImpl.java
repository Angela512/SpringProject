package kr.spring.reservation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.messanger.dao.MessangerMapper;
import kr.spring.reservation.dao.ReservationMapper;
import kr.spring.reservation.vo.ReservationVO;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	public ReservationMapper reservationMapper;

	@Override
	public List<ReservationVO> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void insertReservation(ReservationVO reservation) {
		reservationMapper.insertReservation(reservation);
	}

	@Override
	public ReservationVO selectReservation(Integer reserve_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteReservation(Integer reserve_num) {
		// TODO Auto-generated method stub
		
	}



	

}

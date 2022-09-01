package kr.spring.address.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.address.dao.AddressMapper;
import kr.spring.address.vo.AddressVO;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public List<AddressVO> selectList(Map<String, Object> map) {
		return addressMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return addressMapper.selectRowCount(map);
	}

	@Override
	public void insertAddress(AddressVO address) {
		addressMapper.insertAddress(address);
	}

	@Override
	public AddressVO selectAddress(Integer address_num) {
		return addressMapper.selectAddress(address_num);
	}

	@Override
	public void updateHit(Integer address_num) {
		addressMapper.updateHit(address_num);
	}

	@Override
	public void updateAddress(AddressVO address) {
		addressMapper.updateAddress(address);
	}

	@Override
	public void deleteAddress(Integer address_num) {
		//부모글 삭제
		addressMapper.deleteAddress(address_num);
	}

	@Override
	public void deleteFile(Integer address_num) {
		addressMapper.deleteFile(address_num);
	}

}

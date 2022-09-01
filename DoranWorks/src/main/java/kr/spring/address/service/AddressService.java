package kr.spring.address.service;

import java.util.List;
import java.util.Map;

import kr.spring.address.vo.AddressVO;

public interface AddressService {
	//부모글
	public List<AddressVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertAddress(AddressVO address);
	public AddressVO selectAddress(Integer address_num);
	public void updateHit(Integer address_num);
	public void updateAddress(AddressVO address);
	public void deleteAddress(Integer address_num);
	public void deleteFile(Integer address_num);

}

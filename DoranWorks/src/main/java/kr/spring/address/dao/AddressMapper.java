package kr.spring.address.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.address.vo.AddressVO;

@Mapper
public interface AddressMapper {
	//부모글
	public List<AddressVO> selectList(Map<String,Object> map);
}






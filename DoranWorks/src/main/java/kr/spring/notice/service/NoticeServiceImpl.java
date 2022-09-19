package kr.spring.notice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.notice.dao.NoticeMapper;

@Service
@Transactional
public class NoticeServiceImpl {

	@Autowired
	private NoticeMapper noticeMapper;
}

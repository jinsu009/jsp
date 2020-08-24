package kr.or.ddit.member.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.member.dao.IMemberDAO;

@Component
public class RealDeleteMemberService {

	private static final Logger logger = LoggerFactory.getLogger(RealDeleteMemberService.class);
	
	@Inject
	IMemberDAO memberDao;
	
	// 월요일 새벽 세시마다 작동 되도록 한다. 
	@Scheduled(cron = "*/5 * * * * * ")
	@Transactional
	public void deleteProcess() {
		Map<String, Object> paramMap = new HashMap<>();
		memberDao.realDeleteMembers(paramMap);
		logger.info("탈퇴 처리 결과 : {}",paramMap);
	}
	
}

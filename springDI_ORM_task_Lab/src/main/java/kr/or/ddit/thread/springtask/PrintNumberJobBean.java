package kr.or.ddit.thread.springtask;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

public class PrintNumberJobBean {

	private int number;
	
	@Scheduled(initialDelay = 0, fixedRate = 1000)
	public void printNumber() {
		System.out.printf("%s - %d \n", Thread.currentThread().getName(), ++number);
	}
	
	// 일괄적 탈퇴 작업 : batch job : mult thread 
	// scheduler 필요 
	// cron 표현식 : 시, 분 , 월, 초 등의 날짜 데이터 설정 
	@Scheduled(cron = "0 0 3 * * MON" )
	public void printDate() {
		System.err.printf("%s \n", new Date().toString() );
	}
	
}

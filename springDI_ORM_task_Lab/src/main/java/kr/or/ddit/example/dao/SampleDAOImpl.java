package kr.or.ddit.example.dao;

import org.springframework.stereotype.Repository;

@Repository
public class SampleDAOImpl implements ISampleDAO {

	
	public SampleDAOImpl() {
		System.out.println(getClass().getSimpleName()+" 객체 생성 ");
	}
	
	// 라이프 사이클 콜백의 return은 항상 void
	// 반환값을 주더라도 container한테는 의미가 없다 
	// 파라미터값을 받을 수 없다.
	public void init() {
		System.out.println(getClass().getSimpleName() + " 객체 초기화 ");
	}
	
	public void destory() {
		System.out.println(getClass().getSimpleName() + " 객체 소멸 ");
	}
	
	
	@Override
	public String selectRawData() {
		return "오라클에서 조회된 데이터 ";
	}

}

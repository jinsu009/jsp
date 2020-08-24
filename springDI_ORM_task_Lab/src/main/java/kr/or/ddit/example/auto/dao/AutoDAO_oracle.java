package kr.or.ddit.example.auto.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AutoDAO_oracle implements IAutoDAO{

	@Override
	public String getRawData() {
		// TODO Auto-generated method stub
		return "오라클 조회 데이터 ";
	}

}

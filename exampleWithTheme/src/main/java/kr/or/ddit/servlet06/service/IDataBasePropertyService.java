package kr.or.ddit.servlet06.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DataBasePropertyVO;

public interface IDataBasePropertyService {
	public List<DataBasePropertyVO> readDataBaseProperties(Map<String, Object> paramMap);
}

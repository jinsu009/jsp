package kr.or.ddit.servlet06.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DataBasePropertyVO;
//2020-05-19
public interface IDataBasePropertyService {
	public List<DataBasePropertyVO> readeDataBaseProperties(Map<String, Object> paramMap);
}

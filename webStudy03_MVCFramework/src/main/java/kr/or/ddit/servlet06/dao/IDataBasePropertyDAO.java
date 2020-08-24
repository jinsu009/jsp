package kr.or.ddit.servlet06.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DataBasePropertyVO;

// 2020-05-19
public interface IDataBasePropertyDAO {
	public List<DataBasePropertyVO> selectDataBaseProperties
	(Map<String, Object> paramMap);
}

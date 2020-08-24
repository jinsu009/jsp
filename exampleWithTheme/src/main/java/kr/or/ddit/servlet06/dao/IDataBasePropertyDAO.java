package kr.or.ddit.servlet06.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DataBasePropertyVO;

public interface IDataBasePropertyDAO {
	public List<DataBasePropertyVO> selectDataBaseProperties(Map<String, Object> paramMap);
}

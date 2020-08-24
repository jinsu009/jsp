package kr.or.ddit.servlet06.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import kr.or.ddit.servlet06.dao.DataBasePropertyDAOImpl;
import kr.or.ddit.servlet06.dao.IDataBasePropertyDAO;
import kr.or.ddit.vo.DataBasePropertyVO;

// 2020-05-19
public class DataBasePropertyServiceImpl implements IDataBasePropertyService {

	IDataBasePropertyDAO dao = new DataBasePropertyDAOImpl();
	@Override
	public List<DataBasePropertyVO> readeDataBaseProperties(Map<String, Object> paramMap) {
		List<DataBasePropertyVO> propList =  dao.selectDataBaseProperties(paramMap);
		
		Date today = new Date();
		
		for(DataBasePropertyVO tmp : propList) {
			tmp.setProperty_value(tmp.getProperty_value() + "_"+today);
		}
		
		return propList;
	}

}

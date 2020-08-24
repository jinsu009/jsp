package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BuyerVO;

//20-06-15
@Repository
public interface IOthersDAO {
//	public List<LProdVO> selectLprodList();
	public List<Map<String,Object>> selectLprodList();
	public List<BuyerVO> selectBuyerList(@Param("lgu") String lprod_gu);
}

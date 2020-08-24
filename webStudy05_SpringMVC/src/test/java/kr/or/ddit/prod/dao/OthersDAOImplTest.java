package kr.or.ddit.prod.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.WebAppTestContext;
import kr.or.ddit.vo.BuyerVO;

@RunWith(SpringRunner.class)
@WebAppTestContext
public class OthersDAOImplTest {
	IOthersDAO othersDAO;

	@Test
	public void testSelectLprodList() {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		assertNotNull(lprodList);
		assertNotEquals(0, lprodList.size());
	}

	@Test
	public void testSelectBuyerList() {
		String lprod_gu = null;
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(lprod_gu);
		assertNotNull(buyerList);
		assertNotEquals(0, buyerList.size());
	}

}

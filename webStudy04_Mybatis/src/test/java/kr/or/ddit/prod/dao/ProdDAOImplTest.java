package kr.or.ddit.prod.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImplTest {

	IProdDAO prodDAO = new ProdDAOImpl();
	PagingVO<ProdVO> pagingVO = new PagingVO<ProdVO>();
	
	@Test
	public void testInsertProd() {
		ProdVO pvo = new ProdVO();
		pvo.setProd_name("냉장고");
		pvo.setProd_lgu("P101");
		pvo.setProd_buyer("p10101");
		pvo.setProd_cost("5000");
		pvo.setProd_price("5000");
		pvo.setProd_sale("5000");
		pvo.setProd_outline("좋은 냉장고");
		pvo.setProd_img("ngng.jpg");
		pvo.setProd_totalstock("100");
		pvo.setProd_properstock("100");
		int cnt = prodDAO.insertProd(pvo);
		
		System.out.println(cnt);

	}

//	@Test
	public void testSelectProd() {
		ProdVO prod = prodDAO.selectProd("P101000001");
		assertNotNull(prod);
	}

//	@Test
	public void testSelectProdCount() {
		int cnt = prodDAO.selectProdCount(pagingVO);
		assertEquals(0, cnt);
	}

//	@Test
	public void testSelectProdList() {
		List<ProdVO> prodList = prodDAO.selectProdList(pagingVO);
		assertNotNull(prodList);
	}

//	@Test
	public void testUpdateProd() {
		fail("Not yet implemented");
	}

}

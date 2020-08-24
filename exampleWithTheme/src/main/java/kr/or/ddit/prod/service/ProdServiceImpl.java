package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements IProdService {
	IProdDAO prodDAO = new ProdDAOImpl();
	
	@Override
	public ServiceResult createProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdVO readProd(String prod_id) {
		ProdVO prod = prodDAO.selectProd(prod_id);
		if(prod==null)
			throw new DataNotFoundException(prod_id+" 상품이 없음.");
		return prod;
	}

	@Override
	public int readProdCount(PagingVO<ProdVO> pagingVO) {
		return prodDAO.selectProdCount(pagingVO);
	}

	@Override
	public List<ProdVO> readProdList(PagingVO<ProdVO> pagingVO) {
		return prodDAO.selectProdList(pagingVO);
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		readProd(prod.getProd_id());
		int cnt = prodDAO.updateProd(prod);
		ServiceResult result = ServiceResult.FAIL;
		if(cnt > 0) result = ServiceResult.OK;
		return result;
	}

}

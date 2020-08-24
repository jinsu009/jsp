package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDAOImpl;
import kr.or.ddit.buyer.dao.IBuyerDAO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

public class BuyerServiceImpl implements IBuyerService {
	IBuyerDAO buyerDAO = new BuyerDAOImpl();
	@Override
	public int readBuyerCount(PagingVO<BuyerVO> pagingVO) {
		return buyerDAO.selectBuyerCount(pagingVO);
	}

	@Override
	public List<BuyerVO> readBuyerList(PagingVO<BuyerVO> pagingVO) {
		return buyerDAO.selectBuyerList(pagingVO);
	}

	@Override
	public BuyerVO readBuyer(String buyer_id) {
		BuyerVO buyer = buyerDAO.selectBuyer(buyer_id);
		if(buyer==null) throw new DataNotFoundException("없음.");
		return buyer;
	}

	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		String pk = buyerDAO.insertBuyer(buyer);
		ServiceResult result = ServiceResult.OK;
		if(pk == null) result = ServiceResult.FAIL;
		return result;
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyer) {
		readBuyer(buyer.getBuyer_id());
		int rowcnt = buyerDAO.updateBuyer(buyer);
		ServiceResult result = ServiceResult.OK;
		if(rowcnt <= 0) result = ServiceResult.FAIL;
		return result;
	}

}

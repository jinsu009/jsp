package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 거래처 관리 Business Logic Layer
 *
 */
public interface IBuyerService {
	public int readBuyerCount(PagingVO<BuyerVO> pagingVO);
	public List<BuyerVO> readBuyerList(PagingVO<BuyerVO> pagingVO);
	public BuyerVO readBuyer(String buyer_id);
	public ServiceResult createBuyer(BuyerVO buyer);
	public ServiceResult modifyBuyer(BuyerVO buyer);
}

package homework.service;

import java.util.List;

import homework.dao.BuyerDAOImpl;
import homework.dao.IBuyerDAO;
import homework.enums.ServiceResult;
import homework.vo.BuyerVO;
import homework.vo.PagingVO;

public class BuyerServiceImpl implements IBuyerService{
	
	IBuyerDAO dao = BuyerDAOImpl.getInstance();
	
	private BuyerServiceImpl(){}
	private static BuyerServiceImpl self;
	public static IBuyerService getInstance() {
		if(self==null) self = new BuyerServiceImpl();
		return self;
	}

	@Override
	public ServiceResult createBuyer(BuyerVO buyvo) {
		String code = dao.createBuyer(buyvo);
		ServiceResult result = null;
		if(code!=null) {result = ServiceResult.OK;}
		else { result = ServiceResult.FAIL;}
		return result;
	}

	@Override
	public BuyerVO readBuyer(String buyer_id) {
		return dao.readBuyer(buyer_id);
	}

	@Override
	public int readBuyerCount(PagingVO pagingVO) {
		return dao.readBuyerCount(pagingVO);
	}

	@Override
	public List<BuyerVO> readBuyerList(PagingVO pagingVO) {
		return dao.readBuyerList(pagingVO);
	}

	@Override
	public ServiceResult modifyBuyer(BuyerVO buyvo) {
		int cnt = dao.modifyBuyer(buyvo);
		ServiceResult result  = null;
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public ServiceResult removeBuyer(BuyerVO buyvo) {
		// TODO Auto-generated method stub
		return null;
	}

}

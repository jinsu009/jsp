package homework.service;

import java.util.List;

import homework.enums.ServiceResult;
import homework.vo.BuyerVO;
import homework.vo.PagingVO;


/**
 * 
 * 
 *
 */
public interface IBuyerService {

	/**
	 * 신규 등록
	 * @param buyvo
	 * @return
	 */
	public ServiceResult createBuyer(BuyerVO buyvo);
	
	/**
	 * 정보 상세조회
	 * @param buyer_id
	 * @return
	 */
	public BuyerVO readBuyer(String buyer_id);
	
	/**
	 * 검색조건에 맞는 데이터 수
	 * @param pagingVO
	 * @return
	 */
	public int readBuyerCount(PagingVO pagingVO);
	
	/**
	 * 검색조건에 맞는 데이터 목록
	 * @param pagingVO
	 * @return
	 */
	public List<BuyerVO> readBuyerList(PagingVO pagingVO);
	
	/**
	 * 정보 수정
	 * @param buyvo
	 * @return
	 */
	public ServiceResult modifyBuyer(BuyerVO buyvo);
	
	/**
	 * 데이터 삭제
	 * @param buyvo
	 * @return
	 */
	public ServiceResult removeBuyer(BuyerVO buyvo);
	
}

package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품관리를 위한 Business Logic Layer
 *
 */
public interface IProdService {
	/**
	 * 신규 상품 등록
	 * @param prod
	 * @return OK, FAIL
	 */
	public ServiceResult createProd(ProdVO prod);
	/**
	 * 상품 상세 조회
	 * @param prod_id
	 * @return 존재하지 않으면, DataNotFoundException 발생 
	 */
	public ProdVO readProd(String prod_id);
	/**
	 * 검색 조건에 맞는 상품 수
	 * @param pagingVO
	 * @return
	 */
	public int readProdCount(PagingVO<ProdVO> pagingVO);
	/**
	 * 검색 조건에 맞는 상품 목록
	 * @param pagingVO
	 * @return
	 */
	public List<ProdVO> readProdList(PagingVO<ProdVO> pagingVO);
	
	/**
	 * 상품 수정
	 * @param prod
	 * @return 존재하지 않는다면, DataNotFoundException, OK, FAIL
	 */
	public ServiceResult modifyProd(ProdVO prod);
}










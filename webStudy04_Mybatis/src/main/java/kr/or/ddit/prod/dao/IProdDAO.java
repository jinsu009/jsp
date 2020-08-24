package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 
 * 상품관리를 위한 persistence Layer
 *
 */
public interface IProdDAO {

	/**
	 * 신규 상품 등록
	 * @param prod
	 * @return 신규 등록된 상품 코드 
	 */
	public int insertProd(ProdVO prod);
	
	/**
	 * 상품 상세 조회
	 * @param prod_id
	 * @return 존재하지 않으면 null 반환
	 */
	public ProdVO selectProd(String prod_id);
	
	/**
	 * 
	 * @param pagingVO
	 * @return
	 */
	public int selectProdCount(PagingVO<ProdVO> pagingVO);
	
	/**
	 * 페이징 처리된 상품 목록
	 * @param pagingVO
	 * @return
	 */
	public List<ProdVO>selectProdList(PagingVO<ProdVO> pagingVO);
	
	/**
	 * 상품수정
	 * @param prod
	 * @return
	 */
	public int updateProd(ProdVO prod);
}

package kr.or.ddit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface IBoardDAO {

	/**
	 * 새로운 글 등록
	 * @param boardVO
	 * @return 등록된 게시글 수 
	 */
	public int insertBoard(BoardVO boardVO);

	
	/**
	 * 검색 조건에 맞는 게시글 조회 
	 * @param pagingVO
	 * @return 
	 */
	public int selectBoardCount(PagingVO<BoardVO> pagingVO);

	/**
	 * 검색조건에 맞는 게시글 목록 조회 
	 * @param pagingVO
	 * @return
	 */
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);

	/**
	 * 게시글 상세보기 
	 * @param bo_no
	 * @return
	 */
	public BoardVO selectBoard(int bo_no);
	
	/**
	 * 게시글 조회수 올리기 
	 * @param bo_no
	 * @return
	 */
	public int incrementHit(int bo_no);
	
	/**
	 * 게시글 정보 수정
	 * @param boardVO
	 * @return
	 */
	public int updateBoard(BoardVO boardVO);
	
	/**
	 * 게시글 삭제 
	 * @param bo_no
	 * @return
	 */
	public int deleteBoard(int bo_no);

	
	
}

package kr.or.ddit.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

@Repository
public interface IAttatchDAO {

	/**
	 * 첨부파일 등록
	 * @param attatchVO
	 * @return
	 */
	public int insertAttatchs(BoardVO board);

	/**
	 * 
	 * @param att_no
	 * @return
	 */
	public AttatchVO selectAttatch(int att_no);
	
	/**
	 * 다운로드시 다운로드 횟수 증가 
	 * @param att_no
	 * @return
	 */
	public int incrementDownCount(int att_no);
	
	/**
	 * 첨부파일 삭제
	 * @param att_no
	 * @return
	 */
	public int deleteAttatchs(BoardVO board);
}

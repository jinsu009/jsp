package kr.or.ddit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

@Repository
public interface IReplyDAO {

	/**
	 * 댓글 등록
	 * @param replyVO
	 * @return
	 */
	public int insertReply(ReplyVO replyVO);
	
	/**
	 * 조건에 맞는 댓글 갯수 검색
	 * @param pagingVO
	 * @return
	 */
	public int selectReplyCount(PagingVO<ReplyVO> pagingVO);
	
	/**
	 * 댓글 목록 보여주기 
	 * @param pagingVO
	 * @return
	 */
	public List<ReplyVO> selectReplyList(PagingVO<ReplyVO> pagingVO);
	
	/**
	 * 댓글 수정
	 * @param replyVO
	 * @return
	 */
	public int updateReply(ReplyVO replyVO);
	
	/**
	 * 댓글 삭제
	 * @param rep_no
	 * @return
	 */
	public int deleteReply(Integer rep_no);
}

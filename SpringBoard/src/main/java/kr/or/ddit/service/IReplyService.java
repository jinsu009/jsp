package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public interface IReplyService {

	/**
	 * 새로운 댓글 등록
	 * @param replyVO
	 * @return
	 */
	public int createReply(ReplyVO replyVO);
	
	/**
	 * 조건에 해당하는 댓글 갯수 
	 * @param pagingVO
	 * @return
	 */
	public int readReplyCount(PagingVO<ReplyVO> pagingVO);
	
	/**
	 * 댓글 목록 
	 * @param pagingVO
	 * @return
	 */
	public List<ReplyVO> readReplyList(PagingVO<ReplyVO> pagingVO);
	
	/**
	 * 댓글 수정
	 * @param rep_no
	 * @return
	 */
	public int modifyReply(Integer rep_no);
	
	/**
	 * 댓글 삭제
	 * @param rep_no
	 * @return
	 */
	public int removeReply(Integer rep_no);
}

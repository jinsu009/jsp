package kr.or.ddit.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.dao.IReplyDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

@Service("replyService")
public class ReplyService implements IReplyService {

	@Inject
	private IReplyDAO dao;
	
	@Override
	public int createReply(ReplyVO replyVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int readReplyCount(PagingVO<ReplyVO> pagingVO) {
		return dao.selectReplyCount(pagingVO);
	}

	@Override
	public List<ReplyVO> readReplyList(PagingVO<ReplyVO> pagingVO) {
		return dao.selectReplyList(pagingVO);
	}

	@Override
	public int modifyReply(Integer rep_no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeReply(Integer rep_no) {
		// TODO Auto-generated method stub
		return 0;
	}

}

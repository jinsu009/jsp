package kr.or.ddit.commons.dao;

import java.util.List;

import kr.or.ddit.vo.ResourceVO;

public interface IResourceDAO {
	public List<ResourceVO> selectResourceList();
}

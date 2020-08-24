package kr.or.ddit.commons.dao;

import java.util.List;


import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ResourceVO;


@Repository
public interface IResourceDAO {
	public List<ResourceVO> selectResourceList();
}

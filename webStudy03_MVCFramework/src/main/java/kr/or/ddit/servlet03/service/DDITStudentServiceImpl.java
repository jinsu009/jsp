package kr.or.ddit.servlet03.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.servlet03.dao.DDITStudentDAOImpl;
import kr.or.ddit.servlet03.dao.IDDITStudentDAO;
import kr.or.ddit.servlet03.dao.IStdLicenseDAO;
import kr.or.ddit.servlet03.dao.StdLicenseDAOImpl;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;

// 2020-05-20
public class DDITStudentServiceImpl implements IDDITStudentService {

	// 의존관계 형성 (domain layer를 제외한 모든 레이어는 싱글톤)
	IDDITStudentDAO dao = DDITStudentDAOImpl.getInstance();
	IStdLicenseDAO licDAO = StdLicenseDAOImpl.getInstance();
	
	private DDITStudentServiceImpl() {}
	private static DDITStudentServiceImpl self;
	public static IDDITStudentService getInstance() {
		if(self==null) self = new DDITStudentServiceImpl();
		return self;
	}
	
	@Override
	public ServiceResult createStudent(DDITStudentVO vo) {
		String code = dao.insertStudent(vo);
		ServiceResult result = null;
		if(code!=null) { result = ServiceResult.OK;}
		else { result =ServiceResult.FAIL; }
		return result;
	}

	@Override
	public List<DDITStudentVO> readStudentList(PagingVO pagingVO) {
		return dao.selectAllStudent(pagingVO);
	}

	@Override
	public DDITStudentVO readStudent(String code) {
		DDITStudentVO vo = dao.selectStudent(code);
		if(vo==null) {
			throw new DataNotFoundException(code+" 에 해당하는 학생이 존재하지 않는다.");
		}
		return vo;
	}
	
	@Override
	public ServiceResult modifyStudent(DDITStudentVO vo) {
		int cnt = dao.updateStudent(vo);
		ServiceResult result = null;
		if(cnt>0) {
			licDAO.deleteLicenses(vo.getCode());
			if(vo.getLic_codes()!=null) {
				licDAO.insertLicenses(vo);
			}
			result = ServiceResult.OK;}
		else {result = ServiceResult.FAIL;}
		return result;
	}

	@Override
	public ServiceResult removeStudent(String code) {
		int cnt = licDAO.deleteLicenses(code);
		cnt += dao.deleteStudent(code);
		ServiceResult result = null;
		if(cnt>0) { result = ServiceResult.OK;}
		else { result = ServiceResult.FAIL; }
		return result;
	}

	@Override
	public int readStudentCount(PagingVO pagingVO) {
		return dao.selectStudentCount(pagingVO);
	}

}

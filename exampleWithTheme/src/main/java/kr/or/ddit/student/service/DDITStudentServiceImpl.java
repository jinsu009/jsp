package kr.or.ddit.student.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.student.dao.DDITStudentDAOImpl;
import kr.or.ddit.student.dao.IDDITStudentDAO;
import kr.or.ddit.student.dao.IStdLicenseDAO;
import kr.or.ddit.student.dao.StdLicenseDAOImpl;
import kr.or.ddit.vo.DDITStudentVO;
import kr.or.ddit.vo.PagingVO;

public class DDITStudentServiceImpl implements IDDITStudentService {
	private static IDDITStudentService self;
	public static IDDITStudentService getInstance() {
		if(self==null) self = new DDITStudentServiceImpl();
		return self;
	}
    // 의존관계 형성(domain layer 를 제외한 모든 레이어는 싱글턴)
	IDDITStudentDAO dao = DDITStudentDAOImpl.getInstance();
	IStdLicenseDAO licDAO = StdLicenseDAOImpl.getInstance();
	
	@Override
	public ServiceResult createStudent(DDITStudentVO vo) {
		// 트랜잭션 관리, 원자성
		String code = dao.insertStudent(vo);
		ServiceResult result = null;
		if(code!=null) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public int readStudentCount(PagingVO<DDITStudentVO> pagingVO) {
		return dao.selectStudentCount(pagingVO);
	}
	
	@Override
	public List<DDITStudentVO> readStudentList(PagingVO<DDITStudentVO> pagingVO) {
		return dao.selectStudentList(pagingVO);
	}

	@Override
	public DDITStudentVO readStudent(String code) {
		DDITStudentVO vo = dao.selectStudent(code);
		if(vo==null) {
			throw new DataNotFoundException(code+"에 해당하는 학생은 없음.");
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
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public ServiceResult removeStudent(String code) {
		int cnt =licDAO.deleteLicenses(code);
		cnt += dao.deleteStudent(code);
		ServiceResult result = null;
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAIL;
		}
		return result;
	}

}











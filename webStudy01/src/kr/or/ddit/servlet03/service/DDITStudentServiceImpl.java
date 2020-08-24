package kr.or.ddit.servlet03.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.servlet03.dao.DDITStudentDAOImpl;
import kr.or.ddit.servlet03.dao.IDDITStudentDAO;
import kr.or.ddit.vo.DDITStudentVO;

// 2020-05-20
public class DDITStudentServiceImpl implements IDDITStudentService {

	// 의존관계 형성 (domain layer를 제외한 모든 레이어는 싱글톤)
	IDDITStudentDAO dao = DDITStudentDAOImpl.getInstance();
	
	private DDITStudentServiceImpl() {}
	private static DDITStudentServiceImpl self;
	public static IDDITStudentService getInstance() {
		if(self==null) self = new DDITStudentServiceImpl();
		return self;
	}
	
	@Override
	public ServiceResult createStudent(DDITStudentVO vo) {
		int cnt = dao.insertStudent(vo);
		ServiceResult result = null;
		if(cnt>0) { result = ServiceResult.OK;}
		else { result =ServiceResult.FAIL; }
		return result;
	}

	@Override
	public List<DDITStudentVO> readStudentList() {
		return dao.selectAllStudent();
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
		if(cnt>0) { result = ServiceResult.OK;}
		else {result = ServiceResult.FAIL;}
		return result;
	}

	@Override
	public ServiceResult removeStudent(String code) {
		int cnt = dao.deleteStudent(code);
		ServiceResult result = null;
		if(cnt>0) { result = ServiceResult.OK;}
		else { result = ServiceResult.FAIL; }
		return result;
	}

}

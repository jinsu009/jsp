package kr.or.ddit.servlet03.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DDITStudentVO;

public class DDITStudentDAO implements IDDITStudentDAO {
	// 학생등록할 map
	// map 의 key = PK
	private static Map<String, DDITStudentVO> tableMap;
	
	static {
		// static 코드블럭 
		// 객체가 생성되기 전에 실행 
		// 그냥 {} : 생성자가 실행되기 전에 실행
		tableMap = new LinkedHashMap<>();
		
	}
	
	// 객체의 상태가 없을경우에만 싱글톤 패턴을 생성해준다. 
	private static IDDITStudentDAO self = new DDITStudentDAO(); 
	
	// 생성자 
	private DDITStudentDAO() {}
	public static IDDITStudentDAO getInstance(){
		return self;
	}
	
	/**
	 * 인터페이스가 없는 이유는 무엇 ????
	 * 결합력을 낮추기 위해서 인테퍼에스 생성, 구현체에서  dao에 대한 정보를 임의로  변경 못하도록
	 * 
	 * 장점 
	 * 1) 협엽 : 서로다른 개발자들의 규칙,
	 * 
	 */
	
	/* (non-Javadoc)
	 * @see kr.or.ddit.servlet03.dao.IDDITStudentDAO#insertStudent(kr.or.ddit.vo.DDITStudentVO)
	 */
	@Override
	public int insertStudent(DDITStudentVO vo){
		// key : s001, s002, .... 
		int recordCnt = tableMap.size();
		// database를 이용하면 padding를 사용하면 된다.
		// %flag,자릿수(width),conversion
		String code = String.format("S%03d", recordCnt+1);
		vo.setCode(code);
		
		tableMap.put(code, vo);
		
		return 1;
	}

	@Override
	public List<DDITStudentVO> selectAllStudent() {
		// TODO Auto-generated method stub
		// map안에서  
		return new ArrayList<DDITStudentVO>(tableMap.values());
	}
	
	@Override
	public DDITStudentVO selectStudent(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int updateStudent(DDITStudentVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int deleteStudent(String code) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

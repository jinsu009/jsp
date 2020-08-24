package kr.or.ddit.jdbc;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.vo.MemberVO;
// 20-06-15
public class MemberDAOImpl implements IMemberDAO {

	// 등록된 bean을 주입받으려면 dao도 bean으로 등록되어야한다. -> orm.context
	private SqlSessionTemplate template;
	public void setTemplate(SqlSessionTemplate template) {
		this.template = template;
	}
	
	@Override
	public MemberVO selectMember(String mem_id) {
//		MemberVO member = template.selectOne("kr.or.ddit.jdbc.IMemberDAO.selectMember",mem_id);
		
		// mapper proxy 생성 : 안정성이 없다. 
		// mapper proxy 반복작업 제거 
		IMemberDAO mapper = template.getMapper(IMemberDAO.class);
				
		return mapper.selectMember(mem_id);
	}

}

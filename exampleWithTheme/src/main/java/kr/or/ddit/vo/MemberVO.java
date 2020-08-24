package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 인증이나 회원관리에서 사용할 Domain Layer
 * 한명의 회원의 상세 조회시, 그 회원의 구매 상품 목록을 함께 조회.
 * 
 * ibatis를 이용한 다중 테이블 조인 후 조회 방법
 * 메인 테이블을 기준으로 조인되는 테이블들의 관계성 파악
 * 
 * 1:1
 * 1) VO 와 VO 사이에 (ProdVO) has a (BuyerVO) 관계 형성
 * 2) 다중 테이블을 직접 조인한 쿼리 작성
 * 3) resultClass 대신 resultMap을 수동 바인드.
 * 4) resultMap 과 resultMap 사이에  has a 관계 형성
 * 
 * 1:N
 * 1) VO 와 VO 사이에 (MemberVO) has many (ProdVO) 관계 형성
 * 2) 다중 테이블을 직접 조인한 쿼리 작성
 * 3) resultClass 대신 resultMap을 수동 바인드.
 * 4) resultMap 과 resultMap 사이에  has many 관계 형성
 * 5) N개의 레코드에서 중복 제거할 설정. groupby="PK" 설정. 
 * 
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(of= {"mem_id", "mem_regno1", "mem_regno2"})
public class MemberVO implements Serializable{
	public MemberVO(String mem_id, String mem_pass) {
		super();
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
	}

	private int rnum;
	private String mem_id;
	private transient String mem_pass;
	private String mem_name;
	private transient String mem_regno1;
	private transient String mem_regno2;
	private String mem_bir;
	private String mem_zip;
	private String mem_add1;
	private String mem_add2;
	private String mem_hometel;
	private String mem_comtel;
	private String mem_hp;
	private String mem_mail;
	private String mem_job;
	private String mem_like;
	private String mem_memorial;
	private String mem_memorialday;
	private Integer mem_mileage;
	private String mem_delete;
	
	private List<ProdVO> prodList;
	
	public String getMem_test() {
		return "테스트";
	}
}








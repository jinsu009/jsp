package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 인증이나 회원관리에서 사용할 Domain Layer
 */


/**
 * 05.26
 * 한명의 회원의 상세 조회시, 그 회원의 구매 상품 목록을 함께조회
 * 1) 테이블 간의 관계성 파악 후 vo와 vo 사이에 has many 관계 형성 
 * 	memberVO가 prodVO를 포함하고 있어야 한다.
 * memvo has many prodvo
 */

/**
 * 2020-05-22
 * ibatis를 이용한 다중 테이블 조인 후 조회 방법 
 * 메인테이블을 기준으로 조인되는 테이블들의 관계성 파악 
 * (1:n ) 
 * 	1) 테이블 간의 관계성 파악 후 vo와 vo 사이에 has many 관계 형성 
 *  2) 다중 테이블을 직접 조인한 쿼리 작성 
 *  3) resultClass 대신에 resultMap을 수동 바인드. 
 *  4) resultMap과 resultMap 사이에서 has many 관계 형성 
 *  5) N개의 레코드에서 중복 제거할 설정 . groupby="PK" 설정 
 *  

 */



@Data
@EqualsAndHashCode(of= {"mem_id","mem_regno1","mem_regno2"})
@NoArgsConstructor
// 2020-05-19
public class MemberVO implements Serializable{
	
	public MemberVO(String mem_id, String mem_pass) {
		super();
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
	}

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

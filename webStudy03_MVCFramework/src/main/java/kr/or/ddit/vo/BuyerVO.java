package kr.or.ddit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of={"buyer_id"})

// 2020-05-26
/**
 *  (1:1)
 *	1) 테이블 간의 관계성 파악 후 vo와 vo 사이에 has a 관계 형성 
 *  2) 다중 테이블을 직접 조인한 쿼리 작성 
 *  3) resultClass 대신에 resultMap을 수동 바인드. 
 *  4) resultMap과 resultMap 사이에서 has a 관계 형성 
 *  (prodVO) has a (BuyerVO)
 *
 */
public class BuyerVO {
	private String buyer_id;
	private String buyer_name;
	private String buyer_lgu;
	private String buyer_bank;
	private String buyer_bankno;
	private String buyer_bankname;
	private String buyer_zip;
	private String buyer_add1;
	private String buyer_add2;
	private String buyer_comtel;
	private String buyer_fax;
	private String buyer_mail;
	private String buyer_charger;
	private String buyer_telext;
	
}

package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude= {"prod_detail", "prod_img"})
@EqualsAndHashCode(of= {"prod_id"})
public class ProdVO {
	private String prod_id;
	private String prod_name;
	private String prod_lgu;
	private String prod_buyer;
	private Integer prod_cost;
	private Integer prod_price;
	private Integer prod_sale;
	private String prod_outline;
	private String prod_detail;
	private String prod_img;
	private Integer prod_totalstock;
	private String prod_insdate;
	private Integer prod_properstock;
	private String prod_size;
	private String prod_color;
	private String prod_delivery;
	private String prod_unit;
	private Integer prod_qtyin;
	private Integer prod_qtysale;
	private Integer prod_mileage;
	
	private String lprod_nm;
	private BuyerVO buyer;
	
	private List<MemberVO> memberList;
}











package homework.vo;

import lombok.*;

//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor // 4가지를 Data하나로 퉁칠수 있다.
@Data
@ToString(exclude={"prod_detail","prod_img"})
@EqualsAndHashCode(of={"prod_id"})

// 2020-05-26
public class ProdVO {
	
	private String prod_id;
	private String prod_name;
	private String prod_lgu;
	private String prod_buyer;
	private String prod_cost;
	private String prod_price;
	private String prod_sale;
	private String prod_outline;
	private String prod_detail;
	private String prod_img;
	private String prod_totalstock;
	private String prod_insdate;
	private String prod_properstock;
	private String prod_size;
	private String prod_color;
	private String prod_delivery;
	private String prod_unit;
	private String prod_qtyin;
	private String prod_qtysale;
	private String prod_mileage;
	
	
	
}

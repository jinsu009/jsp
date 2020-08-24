package kr.or.ddit.vo;

import lombok.Data;

// 20-06-12
@Data
public class MenuVO {

	// 하나의 메뉴를 보여주기 위한 변수를 전부 설정
	private String url;
	private String text;
	private String cssClass;
}

package kr.or.ddit.enums;

public enum BrowserType {
	CHROME("크롬"), TRIDENT("익플"), FIREFOX("파폭"), OTHER("기타");
	
	private String desc;

	private BrowserType(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
	public static String browserIdentify(String userAgent) {
		userAgent = userAgent.toUpperCase();
		BrowserType result = OTHER;
		for( BrowserType tmp : values()) {
			if(userAgent.contains(tmp.name())) {
				result = tmp;
				break;
			}
		}
		return result.getDesc();
	}
}













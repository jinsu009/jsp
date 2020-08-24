package kr.or.ddit.enums;

//2020 05 15

public enum IndexType {
	// 기본생성자
	STANDARDJSP("/01/standard.jsp"),CALCULATOR("/01/calForm.jsp"),
	CALENDAR("/03/calendar0511.jsp"),SESSIONTIMER("/05/sessionTimer.jsp"),FOLDER("/ddit/contextBrowse.do"); 
	
	private String jspName; // 콘텐츠 하나가 가질수 있는 url 정보를 넣어준다. 

	private IndexType(String jspName) {
		this.jspName = jspName;
	} // 생성자 

	public String getJspName() {
		return jspName;
	}
	
	// servlet에서 해야할 과정을 현재페이지의 메소드에서 실행 시킨다. 
	public static String index(String jspAddr)
	{
		jspAddr = jspAddr.toUpperCase();
		IndexType result = null;
		
		for(IndexType tmp : values()) {
			// values 혹은 valueOf 를 사용하여 enum 이 기본적으로 가지고 있는 함수를 사용할 수 있다. 
			if(jspAddr.contains(tmp.name())) {
				result = tmp;
				break;
			}
		}
		return result.getJspName();
	}
}

package kr.or.ddit.enums;

public enum ServiceType {
	STANDARDJSP("/01/standard.jsp"), CALENDAR("/02/calendar.jsp"), 
	CALCULATOR("/01/calForm.jsp"), SESSIONTIMER("/05/sessionTimer.jsp"), 
	BROWSING("/ddit/contextBrowse.do");
	
	private ServiceType(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	private String contentUrl;
	public String getContentUrl() {
		return contentUrl;
	}
	public static String findContentUrl(String serviceName) {
		ServiceType service = valueOf(serviceName);
		return service.getContentUrl();
	}
}












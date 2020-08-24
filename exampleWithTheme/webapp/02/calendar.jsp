<%@page import="java.util.TimeZone"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
	table{
		height: 500px;
	}
	.blue{
		color: blue;
	}
	.red{
		color: red;
	}
	.green{
		background-color: green;
	}
</style>

<!-- jsp 파일 하나로 달력 완성하기. -->
<!-- 1. 전달의 달력에 대한 링크 -->
<!-- 2. 다음 달 달력의 링크 -->
<!-- 3. 임의의 년도와 월을 입력받아 달력 완성. -->
<%
String yearStr = request.getParameter("year");
String monthStr = request.getParameter("month");
String lang = request.getParameter("language");
String zone = request.getParameter("zone");
TimeZone currentZone = TimeZone.getDefault();
if(StringUtils.isNotBlank(zone)){
	currentZone = TimeZone.getTimeZone(zone);
} 
Locale clientLocale = request.getLocale();
if(StringUtils.isNotBlank(lang)){
	clientLocale = Locale.forLanguageTag(lang);
}
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M");
Calendar cal = getInstance(currentZone);
int currentYear = cal.get(YEAR);
int currentMonth = cal.get(MONTH);
int currentDay = cal.get(DAY_OF_MONTH);
try{
	sdf.parse(yearStr+"-"+monthStr);
	cal.set(YEAR, Integer.parseInt(yearStr));
	cal.set(MONTH, Integer.parseInt(monthStr));
}catch(ParseException e){
	
}
//****************
// cal.setTimeZone(currentZone);
//****************
out.println(String.format(clientLocale, "<h4>currentTime - %tc</h4>", cal));
cal.set(DAY_OF_MONTH, 1);
int dayOfWeek = cal.get(DAY_OF_WEEK);
int offset = dayOfWeek - 1;
int maxDay = cal.getActualMaximum(DAY_OF_MONTH);
int year = cal.get(YEAR);
int month = cal.get(MONTH);
boolean currentFlag = currentYear==year && currentMonth == month;
cal.add(MONTH, -1);
int beforeYear = cal.get(YEAR);
int beforeMonth = cal.get(MONTH);
cal.add(MONTH, 2);
int nextYear = cal.get(YEAR);
int nextMonth = cal.get(MONTH);

DateFormatSymbols dfs = new DateFormatSymbols(clientLocale);

%>
<a class="aLink" href="#" data-year="<%=beforeYear %>" data-month="<%=beforeMonth %>">이전달</a>
<h4><%=year %>, <%=month+1 %></h4>
<a class="aLink" href="#" data-year="<%=nextYear %>" data-month="<%=nextMonth %>">다음달</a>
<form id="calForm" action="${pageContext.request.contextPath }/02/calendar.jsp" class="form-inline">
year : <input type="number" name="year" value="<%=year %>" class="form-control mr-2"/>
month : 
<select name="month" class="form-control mr-2">
	<%
		String[] months = dfs.getMonths();
		for(int idx = 0; idx < months.length-1; idx++){
			String selected = month==idx?"selected":"";
			out.println(
				String.format("<option %s value='%d'>%s</option>", selected, idx, months[idx])		
			);	
		}		
	%>
</select>
<select name="language" class="form-control mr-2">
	<option value>언어선택</option>
	<%
		Locale[] locales = Locale.getAvailableLocales();
		for(Locale tmp : locales) {
			String display = tmp.getDisplayLanguage(tmp);
			if(StringUtils.isBlank(display)) continue;
			String selected = tmp.equals(clientLocale)?"selected":"";
			out.println(
				String.format("<option %s value='%s'>%s</option>", 
					selected, tmp.toLanguageTag(), display)
			);
		}
	%>
</select>
<select name="zone" class="form-control mr-2">
	<%
		String[] ids = TimeZone.getAvailableIDs();
		for(String zoneId : ids){
			TimeZone tz = TimeZone.getTimeZone(zoneId);
			String display = tz.getDisplayName(clientLocale);
			String selected = zoneId.equals(zone)?"selected":"";
			out.println(
				String.format("<option %s value='%s'>%s</option>", 
					selected, zoneId, display)		
			);
		}
	%>
</select>
</form>
<table class="table table-bordered">
<thead>
	<tr>
		<%
			
			String[] weekdays = dfs.getShortWeekdays();
			for(int idx = Calendar.SUNDAY; idx <= Calendar.SATURDAY; idx++){
				out.println(String.format("<th>%s</th>", weekdays[idx]));
			}
		%>
	</tr>
</thead>
<tbody>
<%
	int count = 1;
	for(int row=1; row<=6; row++){
		out.println("<tr>");
		for(int col=1; col<=7; col++){
			int number = count++ - offset;
			String displayNumber = (number > 0 && number <= maxDay) ? number+"" : "&nbsp;";
			String clzName = col==1?"red":(col==7?"blue":"normal");
			if(currentFlag && number==currentDay){
				clzName += " green";
			}
			out.println(
				String.format("<td class='%s'>%s</td>", clzName, displayNumber)		
			);
		}
		out.println("</tr>");
	}
%>
</tbody>
</table>
<script type="text/javascript">
	var calForm = $("#calForm");
	$(".aLink").on("click", function(){
		let year = $(this).data("year");
		let month = $(this).data("month");
		calForm.find("[name='year']").val(year);
		calForm.find("[name='month']").val(month);
		calForm.submit();
	});
	$(":input").on("change", function(){
		$(this).closest("form").submit();
	});
</script>







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
			table{ height : 500px; 	}
			.blue{color:blue }
			.red{color:red }
			.green{background-color : green}
			a:link{color:blue; text-decoration: none;}
			a:visited{color:blue;}
		</style>
		
	
	<pre>
			0508 과제
			월요일 오전 아홉시까지 
			jsp 파일 하나로 달력완성하기 
			1. 전 달의 달력에 대한 링크 
			2. 다음달 달력의 링크 
			3. 임의의 년도와 월을 입력받아 달력 완성
	</pre>
	<%
	
	String yearStr = request.getParameter("year");
	String monthStr = request.getParameter("month");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

	//============================================
	String lang = request.getParameter("locale");
	Locale clientLocale = request.getLocale();
	// 파라미터 값이 넘어오면 선택된 값으로 넣어준다. 
	if(StringUtils.isNotBlank(lang)){
		clientLocale = Locale.forLanguageTag(lang);
	}
	
	// 한글 달력을 영문 달력으로 바꿔 보자 
	// locale 을 이용해 언어를 선택해서 언어에 맞는 달력이 나오도록 한다.
	DateFormatSymbols dfs = new DateFormatSymbols(clientLocale);
	
	//============================================

	
	// TimeZone 이용해서 오늘 날짜에 배경색 표시하기 
	String zone = request.getParameter("zone");
	
	TimeZone currentZone = TimeZone.getDefault();
	if(StringUtils.isNotBlank(zone)){
		currentZone = TimeZone.getTimeZone(zone);
	}
			
	// 추상 클래스  : new 로 생성하지 않는다.
	Calendar cal = getInstance(currentZone);
	
	int currentYear = cal.get(YEAR);
	int currentMonth = cal.get(MONTH);
	int currentDay = cal.get(DAY_OF_MONTH);
	
	
	
// 	cal.setTimeZone(currentZone);
	
	out.println(String.format("<h2 style='color:pink'> %d : %d </h2>"
			,cal.get(Calendar.HOUR_OF_DAY),cal.get(MINUTE)));
	
	
	//================================
	
	try{
		// 년도와 월을 파라미터에 따라 변경해줘야 한다. 
		sdf.parse(yearStr+"-"+monthStr);
		cal.set(YEAR, Integer.parseInt(yearStr));
		cal.set(MONTH, Integer.parseInt(monthStr));
		
	}catch(ParseException e){
		// 현재 년도와 월을 넘겨준다
	}
	
	//=================================
				// 해당 월에서 1번째 날짜를 set
				cal.set(DAY_OF_MONTH, 1);
				
				int dayOfWeek = cal.get(DAY_OF_WEEK);
				out.print("dayOfWeek > " + dayOfWeek); 
				// 일, 월, 화, 수, 목, 금, 토 
				int offset = dayOfWeek - 1; 
				int maxDay = cal.getActualMaximum(DAY_OF_MONTH);
				
				int year = cal.get(YEAR);
				int month = cal.get(MONTH);
				
				boolean currentFlag = currentYear==year && currentMonth==month;
				
				
				// 전달의 년도와 월을 찾아야한다. 
				cal.add(MONTH, -1);
				int beforeYear = cal.get(YEAR);
				int beforeMonth = cal.get(MONTH);
				
				// 다음달 년도와 월 , 12월에서는 년도가 넘어가니까 주의
				cal.add(MONTH, 2);
				int nextYear = cal.get(YEAR);
				int nextMonth = cal.get(MONTH);
	%>
	<div>
	<!-- 
		data : key를 가지고 해당데이터를 넣어준다.(?)
		----
		한페이지 안에서 많은 값을 전송하게 된다면 되도록 방법을 단일화 시켜서 값을 전송하도록 한다. 
	-->
	
		<a class="aLink" style="float:left" href="#" data-year="<%=beforeYear%>" data-month="<%=beforeMonth %>">◀</a>
		<h4 style="float:left;"><%=year %> 년 , <%=month+1 %> 월 </h4>
		<a class="aLink" style="float:left" href="#" data-year="<%=nextYear %>" data-month="<%=nextMonth %>">▶</a>
		<form id="calForm">
			year : <input type="number" name="year" value="<%=year %>"/>
			month : 
				<select name="month">
					<%
					// selected : true or false
					// i == month 일때만 값을 넘겨준다.
						String[] months = dfs.getMonths();
						for(int i=0; i<=months.length-1; i++){
							String selected = month==i ? "selected":"";
							out.println(
								String.format("<option %s value='%d'>%s</option>",selected,i,months[i])								
									
							);
						}
					%>
				</select>
				
			language : 
				<select name="locale">
					<option value> 언어선택 </option>
					<%
					Locale[] locales = Locale.getAvailableLocales();
					for(Locale tmp : locales) {
						String display = tmp.getDisplayLanguage(tmp);
						if(StringUtils.isBlank(display)){ continue;	}
						String selected = clientLocale.equals(tmp) ? "selected":"";
						out.println(String.format("<option %s value='%s'>%s</option>"
								, selected , tmp.toLanguageTag(), display ));
					}
					%>
				</select>
				
			timeZone :
				<select name="zone">
					<%
						String[] ids = TimeZone.getAvailableIDs();
						for(String zoneId : ids){
							TimeZone tz = TimeZone.getTimeZone(zoneId);
							String display = tz.getDisplayName();
							String selected = zoneId.equals(zone)? "selected":"";
							out.println(
								String.format("<option %s value='%s'>%s</option>"
								,selected , zoneId, display)		
							);
						}
					%>
				</select>
		</form>
	</div>
	<table class="table table-bordered">
		<thead>
			<tr>
				<%
					// 날짜 표현할때 사용하는 메소드 
					String[] weekdays = dfs.getShortWeekdays();
					
					//상수의 값이 바뀌더라도 코드는 변하지 않는 편리함을 가지게 된다. 
					for(int i=Calendar.SUNDAY; i<=Calendar.SATURDAY; i++){
						out.println(String.format("<th>%s</th>",i));
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
						String clzName = col==1? "red" : (col==7 ? "blue" :"normal");
						if( currentFlag && number==currentDay ){
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
	
		$(".aLink").on("click",function(){
			// 선택된 a태그의 data를 가져온다.
			let year = $(this).data("year");
			let month = $(this).data("month");
			// 새로운값을 넘길때에는 무조건 form으로 데이터를 제출해야한다. 
			calForm.find("[name='year']").val(year);
			calForm.find("[name='month']").val(month);
			calForm.submit();
		});
		
		// ':' 입력할수 있는 모든 태그를 포함 	
		$(":input").on("change",function(){
			// 제일 가까운 form 태그를 가져온다.
			$(this).closest("form").submit();
		});
	</script>
	
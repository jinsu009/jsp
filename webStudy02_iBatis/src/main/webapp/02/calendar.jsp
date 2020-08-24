<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/calendar.jsp</title>
<script type="text/javascript">
	function showClock(){
		// 시간이 가는 시계 
		var currentDate = new Date();
		var pClock = document.getElementById("pClock");
		var apm = currentDate.getHours();
		if(apm<12)
		{
			apm = "오전";
		}
		else{
			apm = "오후";
		}
		var msg = apm + " " + (currentDate.getHours()-12)+" : ";
		msg += currentDate.getMinutes()+" : ";
		msg += currentDate.getSeconds();
		pClock.innerText=msg;
		setTimeout(showClock,1000);
	}
</script>

<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("a hh:mm:ss");
	SimpleDateFormat bluesf = new SimpleDateFormat("yyyy년 MM월 dd일 E요일");
	
	Calendar c = Calendar.getInstance(); 
	Calendar cal = Calendar.getInstance(); 
	
	int action = 0;
	int currMon = 0;
	int currYear = 0;
	
	if(request.getParameter("action")==null){
		currMon = c.get(Calendar.MONTH);
		currYear = c.get(Calendar.YEAR);
		cal.set(currYear, currMon,1);
	}
	else{
		if(request.getParameter("action")!=null){
			currMon = Integer.parseInt(request.getParameter("month"));
			currYear = Integer.parseInt(request.getParameter("year"));
			
				if(Integer.parseInt(request.getParameter("action"))==1){
					cal.set(currYear, currMon,1);
					cal.add(Calendar.MONTH, 1);
					currMon = cal.get(Calendar.MONTH);
					currYear = cal.get(Calendar.YEAR);
				}
				else{
					cal.set(currYear, currMon, 1);
					cal.add(Calendar.MONTH, -1);
					currMon = cal.get(Calendar.MONTH);
					currYear = cal.get(Calendar.YEAR);
				}
		}
	}
// 	System.out.println(currYear);
// 	System.out.println(currMon+1);
%>

<%!
	public boolean isDate(int y, int m, int d){
	
			m -= 1;
			Calendar c = Calendar.getInstance();
			c.setLenient(false);
			
			try{
				c.set(y,m,d);
				Date date = c.getTime();
			}catch(IllegalArgumentException e){
				return false;
			}
			
			return true;
	}

	public String getTitle(Calendar cal){
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy년 MM월");
		return sf2.format(cal.getTime());
	}

%>

<style type="text/css">
	div{ width:600px; height:600px; text-align: center;}
	table{width:300px; height:300px; margin: 0 auto;}
	a:link{color:red; text-decoration: none;}
	a:visited{color:red;}
	#cal { background-color:#c5dafa; color:white;} 
	#pClock {font-size:25pt;}
	.empty{background-color: yellow;}
	.toDayColor{ background-color:#0064fc; }
</style>
	</head>
	<body onload="showClock()">
		<pre>
			0508 과제
			월요일 오전 아홉시까지 
			jsp 파일 하나로 달력완성하기 
			1. 전 달의 달력에 대한 링크 
			2. 다음달 달력의 링크 
			3. 임의의 년도와 월을 입력받아 달력 완성
			
			** 참고 사이트 : 
			 달력 : http://blog.naver.com/PostView.nhn?blogId=javaking75&logNo=220143360268&parentCategoryNo=&categoryNo=55&viewDate=&isShowPopularPosts=true&from=search
			 시계 : https://euny0356.tistory.com/13
		</pre>
		<hr>
		
		<div id="cal">
			<p id="pClock"><%=sf.format(nowTime) %></p>
			<p style="color:#429ce3;"><%=bluesf.format(nowTime) %></p>
			<p>
			<%--!
			private boolean validate(String yearstr, String monstr){
				boolean valid = true;
				if(StringUtils.isBlank(yearstr) || StringUtils.isBlank(monstr) ){
					valid = false;
				}else{
					try{
						Integer.parseInt(yearstr);
						Integer.parseInt(monstr);
					}catch(NumberFormatException e){
						// 숫자가 아닌게 넘어온다.
					 	valid = false;
					}
				}
				return valid;
			}
			--%>
			<%--
			
				String yearstr = request.getParameter("wYear");
				String monstr = request.getParameter("wMonth");
			
			if(StringUtils.isBlank(yearstr) || StringUtils.isBlank(monstr)){
				return;
			}
			if(!validate(yearstr, monstr)){
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
// 				int yearint = Integer.parseInt(yearstr);
// 				int monint  = Integer.parseInt(monstr);
				currYear = Integer.parseInt(yearstr);
				currMon = Integer.parseInt(monstr);
				
			--%>
				<a href="calendar.jsp?month=<%=currMon%>&year=<%=currYear%>&action=0">◁</a> 
				<%=getTitle(cal) %>    
				<a href="calendar.jsp?month=<%=currMon%>&year=<%=currYear%>&action=1">▷</a>
			</p>
			<table>
				<tr>
					<td>
						<table id="caltable">
							<tr>
								<td>일</td>
								<td>월</td>
								<td>화</td>
								<td>수</td>
								<td>목</td>
								<td>금</td>
								<td>토</td>
							</tr>
							<%
								int currDay;
								String todayColor;
								int count = 1;
								int dispDay = 1;
								
								for(int i = 1; i<7; i++)
								{
							%>
								<tr>
							<%
								for(int j=1;j<8;j++)
								{
									if(!(count >= cal.get(Calendar.DAY_OF_WEEK)))
										{
						%>
									<td class="empty">&nbsp;</td>
						<%
												count += 1;
										}
										else
										{
											if(isDate(currYear, currMon+1, dispDay))
											{
												if(dispDay==c.get(Calendar.DAY_OF_MONTH)
													&& c.get(Calendar.MONTH)==cal.get(Calendar.MONTH)
													&& c.get(Calendar.YEAR)==cal.get(Calendar.YEAR))
												{
													todayColor = "class='toDayColor'";
												}
												else
												{
													todayColor="";
												}
							%>
<%-- 													<td <%=todayColor%>><%=dispDay%><br></td> --%>
													<td <%=todayColor%>><%=dispDay%></td>
							<%
												count += 1;
												dispDay += 1;
											}
											else
											{
							%>
													<td class="empty">&nbsp;</td>
							<%
											}
										}
									}
							%>
									</tr>
							<%
								}
							%>
						</table>
					</td>
				</tr>
			</table>
			<form method="get" action="<%=request.getContextPath() %>/02/calendar.jsp">
			<input type="text" placeholder="☆☆☆년☆☆☆" id="yeartext" name="wYear" value="<%=currYear %>"/>
			<input type="text" id="monthtext" name="wMonth" value="<%=currMon %>"/>
			<button type="submit" id="warp">이동</button>
		</form>
		</div>
	</body>
</html>

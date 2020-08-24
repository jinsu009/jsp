package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.or.ddit.utils.TemplateUtils;
import kr.or.ddit.utils.TemplateUtils.VariableVO;

@WebServlet("/gugudan.do")
public class GugudanServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// 2020.05.07
		
		// 3-1. 데이터 검증 
		StringBuffer msg = new StringBuffer();
		boolean valid = validate(req, msg);
		if(!valid) {
			// 검증에 통과하지 못한 경우 
			// 검증에따른 메세지, 응답상태코드 (resp) 
			
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, msg.toString());	
			
			return;
		}
				
		// 3. 최소단과 최대단 두개의 파리미터 받기
		String min = req.getParameter("min");
		String max = req.getParameter("max");
		
		System.out.println("최대값 최소값 "+min+","+max);
		
		// 받아온 데이터를 검증하기 위해 담아준다
		// 만약 건너온 데이터가 null이라면 parse를 못하므로 한번 담아주고 다음으로 넘어간다.
		
		int mindan = Integer.parseInt(min);
		int maxdan = Integer.parseInt(max);
		
		// 4. makedata의 최종데이터 생성 
		
		
		//--------------------------
		// 테이블 태그를 사용해서 , 한 행에 한단씩 구구단을 출력 (2~9단 까지, 1~9승수)
		// 소스파일을 두개나 하나를 사용 
		// 템플릿으로 분류해야할 코드와 아닌 코드를 확인 
		
		StringBuffer data = makeData(mindan, maxdan);
		
		// 가지고 있는 타입의 형태를 모르면 object를 준다. 
		Map<String, Object> dataMap = new LinkedHashMap<>();
		dataMap.put("dan", data);
		dataMap.put("now", new Date());
		
//		String html = TemplateUtils.makeHTML("kr/or/ddit/servlet02/gugudanTemplate.tmpl", dataMap);
		String html = TemplateUtils.makeHTML("gugudanTemplate.tmpl", dataMap);

		try(
			PrintWriter out = resp.getWriter();
		){
			out.print(html);
		}
	}
	private boolean validate(HttpServletRequest req, StringBuffer msg) {
		boolean valid = true;
		// 필수 파라미터 전송여부 
		String min = req.getParameter("min");
		String max = req.getParameter("max");
		
		if( min==null || min.isEmpty() || max==null || max.isEmpty()) {
			valid = false;
			msg.append("필수데이터 누락");
		}
		
		// 숫자 여부  
		if(valid && (!min.matches("[2-9]") || !max.matches("[2-9]"))) {
			valid = false;
			msg.append("입력데이터 오류");
		}
		
		return valid;
	}
	
	// 텍스트 형식으로 작성된 tmpl을 불러오고 그걸 html에 담아준다. 
	public StringBuffer makeData(int mindan, int maxdan) {
		StringBuffer data = new StringBuffer();
		String format = "<td> %d * %d = %d </td>";
		for(int dan=mindan; dan<=maxdan; dan++) {
			data.append("<tr>");
			for(int mul=1; mul<=9; mul++) {
				data.append(String.format(format, dan, mul, dan*mul));
			}
			data.append("</tr>");
		}
		return data;
	}
}

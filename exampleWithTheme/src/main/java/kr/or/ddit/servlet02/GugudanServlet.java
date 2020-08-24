package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.TemplateUtils;


@WebServlet("/gugudan.do")
public class GugudanServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// table 태그 사용. 한행에 한단씩 구구단 출력. 2~9단, 1~9승수
//		4. 검증
		StringBuffer msg = new StringBuffer();
		boolean valid = validate(req, msg);
		if(!valid) {
			// 검증 실패
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, msg.toString());
			return;
		}
//		3. 최소단과 최대단 파라미터 확보.
		String minStr = req.getParameter("minDan");
		String maxStr = req.getParameter("maxDan");
		
//		5. makeData 에서 최종 데이터 생성.
		int minDan = Integer.parseInt(minStr);
		int maxDan = Integer.parseInt(maxStr);
		
		StringBuffer data = makeData(minDan, maxDan);
		Map<String, Object> dataMap = new LinkedHashMap<>();
		dataMap.put("trTags", data);
		dataMap.put("now44", new Date());
		String html = TemplateUtils.makeHTML("/kr/or/ddit/servlet02/gugudan.tmpl", dataMap);
		
		try(
			PrintWriter out = resp.getWriter();
		){
			out.println(html);
		}
	}

	private boolean validate(HttpServletRequest req, StringBuffer msg) {
		 boolean valid = true;
		// 필수 파라미터 전송 여부
		String min = req.getParameter("minDan"); 
		String max = req.getParameter("maxDan");
		if(min==null || min.isEmpty() || max==null || max.isEmpty()) {
			valid = false;
			msg.append("필수 데이터 누락");
		}
		// 숫자 여부
		if(valid && (!min.matches("[2-9]") || !max.matches("[2-9]"))) {
			valid = false;
			msg.append("입력 데이터 오류"); 
		}
		return valid;
	}
	
	private StringBuffer makeData(int minDan, int maxDan) {
		StringBuffer data = new StringBuffer();
		String format = "<td>%d*%d=%d</td>";
		for(int dan =minDan ; dan<=maxDan; dan++) {
			data.append("<tr>");
			for(int mul = 1; mul<=9; mul++) {
				data.append(String.format(format, dan, mul, dan*mul));
			}
			data.append("</tr>");
		}
		return data;
	}

}

package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.or.ddit.enums.OperatorType;
import kr.or.ddit.enums.OperatorType.Operator;


//@WebServlet("/01/calculate.do")
@WebServlet(urlPatterns="/01/calculate.do")

public class CalculateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// http  서비스에 상관없이 처리 하기 위해 서비스로 받는다. 
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 받아오고 
		// 검증하고 ,, 숫자2개, 연산자 4가지중 하나만,
		// 다중조건문,
		// 연산자는 어떤경우에도 하나만 받아와야한다. 
		// 연산자를 상수로 받아야한다. 
		// ENUM 문법 사용 
		
		// 데이터 가져오기
		String right = request.getParameter("right");
		String left = request.getParameter("left");
		String radio = request.getParameter("operator");
		
		// 데이터 검증 
		StringBuffer msg = new StringBuffer();
		OperatorType opt = validate(request, msg);
		if(opt == null) {
			
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg.toString());	
			return;
		}
		
		
		int right01 = Integer.parseInt(right);
		int left01 = Integer.parseInt(left);
		
		int result = opt.operate(left01,right01);
		char sign = opt.getSign();
		
		System.out.println(left01 +" , "+ right01+","+sign);
		
		StringBuffer html =  new StringBuffer("<html>");
		html.append("<body>");
		html.append(String.format("<h5>%d %s %d = %d</h5>", left01, sign, right01, result));
		html.append("</body>");
		html.append("</html>");
		
		try(
				PrintWriter out = response.getWriter();
			){
				out.print(html);
			}
	}
	
	private OperatorType validate(HttpServletRequest request, StringBuffer msg) {
		OperatorType operator = null;
		boolean valid = true;
		// 필수 파라미터 전송여부 
		String right = request.getParameter("right");
		String left = request.getParameter("left");
		String radio = request.getParameter("operator");
		int statusCode = 0;
		
		if( right==null || right.isEmpty() || left==null || left.isEmpty()) {
			valid = false;
			msg.append("필수데이터 누락");
		}
		
		// 숫자 여부  
		if(valid && (!right.matches("[2-9]") || !left.matches("[2-9]"))) {
			valid = false;
			msg.append("입력데이터 오류");
		}
		if(valid) {
			try {
				operator = OperatorType.valueOf(radio);
				
			}catch(Exception e) {
				statusCode = HttpServletResponse.SC_BAD_REQUEST;
			}
			
		}
		return operator;
	}
	
//	public StringBuffer calData(int right, int left, String radio) {
//		StringBuffer data = new StringBuffer();
//		String format = "<div> %d </div>";
//		
//		if(radio.equals("plus")) {
//			data.append(String.format(format, right+left));
//		}
//		if(radio.equals("minus")) {
//			data.append(String.format(format,  right-left));
//		}
//		if(radio.equals("multi")) {
//			data.append(String.format(format, right*left));
//		}
//		if(radio.equals("divide")) {
//			data.append(String.format(format, right/left));
//		}
//		return data;
//	}

}

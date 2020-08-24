package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.OperatorType;


@WebServlet(urlPatterns="/01/calculate.do")
public class CalculateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String leftStr = request.getParameter("left");
		String rightStr = request.getParameter("right");
		String opParam = request.getParameter("operator");
		int statusCode = 0;
		if(StringUtils.isBlank(leftStr) || StringUtils.isBlank(rightStr)
				|| !StringUtils.isNumeric(leftStr) 
				|| !StringUtils.isNumeric(rightStr)
				|| StringUtils.isBlank(opParam)) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		OperatorType operator = null;
		try {
			operator = OperatorType.valueOf(opParam);
		}catch (Exception e) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		if(statusCode!=0) {
			response.sendError(statusCode);
			return;
		}
		
		double left = Double.parseDouble(leftStr);
		double right = Double.parseDouble(rightStr);
		double result = operator.operate(left, right);
		char sign = operator.getSign();
		
		StringBuffer html = new StringBuffer("<html>");
		html.append("<body>");
		html.append(String.format("<h4>%.2f %s %.2f = %.2f", left, sign, right, result));
		html.append("</body>");
		html.append("</html>");
		try(
			PrintWriter out = response.getWriter();	
		){
			out.println(html);
		}
	}
}









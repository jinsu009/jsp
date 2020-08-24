package kr.or.ddit.servlet03.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.servlet03.dao.GradeLicenseDAOImpl;
import kr.or.ddit.servlet03.dao.IGradeLicenseDAO;
import kr.or.ddit.servlet03.service.DDITStudentServiceImpl;
import kr.or.ddit.servlet03.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;

//@WebServlet("/ddit/studentUpdate.do")
@CommandHandler
public class DDITStudentUpdateServlet{
	private static final long serialVersionUID = 1L;
    
	IDDITStudentService service = DDITStudentServiceImpl.getInstance();
	IGradeLicenseDAO gradedao = GradeLicenseDAOImpl.getInstance();
	
	private void attributeSetting(HttpServletRequest request){
		request.setAttribute("gradeList", gradedao.selectGradeList());
		request.setAttribute("licenseList", gradedao.selectLicenseList());
	}
	
	@URIMapping("/ddit/studentUpdate.do")
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		attributeSetting(request);
		String code = request.getParameter("code");
		if(StringUtils.isBlank(code)) {
			response.sendError(400,"필수 파라미터 누락");
			return null;
		}
		DDITStudentVO vo = service.readStudent(code);
		request.setAttribute("student", vo);
		return "ddit/registForm";
//		request.getRequestDispatcher("/WEB-INF/views/ddit/registForm.jsp").forward(request, response);
		
	}
	
	@URIMapping(value="/ddit/studentUpdate.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 자격증정보는 기존의 정보를 삭제하고 다시 update
		// 1. jsp에서 가져온 값을 가공하기 위해 dao로 전송 
		
		attributeSetting(req);
		
		req.setCharacterEncoding("UTF-8");
		DDITStudentVO vo = new DDITStudentVO();
		req.setAttribute("newStudent", vo);
		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			BeanUtils.populate(vo, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return null;
		}
		Map<String, String> errors = new LinkedHashMap<>();

		boolean valid = vaildate(vo, errors);
		req.setAttribute("errors", errors);
		String goPage = null;

		String message = null;

		if(valid) {
			// 검증 통과
			// 저장
			ServiceResult result = service.modifyStudent(vo);
			if (ServiceResult.OK.equals(result)) {
				// 성공한 정보를 담아서 넘겨준다.
				// vo 또는 vo안에 있는 식별코드
				goPage = "redirect:/ddit/dditStudents.do";
				HttpSession session = req.getSession();
				session.setAttribute("lastStudent", vo);

			} else {
				message = "조금 뒤에 다시 해보세요";
				goPage = "ddit/registForm";
			}
		} else {
			// 검증 실패
			// web-inf : dispatch.forward
			goPage = "ddit/registForm";
			// scope를 통해 데이터를 공유
		}

		req.setAttribute("message", message);
		
		return goPage;

//		if (redirect) {
//			// redirect 클라이언트 측에서 이동하는 페이지 경로 앞에는 contextpath가 있어야 한다.
//			resp.sendRedirect(req.getContextPath() + goPage);
//		} else {
//			req.getRequestDispatcher(goPage).forward(req, resp);
//		}
	}
	
	private boolean vaildate(DDITStudentVO vo, Map<String, String> errors) {
		boolean valid = true;

		if (StringUtils.isBlank(vo.getName())) {
			valid = false;
			errors.put("name", "이름 누락");
		}
		if (vo.getAge() > 40) {
			valid = false;
			errors.put("age", "연령 제한");
		}
		if (StringUtils.isBlank(vo.getGender()) || !vo.getGender().matches("[FM]")) {
			valid = false;
			errors.put("gender", "성별 확인");
		}
		if (StringUtils.isNoneBlank(vo.getBirthday())) {
			try {
				new SimpleDateFormat("yyyy-MM-dd").parse(vo.getBirthday());
			} catch (ParseException e) {
				valid = false;
				errors.put("birthday", "생일 형식 확인");
			}
		}
		return valid;
	}

}

package kr.or.ddit.servlet03.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
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
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.DDITStudentVO;

//@WebServlet("/ddit/studentUpdate.do")
@CommandHandler
public class DDITStudentUpdateController{
	private static final long serialVersionUID = 1L;
    
	IDDITStudentService service = DDITStudentServiceImpl.getInstance();
//	IGradeLicenseDAO gradedao = GradeLicenseDAOImpl.getInstance();
	IGradeLicenseDAO gradedao = new GradeLicenseDAOImpl();
	
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
		
		CommonValidator<DDITStudentVO> validator = new CommonValidator<>();
		Map<String, List<String>> errors = validator.validate(vo,UpdateGroup.class);
		req.setAttribute("errors", errors);
		
		String goPage = null;
		String message = null;

		if(errors.size()==0) {
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
	}

}

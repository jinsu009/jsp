package kr.or.ddit.student.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.HttpMethod;
import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.student.dao.GradeLicenseDAOImpl;
import kr.or.ddit.student.dao.IGradeLicenseDAO;
import kr.or.ddit.student.service.DDITStudentServiceImpl;
import kr.or.ddit.student.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;


@CommandHandler
public class DDITStudentUpdateController{
	IDDITStudentService service = DDITStudentServiceImpl.getInstance();
	IGradeLicenseDAO dao = GradeLicenseDAOImpl.getInstance();
	 
	private void attibuteSetting(HttpServletRequest req){
		req.setAttribute("gradeList", dao.selectGradeList());
		req.setAttribute("licenseList", dao.selectLicenseList());
		req.setAttribute("currentAction", "/ddit/studentUpdate.do");
	}

	@URIMapping("/ddit/studentUpdate.do")
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		attibuteSetting(request);
	 	String code = request.getParameter("code");
	 	if(StringUtils.isBlank(code)) {
	 		response.sendError(400, "필수 파라미터 누락");
	 		return null;
	 	}
	 	DDITStudentVO vo = service.readStudent(code);
	 	request.setAttribute("student", vo);
	 	return "ddit/registForm";
	}
	
	@URIMapping(value="/ddit/studentUpdate.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		attibuteSetting(req);
		DDITStudentVO vo = new DDITStudentVO();
		req.setAttribute("student", vo);
		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			BeanUtils.populate(vo, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return null;
		}
//		3. 검증 : 이름, 생일, 나이, 성별
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(vo, errors);
		String goPage = null;
		String message = null;
		if(valid) {
//		4-1. 검증 통과
//			저장
			ServiceResult result = service.modifyStudent(vo);
			if(ServiceResult.OK.equals(result)) {
				// 성공 : /ddit/dditStudents.do(Model2) ,  Redirect -> Get (PRG)
				goPage = "redirect:/ddit/studentView.do?code="+vo.getCode();
			}else {
				message = "쫌따 다시 해보셈.";
				goPage = "ddit/registForm";
			}
		}else {
//		4-2. 불통
			// 실패 : 필수 파라미터 누락, 기존 입력 데이터, 메시지
//			registForm 이동( VO, message  공유)
			// scope 를 통해 데이터를 공유.
			goPage = "ddit/registForm";
		}
		req.setAttribute("message", message);
		return goPage;
	}
	
	private boolean validate(DDITStudentVO vo, Map<String, String> errors) {
		boolean valid = true;
		if(StringUtils.isBlank(vo.getCode())) {
			valid = false;
			errors.put("code", "학번 누락");
		}
		if(StringUtils.isBlank(vo.getName())) {
			valid = false;
			errors.put("name", "이름 누락");
		}
		if(vo.getAge() > 40 || vo.getAge() < 15) {
			valid = false;
			errors.put("age", "연령 제한");
		}
		if(StringUtils.isBlank(vo.getGen()) || !vo.getGen().matches("[FM]")) {
			valid = false;
			errors.put("gen", "성별 확인");
		}
		if(StringUtils.isNotBlank(vo.getBirthday())) {
			// 2020-05-13
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

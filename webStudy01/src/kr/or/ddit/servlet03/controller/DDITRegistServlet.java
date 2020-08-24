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
import kr.or.ddit.servlet03.dao.DDITStudentDAO;
import kr.or.ddit.servlet03.dao.GradeLicenseDAOImpl;
import kr.or.ddit.servlet03.dao.IDDITStudentDAO;
import kr.or.ddit.servlet03.dao.IGradeLicenseDAO;
import kr.or.ddit.servlet03.service.DDITStudentServiceImpl;
import kr.or.ddit.servlet03.service.IDDITStudentService;
import kr.or.ddit.vo.DDITStudentVO;

@WebServlet("/ddit/regist.do")
public class DDITRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IDDITStudentService service = DDITStudentServiceImpl.getInstance();
	IGradeLicenseDAO gradedao = GradeLicenseDAOImpl.getInstance();

	private void attributeSetting(HttpServletRequest request){
		request.setAttribute("gradeList", gradedao.selectGradeList());
		request.setAttribute("licenseList", gradedao.selectLicenseList());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		attributeSetting(request);

		// 가입을 하기 위해서 webservlet에 작성된 주소를 기재해 준다.
		// sampleform 에서 응답데이터가 나갈수 있도록 이동해야 하고 이동방식을 정해준다. > dispatch방식
		request.getRequestDispatcher("/WEB-INF/views/ddit/registForm.jsp").forward(request, response);

		// doGet > dispatch forward구조를 이용해서 jsp로 정보 전달
	}

	// has a 관계 , 의존관계 : servlet에서 dao객체 생성 후 가져다 사용
	IDDITStudentDAO dao = DDITStudentDAO.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 가입 처리가 이루어진다.
		// 디코딩, 검증,
		// 가입 성공 -> index.jsp (redirect방식 ) > get방식으로 전송
		// 가입 실패 -> registForm.jsp 로 이동 > 필수 파라미터가 빠졌을 경우 이름, 생년월일, 전화번호 누락,
		// > 기존의 입력데이터를 가지고 form으로 이동해야 한다. (req가 가지고 있음 ) > 이동방식은 dispatch
		// > message도 갖고 있어야한다.

		// dao.. vo를 DB에 넣기 위해 dao를 만들어 줘야 한다.
		// vo .. dao에게 데이터를 전송할 그릇 만들기 , 객체를 주고 받을 떄 이용

		// 클래스 다이어그램 그려오기
		// =========================
		// 1. dao 와 객체간의 의존관계 형셩
		// 2. 파라미터를 받기위한 디코딩
		// 3. 파라미터 확보 : vo생성
		// 4. 검증 : 대상 = vo
		// rule : 이름,생일, 나이, 성별
		// 4-1. 검증에 통과 하는 경우 >
		// 저장
		// 등록완료 ,(/ddit/dditStudents.do > model2) , redirect > get(PRG)
		// 학생이입력한 모든 데이터를 보여주는 페이지로 이동 vo의 데이터를 가져갈 필요가 없다.
		// 4-2. 검증에 통과 하지 못한 경우 > registForm.jsp로 이동( vo전달 , message 전달 ) --> 이부분 제외하고
		// 코드 작성

		/*
		 * // registFrom.jsp 에서 데이터 가져오기 
		 * String name = req.getParameter("name"); 
		 * String birthStr = req.getParameter("birthday"); 
		 * // 날짜데이터 여부 
		 * String ageStr =req.getParameter("age"); 
		 * // int 형으로 저장 
		 * String gender = req.getParameter("gen"); 
		 * String grade = req.getParameter("grade"); 
		 * String career = req.getParameter("career"); 
		 * String[] license = req.getParameterMap().get("license");
		 * 
		 * // 검증 
		 * if(StringUtils.isBlank(name) || StringUtils.isBlank(birthStr) ||
		 * StringUtils.isBlank(ageStr)|| StringUtils.isNumeric(ageStr) ||
		 * StringUtils.isBlank(gender)) { 
		 * // 검증에 실패했을 때 
		 * System.out.println("검증실패 "); }
		 * else { // 검증에 성공했을 때 // 나이는 int로 형변환
		 *  int ageInt = Integer.parseInt(ageStr);
		 * 
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		 * String birthDate = sdf.format(birthStr);
		 * 
		 * // vo에 데이터 set 시켜주기 
		 * vo.setName(name); 
		 * vo.setBirthday(birthDate);
		 * vo.setAge(ageInt); 
		 * vo.setGender(gender); 
		 * vo.setCareer(career);
		 * vo.setLicense(license);
		 * 
		 * // dao에 vo 넘겨줘서 남은 code 부여 dao.insertStudent(vo);
		 * 
		 * // 페이지 전환 
		 * // resp.sendRedirect(req.getContextPath() + "/01/DDITStudentReadServlet.jsp"); 
		 * resp.sendRedirect("/webStudy01/src/kr/or/ddit/servlet03/DDITStudentReadServlet.java");
		 * 
		 * }
		 */

		// 선생님
		attributeSetting(req);

		// Decoding
		req.setCharacterEncoding("UTF-8");

		DDITStudentVO vo = new DDITStudentVO();
		req.setAttribute("newStudent", vo);

		// 코드를 간결화 시키기 위해 lib 나 framework 필요
		Map<String, String[]> parameterMap = req.getParameterMap();
		/*
		 * // key 만 담겨진 iterator 생성
		 *  Iterator<String> pNames = parameterMap.keySet().iterator(); 
		 *  while (pNames.hasNext()) {
		 *   String paramName= (String) pNames.next(); 
		 *   try { 
		 *   Field field =DDITStudentVO.class.getDeclaredField(paramName); 
		 *   // field 와는 다르게 declared는 private으로 선언된 값도 가져온다.
		 * // private으로 묶여 있기 때문에 값은 바뀌지 않는다. 
		 * field.setAccessible(true); // 접근 불가능한 필드를접근 가능하게 만들어 준다.
		 * 
		 * field.set(vo, req.getParameter(paramName));
		 * 
		 * } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
		 * | IllegalAccessException e) 
		 * { continue; // 예외가 발생하면 그다음 으로 계속 진행 
		 * } }//reflection : 실시간 최적화 -- 보강 // 전역변수 == property == field
		 * 
		 */
		// --------------------------------------------------------
		// beanUtils framework 사용
		try {
			BeanUtils.populate(vo, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return;
		}

		System.out.println(vo);

		// 검증
		// 누적된 error 메세지를 저장하고 출력할수 있도록 map을 사용
		Map<String, String> errors = new LinkedHashMap<>();

		boolean valid = vaildate(vo, errors);
		req.setAttribute("errors", errors);
		String goPage = null;
		boolean redirect = false;

		String message = null;

		if(valid) {
			// 검증 통과
			// 저장

			ServiceResult result = service.createStudent(vo);

			if (ServiceResult.OK.equals(result)) {
				// 성공한 정보를 담아서 넘겨준다.
				// vo 또는 vo안에 있는 식별코드
				goPage = "/ddit/dditStudents.do";
				redirect = true; // 정보를 reset하고 보낸다.
				HttpSession session = req.getSession();
				session.setAttribute("lastStudent", vo);

			} else {
				message = "조금 뒤에 다시 해보세요";
				goPage = "/WEB-INF/views/ddit/registForm.jsp";
			}
		} else {
			// 검증 실패
			// web-inf : dispatch.forward
			goPage = "/WEB-INF/views/ddit/registForm.jsp";
			// scope를 통해 데이터를 공유
		}

		req.setAttribute("message", message);

		if (redirect) {
			System.out.println("성공부붐");
//			service.createStudent(vo);
			
			// redirect
			// 클라이언트 측에서 이동하는 페이지 경로 앞에는 contextpath가 있어야 한다.
			resp.sendRedirect(req.getContextPath() + goPage);
		} else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
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
			// 생일데이터가입력이 됐으면..
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

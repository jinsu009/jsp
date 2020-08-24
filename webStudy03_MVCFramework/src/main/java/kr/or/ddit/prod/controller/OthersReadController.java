package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OtherDAOImpl;
import kr.or.ddit.vo.BuyerVO;

@CommandHandler
public class OthersReadController {
	// json 데이터로 마샬링해서 내보내기

	IOthersDAO otherDAO = new OtherDAOImpl();

	@URIMapping("/prod/getBuyerList.do")
	public String getBuyerList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String prod_lgu = req.getParameter("prod_lgu");
		List<BuyerVO> buyerList = otherDAO.selectBuyerList(prod_lgu);

		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = resp.getWriter();) {
			mapper.writeValue(out, buyerList);
		}

		return null;
	}

	@URIMapping("/prod/getLprodList.do")
	public String getLprodList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Map<String, Object>> lprodList = otherDAO.selectLprodList();
		// 마샬링
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json;charset=UTF-8"); // MIME
		try (PrintWriter out = resp.getWriter();) {
			mapper.writeValue(out, lprodList);
		}
		return null;
	}
}

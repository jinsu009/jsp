package kr.or.ddit.prod.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.stereotype.CommandHandler;
import kr.or.ddit.mvc.stereotype.URIMapping;
import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.vo.BuyerVO;

@CommandHandler
public class OthersReadController {
	IOthersDAO othersDAO = new OthersDAOImpl();
	
	@URIMapping("/prod/getBuyerList.do")
	public String getBuyerList(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String prod_lgu = req.getParameter("prod_lgu");
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(prod_lgu);
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();
		){
			mapper.writeValue(out, buyerList);
		}
		return null;		
	}
	@URIMapping("/prod/getLprodList.do")
	public String getLprodList(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();
		){
			mapper.writeValue(out, lprodList);
		}
		return null;
	}
}





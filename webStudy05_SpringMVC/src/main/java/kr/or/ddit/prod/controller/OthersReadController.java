package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.vo.BuyerVO;
//20-06-12
@RestController
@RequestMapping(value="/prod",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OthersReadController {
	// json 데이터로 마샬링해서 내보내기
	
	@Inject
	IOthersDAO otherDAO;

	@GetMapping(value = "getBuyerList.do")
	public List<BuyerVO> getBuyerList(
			@RequestParam(required = false) String prod_lgu
			) throws IOException {
		List<BuyerVO> buyerList = otherDAO.selectBuyerList(prod_lgu);
		return buyerList;
	}

	@GetMapping(value = "getLprodList.do")
	public List<Map<String, Object>> getLprodList() {
		List<Map<String, Object>> lprodList = otherDAO.selectLprodList();
		return lprodList;
	}
}

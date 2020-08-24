package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Service
public class ProdServiceImpl implements IProdService {

	@Inject
	IProdDAO prodDAO;

	@Inject
	WebApplicationContext container;
	ServletContext application;

	// root-context 에서 불러오는 이미지 저장경로 
	@Value("#{appInfo.prodImagePath}")
	String prodImagePath;
	
	File saveFolder;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
		String folderPath = application.getRealPath(prodImagePath);
		saveFolder = new File(folderPath);
		if (!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
	}

	private void processImage(ProdVO prodvo) {
		// 이미지 저장
		try {
			MultipartFile prod_image = prodvo.getProd_image();
			if (prod_image != null) {
				prodvo.saveFile(saveFolder);
			}
		} catch (IOException e) {
			throw new RuntimeException();

		}
	}

	@Transactional // 두개의 메소드를 실행시킬때 적용시키는 annotation
	@Override
	public ServiceResult createProd(ProdVO prod) {
		int rowcnt = prodDAO.insertProd(prod);
		processImage(prod);
		ServiceResult result = ServiceResult.FAIL;
		if (rowcnt > 0)
			result = ServiceResult.OK;
		return result;
	}

	@Override
	public ProdVO readProd(String prod_id) {
		ProdVO prod = prodDAO.selectProd(prod_id);
		if (prod == null) {
			throw new DataNotFoundException(prod_id + "상품없음");
		}
		return prod;
	}

	@Override
	public int readProdCount(PagingVO<ProdVO> pagingVO) {
		return prodDAO.selectProdCount(pagingVO);
	}

	@Override
	public List<ProdVO> readProdList(PagingVO<ProdVO> pagingVO) {
		return prodDAO.selectProdList(pagingVO);
	}

	// 선언적 프로그램 : annotation 하나만 붙여서 설정을 완료함 
	
	@Transactional
	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		readProd(prod.getProd_id());
		int cnt = prodDAO.updateProd(prod);
		processImage(prod);
		ServiceResult result = ServiceResult.FAIL;
		if (cnt > 0)
			result = ServiceResult.OK;
		return result;
	}
}

package kr.or.ddit.preparer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.vo.MenuVO;

// 20-06-13
public class ProdViewPreparer implements ViewPreparer{

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		// 동적메뉴구성 만들기 
		MenuVO menu1 = new MenuVO();
		menu1.setUrl("/prod/prodList.do");
		menu1.setText("상품 목록조회");
		
		
		MenuVO menu2 = new MenuVO();
		menu2.setUrl("/prod/prodInsert.do");
		menu2.setText("상품 신규등록");
		
		List<MenuVO> menuList = new ArrayList<MenuVO>();
		menuList.add(menu1);
		menuList.add(menu2);
		Map<String, Object > scope = tilesContext.getContext(Request.REQUEST_SCOPE);
		scope.put("menuList", menuList);
	}

}

package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;

@Data
public class PagingVO<T> {
	public PagingVO() {
		super();
	}
	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	
	private int screenSize = 10;
	private int blockSize = 5;
	private int currentPage;
	private int totalRecord;
	
	private int totalPage;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	private List<T> dataList;
	
	private SearchVO searchVO;
	private T detailSearch;
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = (int)Math.ceil(totalRecord/(double)screenSize);
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		this.endRow = currentPage * screenSize;
		this.startRow = endRow - (screenSize - 1);
		this.startPage = blockSize * ((currentPage-1)/blockSize) + 1;
		this.endPage = startPage + (blockSize - 1);
	}
	
	String blockPattern = 
			"<li class='page-item %1$s'>\r\n" + 
			"      <a data-page='%2$d' class='page-link' href='?page=%2$d' tabindex='-1'>%3$s</a>\r\n" + 
			"    </li>";
	String pagePattern = "<li class='page-item'><a data-page='%1$d' class='page-link' href='?page=%1$d'>%2$d</a></li>"; 
			
	public String getPagingHTML() {
		if(endPage>totalPage) {
			endPage = totalPage;
		}
		StringBuffer html = new StringBuffer("<ul class='pagination'>");
		String liClass = startPage>blockSize?"":"disabled";
		html.append(
			String.format(blockPattern, liClass, startPage - blockSize, "previous")
		);
		for(int page = startPage; page <= endPage; page++) {
			if(page == currentPage) {
				html.append("<li class='page-item active' aria-current='page'>");
				html.append(String.format("<a class='page-link' data-page='%1$d' href='#'>%1$d<span class='sr-only'>(current)</span></a>", page));
				html.append("</li>");
			}else {
				html.append(
					String.format(pagePattern, page, page)
				);
			}
		}
		liClass = endPage < totalPage?"":"disabled";
		html.append(
			String.format(blockPattern, liClass, endPage+1, "next")
		);
		html.append("</ul>");
		return html.toString();
	}
}









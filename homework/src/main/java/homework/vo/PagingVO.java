package homework.vo;

import java.util.List;

// 2020-05-25
public class PagingVO<T> {

	public PagingVO() {
		super();
	}
	public PagingVO(int screenSize,int blockSize) {
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
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = (int)Math.ceil(totalRecord/(double)screenSize);
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		this.endRow = currentPage * screenSize;
		this.startRow = endRow - (screenSize - 1);
		this.startPage = blockSize * ((currentPage-1)/blockSize)+1;
		this.endPage = startPage + (blockSize -1);
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
				html.append("<a class='page-link' href='#'>"+page+"<span class='sr-only'>(current)</span></a>");
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

	public int getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(int screenSize) {
		this.screenSize = screenSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	public SearchVO getSearchVO() {
		return searchVO;
	}
	public void setSearchVO(SearchVO searchVO) {
		this.searchVO = searchVO;
	}
	
	
	
}

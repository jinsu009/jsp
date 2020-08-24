package kr.or.ddit.filter.wrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

public class FileUploadRequestWrapper extends HttpServletRequestWrapper{
	
	// key : partname
	private Map<String, List<PartWrapper>> partWrapperMap; 

	public FileUploadRequestWrapper(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		partWrapperMap = new LinkedHashMap<>();
		
		parseRequest(request);
	}

	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		Collection<Part> parts = request.getParts();
		
		Iterator<Part> it = parts.iterator();
		while(it.hasNext()) {
			Part part = (Part) it.next();
			
			// header가 null 이거나 filename 이 없을경우  반복을 계속 한다.
			String header = part.getHeader("Content-Disposition");
			if(header==null || header.indexOf("filename")==-1) {
				continue;
			}
			
			PartWrapper wrapper = new PartWrapper(part);
			String partName = wrapper.getPartName();
			List<PartWrapper> list = partWrapperMap.get(partName); // 기존에 있던 list
			if(list==null) {
				// 기존의 list가 존재하지 않는다. 
				list = new ArrayList<>();
				partWrapperMap.put(partName,list);
			}
			list.add(wrapper);
		}
	}
	
	public PartWrapper getPartWrapper(String partName) {
		List<PartWrapper> list = partWrapperMap.get(partName);
		
		if(list !=null && list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
	public List<PartWrapper> getPartWrappers(String partName){
		return partWrapperMap.get(partName);
	}
	
	public Map<String, List<PartWrapper>> getPartWrappersMap(){
		return partWrapperMap;
	}
	
	
	public Enumeration<String> getPartWrapperNames(){
		final Iterator<String> partNames = partWrapperMap.keySet().iterator();
		// 변수, 상수(final) : 저장범위가 달라진다.
		return new Enumeration<String>() {
			@Override
			public boolean hasMoreElements() {
				return partNames.hasNext();
			}
			
			@Override
			public String nextElement() {
				return partNames.next();
			}
		};
	}
	
}

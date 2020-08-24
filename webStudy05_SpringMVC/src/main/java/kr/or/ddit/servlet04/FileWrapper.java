package kr.or.ddit.servlet04;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;

public class FileWrapper  extends File implements IFileWrapper{
	
	public FileWrapper(File wrapped, ServletContext application) {
		super(wrapped.getAbsolutePath());
		this.name = wrapped.getName();
		this.clzName = wrapped.isDirectory()?"fol":"file";
		String tempPath =
				StringUtils.substringAfter(
						wrapped.getAbsolutePath(), application.getRealPath("/"));
		
		tempPath = StringUtils.prependIfMissing(tempPath, "/");
		
		this.id = tempPath.replace(File.separatorChar, '/');
		
//		System.out.println(id);
	}
	// file은 name, clzName, id 를 가지고 있지 않기 때문에 
	// filewrapper를 만들어서 servlet에서 세가지 요소를 사용할수 있게끔해준다
	private String name;
	private String clzName;
	private String id;
	
	public String getName() {
		return name;
	}
	public String getClzName() {
		return clzName;
	}
	public String getId() {
		return id;
	}
}

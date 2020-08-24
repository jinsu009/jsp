package kr.or.ddit.servlet04;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;

public class FileWrapper extends File implements IFileWrapper {
	public FileWrapper(File wrapped, ServletContext application) {
		super(wrapped.getAbsolutePath());
		this.name = wrapped.getName();
		this.clzName = wrapped.isDirectory()?"folder":"file";
		String tempPath = StringUtils.substringAfter(wrapped.getAbsolutePath(), 
							application.getRealPath("/"));
		tempPath = StringUtils.prependIfMissing(tempPath, "/");
		this.id = tempPath.replace(File.separatorChar, '/');
//		System.out.println(id);
	}
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






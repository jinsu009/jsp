package kr.or.ddit.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.or.ddit.servlet02.GugudanServlet;

public class TemplateUtils {

	//과제 
	public static String makeHTML(String path, Map<String, Object> dataMap) throws IOException {
		String html = readTemplate(path)+"";
//		String html = TemplateUtils.readTemplate("."+path , names)+"";
		
		System.out.println(path);
		
		 html = replaceDataVariables(html, dataMap);
		
		
		// map : key, vlaue 한쌍을 묶어서 > entry		
//		for(Entry<String, Object> entry : dataMap.entrySet()) {
//			String name = entry.getKey();
//			Object value = entry.getValue();
//			
//			VariableVO vvo = names.get(name);
//			if(vvo==null) {
//				continue;
//			}
//			html = html.replace("@"+name, Objects.toString(value,""));
//		}
		return html;
	}
	
	//() : 그룹연산자 
	private static final String ptrn = "@([a-zA-Z]+)"; // 한글자이상의 문자를 가지고 있는 
	
	// html은 string인지 stringbuffer인지 알수 없으므로 charsequence로 받아준다. 
	private static String replaceDataVariables(CharSequence html,Map<String, Object> dataMap ) {
		Pattern pattern = Pattern.compile(ptrn);
		Matcher matcher = pattern.matcher(html);
		StringBuffer result = new StringBuffer();	// 치환을 하기위해 문자를 누적시킬 객체를 줘야한다.
		while(matcher.find()) {
			String name = matcher.group(1);
			Object data = dataMap.get(name);
			matcher.appendReplacement(result, Objects.toString(data,""));
			// data가 null일 경우를 예방해서 objects로 데이터를 받고 data가 null일경우 공백으로 처리 
		}
		matcher.appendTail(result);
		return result.toString(); 
	}
	
	
	public static StringBuffer readTemplate(String path) throws IOException {
		StringBuffer template = new StringBuffer();
		
		try(
			InputStream is = GugudanServlet.class.getResourceAsStream(path);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is))
		){
			String tmp = null;
			while((tmp=reader.readLine())!=null) {
				template.append(tmp + "\n");
				System.out.println("tmp >> " +tmp);
			}
		}
		return template;
	}

	public static Map<String, VariableVO> findVariables(CharSequence text,
			Map<String, VariableVO> names){
	
		
		Pattern regex = Pattern.compile(ptrn);
		Matcher matcher = regex.matcher(text);
		
		while( matcher.find() ) {
			String variableName = matcher.group(1); //변수 하나의 이름이 돌아온다.
			
			int start = matcher.start();
			int end = start + (variableName.length()+1);
			
			names.put(variableName, new VariableVO(variableName, start, end));
			
			System.out.println("variableName >> " + variableName +",");
		}
		return names;
	}
	
	public static class VariableVO{
		//변수의 이름과 위치데이터 , start, end 
		private String varName;
		private int start;
		private int end;
		
		public VariableVO(String varName, int start, int end) {
			super();
			this.varName = varName;
			this.start = start;
			this.end = end;
		}

		public String getVarName() {
			return varName;
		}

		public int getStart() {
			return start;
		}

		public int getEnd() {
			return end;
		}
		@Override
		public String toString() {
			return "VariableVO [varName=" + varName + ", start=" + start + ", end=" + end + "]";
		}
	}
}

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

public class TemplateUtils {
	public  static String makeHTML(String path, Map<String, Object> dataMap) throws IOException {
		String html = readTemplate(path)+"";
		html = replaceDataVariables(html, dataMap);
//		for(Entry<String, Object> entry : dataMap.entrySet()) {
//			String name = entry.getKey();
//			Object value = entry.getValue();
//			VariableVO dataVariable = names.get(name);
//			if(dataVariable==null) continue;
//			html = html.replace("@"+name, Objects.toString(value, ""));
//		}		
		return html;
	}
	
	private static final String ptrn = "@([a-zA-Z]+)";
	
	private static String replaceDataVariables(CharSequence html, Map<String, Object> dataMap) {
		Pattern pattern = Pattern.compile(ptrn);
		Matcher matcher = pattern.matcher(html);
		StringBuffer result = new StringBuffer();
		while(matcher.find()) {
			String name = matcher.group(1);
			Object data = dataMap.get(name);
			matcher.appendReplacement(result, Objects.toString(data, ""));
		}
		matcher.appendTail(result);
		return result.toString();
	}
	
	public static StringBuffer readTemplate(String path) throws IOException{
		StringBuffer template = new StringBuffer();
		try(
			InputStream is = TemplateUtils.class.getResourceAsStream(path);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));	
		){
			String temp = null;
			while((temp = reader.readLine()) != null) {
				template.append(temp  + "\n");
			}
		}
		return template;
	}
	
	public static class VariableVO{
		// 변수의 이름과 위치 데이터, start, end
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
	
	public static Map<String, VariableVO> findVariables(CharSequence text, Map<String, VariableVO> names){
		Pattern regex = Pattern.compile(ptrn);
		
		Matcher matcher = regex.matcher(text);
		while( matcher.find() ) {
			String variableName = matcher.group(1);
			int start = matcher.start();
			int end = start + (variableName.length()+1);
			names.put(variableName,  new VariableVO(variableName, start, end));
		}
		return names;
	}
}




















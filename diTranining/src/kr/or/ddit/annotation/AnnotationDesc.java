package kr.or.ddit.annotation;

import java.util.*;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import kr.or.ddit.annotation.stereotype.CommandHandler;
import kr.or.ddit.annotation.stereotype.SecondSingleValueAnnotation;
import kr.or.ddit.annotation.stereotype.URIMapping;
import kr.or.ddit.annotation.stereotype.URIMapping.HttpMethod;
import kr.or.ddit.utils.ReflectionUtils;

/**
 * comment 주석 : 대상자 > 사람
 * annotation 주석 (골뱅이모양) : 대상자 > 사람 + 시스템(컴파일러)
 * 	- 코드에 대한 메타데이터를 대상에게 전달하기 위한 특수한 주석 
 * 	1) marker annotation : annotation이 부착된 타겟을 그룹으로 묶을 때 사용
 * 	FirstMarkerAnnotation
 * 	2) singleValue annotation : value라는 이름의 속성 하나를 가진 경우 
 * 	SecondSingleValueAnnotation
 *  3) multiValue annotation : 여러개의 속성을 가진 경우
 *
 */

// 2020-05-29
public class AnnotationDesc {

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		String basePackage="kr.or.ddit.annotation.sample";
		Class<? extends Annotation> annotationType = CommandHandler.class;

		Map<Class<?>, Annotation > targets =  ReflectionUtils.getClassesWithAnnotationAtBasePackages(annotationType, basePackage);
		System.out.println(targets);
		
		for(Entry<Class<?>, Annotation> entry : targets.entrySet()) {
			Class<?> target = entry.getKey();
			
			Object targetObj = target.newInstance();
			
			annotationType = URIMapping.class;
			
			Map<Method, Annotation> mtdMap = ReflectionUtils.getMethodsWithAnnotationAtClass(target,
					annotationType, String.class, HttpServletRequest.class, HttpServletResponse.class);
			System.err.println(mtdMap);
			
			for(Entry<Method,Annotation> tmp :mtdMap.entrySet()) {
			 	URIMapping annotation =  (URIMapping) tmp.getValue();
			 	
			 	HttpServletRequest req = null;
			 	HttpServletResponse resp = null;
			 	
				String result = (String)tmp.getKey().invoke(targetObj, req, resp);
				System.out.println(result);
		}
		
		
//		Class<?>[] targets = markerAnnotationTracing(basePackage,annotationType);
//		System.out.println(Arrays.toString(targets));
//		for(Class<?> target : targets) {
//			Class<? extends Annotation> mtdType = SecondSingleValueAnnotation.class;
//			Class<? extends Annotation> mtdType = ThirdMultiValueAnnotation.class;
//			Method[] methods = annotationTracingForMethod(target,mtdType);
//			for(Method mtd : methods) {
//				SecondSingleValueAnnotation mtdAnno = (SecondSingleValueAnnotation) mtd.getAnnotation(mtdType);
//				ThirdMultiValueAnnotation mtdAnno = (ThirdMultiValueAnnotation) mtd.getAnnotation(mtdType);
//				System.out.println(mtdAnno.value());
//				System.out.println(mtdAnno.value()+mtdAnno.number());
//			}
		}
	}

	private static Method[] annotationTracingForMethod(Class<?> target, Class<? extends Annotation> mtdType) {
		Method[] methods = null;
		List<Method> mtdList = new ArrayList<>();
		for(Method tmp : target.getDeclaredMethods()) {
			Annotation anno =  tmp.getAnnotation(mtdType);
			if(anno!=null) {
				mtdList.add(tmp);
			}
		}
		methods = new Method[mtdList.size()];
		return mtdList.toArray(methods);
	}

	private static Class<?>[] markerAnnotationTracing(String basePackage, Class<? extends Annotation> annotationType) throws ClassNotFoundException {
		Class<?>[] targets = null;
		String classPath = '/'+basePackage.replace('.', '/');
		String systemPath = AnnotationDesc.class.getResource(classPath).getFile();
		File baseFolder = new File(systemPath);
		String[] children = baseFolder.list((dir,name)->{
			return name.endsWith(".class");
		});
		List<Class<?>> targetList = new ArrayList<>();
		
		System.out.println(targetList.toString());
		
		for(String name : children) {
			int lastIdx = name.lastIndexOf(".class");
			String qulifiedName = name.substring(0,lastIdx);
			
			System.out.println(qulifiedName);
			
			Class<?> target = Class.forName(qulifiedName);
			Annotation annotation = target.getAnnotation(annotationType);
			if(annotation!=null) {
				targetList.add(target);
			}
		}
		targets = new Class<?>[targetList.size()];
		return targetList.toArray(targets);
	}
}

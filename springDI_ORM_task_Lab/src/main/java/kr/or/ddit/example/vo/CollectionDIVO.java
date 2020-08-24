package kr.or.ddit.example.vo;

import java.util.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@ToString
@Getter
public class CollectionDIVO {

	private List<?> list;
	private Set<String> set;
	private Map<String, Date> map;
	private Properties props;
	
	private String[] array;
}

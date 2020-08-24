package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class ResourceVO implements Serializable{
// 자원하나에 대한 정보 , RESOURCES
	
	private String description;
	private Integer sort;
	private String res_id;
	private String res_url;
	
	
	private Set<String> roles;
	
}

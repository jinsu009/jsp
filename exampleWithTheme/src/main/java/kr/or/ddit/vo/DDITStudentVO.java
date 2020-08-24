package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ValueObject-DataTransferObject-Model-JavaBean
 * 1. 값을 가질수 있는 속성(property, field) 
 * 2. 캡슐화된 데이터에 접근할 방법 제공(getter, setter)
 * 		get[set]ProertyName - carmel 표기 방식
 * 3. 상태 확인 메소드 제공 (toString)
 * 4. 객체의 상태 비교 메소드 제공(equals)
 * 5. 직렬화 가능.
 */
public class DDITStudentVO implements Serializable{
	private int rnum;
	private String code; // PK(식별성)
	private String name;
	private String birthday;
	private Integer age;
	private String grade;
	private String gen;
	private List<String> license; // database 용
	private String[] lic_codes; // request parameter 용
	private String career;
	
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public List<String> getLicense() {
		return license;
	}
	public void setLic_codes(String[] lic_codes) {
		this.lic_codes = lic_codes;
	}
	public String[] getLic_codes() {
		return lic_codes;
	}
	public void setLicense(List<String> license) {
		this.license = license;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	@Override
	public String toString() {
		return "DDITStudentVO [name=" + name + ", birthday=" + birthday + ", age=" + age + ", grade=" + grade + ", gen="
				+ gen + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DDITStudentVO other = (DDITStudentVO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	
}

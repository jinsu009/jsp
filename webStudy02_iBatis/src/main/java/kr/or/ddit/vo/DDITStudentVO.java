package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

/**
 * VO (Value Object) - DTO(Data TransferObject) - Model - JavaBean
 * JavaBean 규약 : 이클립스가 자체적으로 가지고 있는 규약 
 * 	1) VO는 값을 가지고 있는 속성이 있어야한다. (속성 = property, field)
 *  2) 캡슐화되어 있는 메소드에 접근할 인터페이스가 필요하다 (캡슐화된 데이터에 접근할 방법 제공) (getter, setter)
 *  	get[set]ProperyName - 'carmel' 표기 방식이 적용된다 - 중간에 단어가 바뀔떄 대문자로 바꿔준다.  
 *  3) 데이터의 상태를 확인할 수 있는 메소드가 필요하다(상태확인 메소드 제공) (toString : 객체 안에 어떤 값이 있는지 확인하고 싶을 때 사용) 
 *  4) 객체의 상태를 비교할수 있는 메소드 제공 해야한다. (equals)
 *  5) 직렬화가 가능해야한다. > Serializable 를 implemenets
 * 
 *  $ 직렬화 , 마샬링 , 언마샬링 
 *
 */

public class DDITStudentVO implements Serializable{
	
	private String code; // DB에서 가져오는 데이터 식별 하기위한 PK (식별성을 가진다.)
	private String name; 
	private String birthday;
	private Integer age;
	private String grade;
	private String gender;
	private List<String> license; // database 용
	private String[] lic_codes; // request parameter 용
	private String career;

	public String[] getLic_codes() {
		return lic_codes;
	}

	public void setLic_codes(String[] lic_codes) {
		this.lic_codes = lic_codes;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<String> getLicense() {
		return license;
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
		return "DDITStudentVO [name=" + name + ", birthday=" + birthday + ", age=" + age + ", grade=" + grade
				+ ", gender=" + gender + "]";
	}
	
	// code값만 비교해서 같은 사람인지 아닌지 비교 
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

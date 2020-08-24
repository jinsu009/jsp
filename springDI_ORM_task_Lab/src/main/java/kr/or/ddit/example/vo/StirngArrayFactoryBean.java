package kr.or.ddit.example.vo;

import org.springframework.beans.factory.FactoryBean;

public class StirngArrayFactoryBean implements FactoryBean<String[]>{

	@Override
	public String[] getObject() throws Exception {
		
		return new String[] {"value1","value2"};
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return String[].class;
	}

	@Override
	public boolean isSingleton() {
		// scope 성격 : false = prototype , true = singleton
		return false;
	}

	
}

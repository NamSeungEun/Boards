package com.util.sqlMap;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapConfig {
	
	private static final SqlMapClient sqlMap; //xml에서 읽어온 데이터의 객체를 담을 것임
	
	
	static {  // 상수 초기화 처리 
		
		try {
			
			String resource = "com/util/sqlMap/sqlMapConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource); //이 두 개가 있어야 xml 파일을 읽어올 수 있음 
			
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);  // 그 데이터를 객체 생성함
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("sqlMap Error : " + e);
		}
	}
	
	public static SqlMapClient getSqlMapInstance() {
		return sqlMap;
	}

}


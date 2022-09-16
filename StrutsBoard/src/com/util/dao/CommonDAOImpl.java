package com.util.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.util.sqlMap.SqlMapConfig;

public class CommonDAOImpl implements CommonDAO{

	private SqlMapClient sqlMap;
	
	public CommonDAOImpl() {
		this.sqlMap = SqlMapConfig.getSqlMapInstance();
	}
	//객체 생성 
	public static CommonDAO getInstance() { //인터페이스로 구현한 클래스는 
		return new CommonDAOImpl();   // 자기 자신의 객체를 반환함 이게 뭔 개소리임
		//CommonDAO dao = new CommonDAOImpl();
	}
	
	@Override
	public void insertData(String id, Object value) {

		try {
			
			sqlMap.startTransaction();
			
			sqlMap.insert(id, value);
			
			sqlMap.commitTransaction();
			
		} catch (SQLException e) {
			System.out.print(e.toString());
		} finally {
			try {
				sqlMap.endTransaction();
			} catch (Exception e2) {
				
			}
		}
	}
	@Override
	public int updateData(String id, Object value) {

		int result = 0;
		
		try {
			
			sqlMap.startTransaction();
			
			result = sqlMap.update(id, value);
			
			sqlMap.commitTransaction();
			
		} catch (SQLException e) {
			System.out.print(e.toString());
		} finally {
			try {
				sqlMap.endTransaction();
			} catch (Exception e2) {
				
			}
		}

		return result;
	}
	@Override
	public int updateData(String id, Map<String, Object> map) {

		int result = 0;
		
		try {
			
			sqlMap.startTransaction();
			
			result = sqlMap.update(id, map);
			
			sqlMap.commitTransaction();
			
		} catch (SQLException e) {
			System.out.print(e.toString());
		} finally { // 무조건 실행
			
			try {
				sqlMap.endTransaction();
			} catch (Exception e2) {
				
			}
		}

		return result;
	}
	@Override
	public int deleteData(String id, Object value) {

		int result = 0;
		
		try {
			
			sqlMap.startTransaction();
			
			result = sqlMap.delete(id, value);
			
			sqlMap.commitTransaction();
			
		} catch (SQLException e) {
			System.out.print(e.toString());
		} finally { // 무조건 실행
			
			try {
				sqlMap.endTransaction();
			} catch (Exception e2) {
				
			}
		}

		return result;
	}
	@Override
	public int deleteData(String id, Map<String, Object> map) {

		int result = 0;
		
		try {
			
			sqlMap.startTransaction();
			
			result = sqlMap.delete(id, map);
			
			sqlMap.commitTransaction();
			
		} catch (SQLException e) {
			System.out.print(e.toString());
		} finally { // 무조건 실행
			
			try {
				sqlMap.endTransaction();
			} catch (Exception e2) {
				
			}
		}

		return result;
	}
	@Override
	public int deleteAllData(String id) {

		int result = 0;
		
		try {
			
			sqlMap.startTransaction();
			
			result = sqlMap.delete(id); //실제로 삭제하는 명령어 
			
			sqlMap.commitTransaction();
			
		} catch (SQLException e) {
			System.out.print(e.toString());
		} finally { // 무조건 실행
			
			try {
				sqlMap.endTransaction();
			} catch (Exception e2) {
				
			}
		}

		return result;
	}
	@Override
	public Object getReadData(String id) {

		try {
			
			return sqlMap.queryForObject(id);
			
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		return null;
	}
	@Override
	public Object getReadData(String id, Object value) {

		try {
			
			return sqlMap.queryForObject(id, value);
			
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		return null;
	}
	@Override
	public Object getReadData(String id, Map<String, Object> map) {
		
		try {
			
			return sqlMap.queryForObject(id,map);
			
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		return null;
	}
	@Override
	public int getIntValue(String id) {

		try {
			
			return ((Integer) sqlMap.queryForObject(id)).intValue(); // int 값으로 반환해줌
			
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		return 0;
	}
	@Override
	public int getIntValue(String id, Object value) {

		try {
			
			return ((Integer) sqlMap.queryForObject(id, value)).intValue(); // int 값으로 반환해줌
			
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		return 0;
	}
	@Override
	public int getIntValue(String id, Map<String, Object> map) {

		try {
			
			return ((Integer) sqlMap.queryForObject(id, map)).intValue(); // int 값으로 반환해줌
			
		} catch (Exception e) {
			System.out.print(e.toString());
		}
		return 0;
	}
	@SuppressWarnings("unchecked") // 노란 줄 감추기 
	@Override
	public List<Object> getListData(String id) {

		try {
			
			return (List<Object>)sqlMap.queryForList(id);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return null;
	}
	@Override
	public List<Object> getListData(String id, Object value) {

		try {
			
			return (List<Object>)sqlMap.queryForList(id, value);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return null;
	}
	@Override
	public List<Object> getListData(String id, Map<String, Object> map) {

		try {
			
			return (List<Object>)sqlMap.queryForList(id, map);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return null;
	}
	
	
	
}

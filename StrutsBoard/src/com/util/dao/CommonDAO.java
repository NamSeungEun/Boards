package com.util.dao;

import java.util.List;
import java.util.Map;

public interface CommonDAO {
	
	//데이터추가
	public void insertData(String id, Object value);
	
	//데이터 수정
	public int updateData(String id, Object value);
	public int updateData(String id, Map<String, Object> map);
	
	//데이터 삭제
	public int deleteData(String id, Object value);
	public int deleteData(String id, Map<String, Object> map);
	public int deleteAllData(String id); //쓸 일 없으나 모든 경우의 수 다 쓰는 중
	
	//레코드 가져오기
	public Object getReadData(String id);
	public Object getReadData(String id, Object value);
	public Object getReadData(String id, Map<String, Object> map);
	
	//하나의 레코드 가져오기
	public int getIntValue(String id);
	public int getIntValue(String id, Object value);
	public int getIntValue(String id, Map<String, Object> map);
	
	public List<Object> getListData(String id);
	public List<Object> getListData(String id, Object value);
	public List<Object> getListData(String id, Map<String, Object> map);


}







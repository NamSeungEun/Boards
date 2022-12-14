package com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdbc.dto.BoardDTO;

public class BoardDAO {

	private DataSource dataSource;
	
	
	public void setDataSource(DataSource dataSource) throws Exception {
		this.dataSource = dataSource;
		
		conn = dataSource.getConnection();
	}
	
	Connection conn = null;
	
	//num의 최대값 : insert 시 num을 가져오게 하는 코딩 
	public int getMaxNum() {
		
		int maxNum = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select nvl(max(num),0) from board"; //null인 경우 0으로 바꿔라 
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxNum = rs.getInt(1); // 실존 컬럼이 아니기 때문에 컬럼명이 아닌 자릿값을 써줌
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return maxNum;
	}
	
	//입력 
	public int insertData(BoardDTO dto) {  //인서트는 insertData와 getMaxNum 두 메서드가 필요하다. 
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			
			sql = "insert into board (num,name,pwd,email,subject,"
					+ "content,ipAddr,hitCount,created) "
					+ "values (?,?,?,?,?,?,?,0,sysdate)"; // hitCount 0으로 초기화 
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPwd());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getSubject());
			pstmt.setString(6, dto.getContent());
			pstmt.setString(7, dto.getIpAddr());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
		
	}
	
	//전체 데이터 갯수 구하기     / 데이터 검색을 위한 매개변수 주기 
	public int getDataCount(String searchKey, String searchValue) {
		
		int  totalCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			searchValue = "%" + searchValue + "%"; //특정 단어가 들어가면 다 검색이 되도록 함 
			
			sql = "select nvl(count(*),0) from board ";
			sql+= "where "+searchKey+" like ?";   // 서키치는 반드시 네임, 서브젝트,콘텐트 중에 하나가 들어가게 되니 고정해놓고 물음표는 값을 줄 수도 안줄 수도 있음
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchValue);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return totalCount;
		
	}
	
	
	
	
	//전체 데이터 가져오기                          / 데이터 검색을 위한 매개변수 주기 
	public List<BoardDTO> getLists(int start,int end, String searchKey, String searchValue ){
		
		List<BoardDTO> lists = new ArrayList<BoardDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			/* 감싸고 감싸고 감싸는 작업 : 원하는 페이지를 추출하는 select문
			 * select * from (
			select rownum rnum,data.* from (
			select num,name,subject from board order by num desc) data)
			where rnum>=2 and rnum<=3;*/
			
			searchValue = "%" + searchValue + "%";
			
			sql = "select * from (";
			sql+= "select rownum rnum, data.* from (";
			sql+= "select num,name,subject,hitCount,";
			sql+= "to_char(created,'YYYY-MM-DD') created ";
			sql+= "from board where " +searchKey+ " like ? ";
			sql+= "order by num desc) data) ";
			sql+= "where rnum>=? and rnum<=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchValue);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setHitCount(rs.getInt("hitCount"));
				dto.setCreated(rs.getString("created"));
				
				lists.add(dto);
			}
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}

	//num으로 조회한 한 개의 데이터
		public BoardDTO getReadData(int num){
			
			BoardDTO dto = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;
			
			try {

				sql= "select num,name,pwd,email,subject,content,";
				sql+= "ipAddr,hitCount,created ";
				sql+= "from board where num=?";

				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, num);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					dto = new BoardDTO();
					dto.setNum(rs.getInt("num"));
					dto.setName(rs.getString("name"));
					dto.setPwd(rs.getString("pwd"));
					dto.setEmail(rs.getString("email"));
					dto.setSubject(rs.getString("subject"));
					dto.setContent(rs.getString("content"));
					dto.setIpAddr(rs.getString("ipAddr"));
					dto.setHitCount(rs.getInt("hitCount"));
					dto.setCreated(rs.getString("created"));
					
				}
				rs.close();
				pstmt.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return dto;
		}
	
		//조회수 증가
		public int updateHitCount(int num) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql;
			
			try {
				
				sql = "update board set hitCount=hitCount+1 where num=?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, num);
				
				result = pstmt.executeUpdate();
				
				pstmt.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return result;
			
		}
		
		//수정
		public int updateData(BoardDTO dto) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql;
			
			try {
				
				sql = "update board set name=?,pwd=?,email=?,subject=?,"
						+ "content=? where num=?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, dto.getName());
				pstmt.setString(2, dto.getPwd());
				pstmt.setString(3, dto.getEmail());
				pstmt.setString(4, dto.getSubject());
				pstmt.setString(5, dto.getContent());
				pstmt.setInt(6, dto.getNum());
				
				result = pstmt.executeUpdate();
				
				pstmt.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return result;
			
		}
		
		//삭제 
		
		public int deleteData(int num) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql;
			
			try {
				
				sql ="delete board where num=?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, num);
				
				result = pstmt.executeUpdate();
				
				pstmt.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return result;
		}
		
		
		
		
		
		
}

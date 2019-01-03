package com.biz.grade.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.vo.StudentVO;

public class StudentDAOImp implements StudentDAO {

	Connection dbConn;
	
	public StudentDAOImp() {
		this.dbConnection();
	}
	
	public void dbConnection() {
		//dbConn 멤버변수를  초기화하는 메서드
		//dbConn 멤버변수 : db에 접속하기 위한 통로를 마련하고
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String strurl = "jdbc:oracle:thin:@localhost:1521:xe";
			String struser = "gradeUSER";
			String strpassword = "1234";
			
			dbConn = DriverManager.getConnection(strurl, struser, strpassword);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public int insert(StudentVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StudentVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentVO findByNum(String st_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentVO> findByName(String st_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Update(StudentVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String st_num) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void insertBulk(List<StudentVO> stdList) {
		// TODO Sevice에서 생선한 stdList를 매개변수로 받아 DB에  insertBulk하는 메서드
		
		String sql = " INSERT INTO tbl_student ";
		sql += " VALUES(?,?,?,?) ";
		
		PreparedStatement ps;
		
		try {
			ps = dbConn.prepareStatement(sql);
/*			for(StudentVO v : stdList) {
				ps.setString(1, v.getSt_num());
				ps.setString(2, v.getSt_name());
				ps.setString(3, v.getSt_tel());
				ps.setString(4, v.getSt_addr());
				
				ps.executeUpdate(); // 파일이 많아질수록 느려지고 오류날 가능성이 높아짐
			}
*/
			/*
			 * 벌크데이터를 insert할때는 1개의 데이터를 set 하고 exec...()을 실행하는 것은 매우 비 효율적이다.
			 * JAVA PrepareStatement에는 이처럼 bulk DATA를 insert할 때는 Batch처리를 한다.
			 * 
			 * batch처리란 ? 일괄처리방식
			 */
			
			for(StudentVO vo : stdList) {
				ps.setString(1, vo.getSt_num());
				ps.setString(2, vo.getSt_name());
				ps.setString(3, vo.getSt_tel());
				ps.setString(4, vo.getSt_addr());
				
				ps.addBatch();
			}
			ps.executeBatch(); //한꺼번에 모아서 보냄(update는 매번 하나씩 보냄)
			
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

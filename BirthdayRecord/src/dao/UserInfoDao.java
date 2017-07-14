package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.*;
import util.*;
public class UserInfoDao {
	public void add(UserInfo userInfo){
		try{
			Connection conn = JdbcUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into user_infot_table (id_phone_number,name,phone,email,nickname,password) values (?,?,?,?,?,?)");
			pstmt.setString(1,userInfo.getId_phone_number());
			pstmt.setString(2,userInfo.getName());
			pstmt.setString(3,userInfo.getPhone());
			pstmt.setString(4,userInfo.getEmail());
			pstmt.setString(5,userInfo.getNickname());
			pstmt.setString(6,userInfo.getPassword());
			pstmt.executeUpdate();
			JdbcUtil.close(pstmt,conn);
		}catch(SQLException e){
		    e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<UserInfo> findAll(){
		List<UserInfo> list = new ArrayList<UserInfo>();
		try{
			Connection conn = JdbcUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from user_infot_table");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				UserInfo userInfo = new UserInfo();
				userInfo.setId_phone_number(rs.getString(1));
				userInfo.setName(rs.getString(2));
				userInfo.setPhone(rs.getString(3));
				userInfo.setEmail(rs.getString(4));
				userInfo.setNickname(rs.getString(6));
				userInfo.setPassword(rs.getString(7));
			   list.add(userInfo);
			}

			JdbcUtil.close(pstmt,conn);
		}catch(SQLException e){
		    e.printStackTrace();
			throw new RuntimeException(e);
		}

		return list;
	}
	
	public UserInfo findById(int id){
		UserInfo userInfo = new UserInfo();
		try{
			Connection conn = JdbcUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from user_infot_table where id="+id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				userInfo.setId_phone_number(rs.getString(1));
				userInfo.setName(rs.getString(2));
				userInfo.setName(rs.getString(3));
				userInfo.setEmail(rs.getString(4));
				userInfo.setNickname(rs.getString(5));
				userInfo.setPassword(rs.getString(6));
			}
			JdbcUtil.close(pstmt,conn);
		}catch(SQLException e){
		    e.printStackTrace();
			throw new RuntimeException(e);
		}

		return userInfo;
	}
	
}

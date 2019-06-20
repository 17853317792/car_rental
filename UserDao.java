package cn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.bean.User;
import cn.bean.User2;
import cn.util.JdbcUtil;

public class UserDao {
//查询所有信息
	public ArrayList<User> findAll()
	{
		String sql="select * from aaa";
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<User> list=new ArrayList<User>();
		int flag=0;
		try {
			con = JdbcUtil.getConnection();
			stmt=con.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				
				User user=new User();
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setPeople(rs.getString("People"));
				list.add(user);
			}
			return list;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
// 
	public void insert(User user) {
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int flag=0;
		try {
			con = JdbcUtil.getConnection();
			//String sql = "insert into aaa(Username,Password,Email)"+"values("+ user.getUsername()+","+user.getPassword()+","+user.getEmail()+")";
			//stmt=con.prepareStatement(sql);
			//rs=stmt.executeQuery();
			String sql = "INSERT INTO aaa(Username,Password,Email,People) VALUES (?,?,?,?)"; 
			stmt=con.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPeople());
			stmt.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void update(User user) {
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int flag=0;
		try {
			con = JdbcUtil.getConnection();
			//String sql = "insert into aaa(Username,Password,Email)"+"values("+ user.getUsername()+","+user.getPassword()+","+user.getEmail()+")";
			//stmt=con.prepareStatement(sql);
			//rs=stmt.executeQuery();
			String sql = "Update aaa set Password=? where Username=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, user.getPassword());
			stmt.setString(2, user.getUsername());
			stmt.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

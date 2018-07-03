package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.DbConnect;
import beans.User;
import beans.User_info;

public class UserDao implements IsUserDao {

	@Override
	public User find_user(User u) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User u2 = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "select u_name,u_pwd from user where u_name=? or u_tel=?";
			pstm =con.prepareStatement(sql);
			pstm.setString(1, u.getName());
			pstm.setString(2, u.getName());
			rs = pstm.executeQuery();
			if(rs.next()){
				u2 = new User();
				u2.setName(rs.getString(1));
				u2.setPwd(rs.getString(2));		
			}
		}catch(Exception e){
			System.out.println("发生错误："+e);
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return u2;
	}

	@Override
	public boolean create_user(User u) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "insert into user(u_name,u_pwd,u_tel) values(?,?,?)";
			pstm =con.prepareStatement(sql);
			pstm.setString(1, u.getName());
			pstm.setString(2, u.getPwd());
			pstm.setString(3, u.getTel());
			pstm.executeUpdate();
		}catch(Exception e){
			System.out.println("发生错误："+e);
			return false;
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return true;
	}

	@Override
	public boolean update_pwd(User u) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "update user set u_pwd=? where u_name=?";
			pstm =con.prepareStatement(sql);
			pstm.setString(1, u.getPwd());
			pstm.setString(2, u.getName());
			pstm.executeUpdate();
		}catch(Exception e){
			System.out.println("发生错误："+e);
			return false;
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return true;
	}

	@Override
	public User_info self_info(String name) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User_info info2 = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "select u_nickname,u_age,u_sex,u_note from u_info,user where user.u_id=u_info.u_id and u_name=?";
			pstm =con.prepareStatement(sql);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			if(rs.next()){
				info2 = new User_info();
				info2.setNickname(rs.getString(1));
				info2.setAge(rs.getString(2));	
				info2.setSex(rs.getString(3));
				info2.setNote(rs.getString(4));
			}
		}catch(Exception e){
			System.out.println("发生错误："+e);
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return info2;
	}
	
	@Override
	public User_info find_info(String name) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User_info info2 = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "select u_nickname,u_age,u_sex,u_note from u_info where u_nickname=?";
			pstm =con.prepareStatement(sql);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			if(rs.next()){
				info2 = new User_info();
				info2.setNickname(rs.getString(1));
				info2.setAge(rs.getString(2));	
				info2.setSex(rs.getString(3));
				info2.setNote(rs.getString(4));
			}
		}catch(Exception e){
			System.out.println("发生错误："+e);
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return info2;
	}

	@Override
	public boolean update_info(User_info info,String name) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "update u_info set u_nickname=?,u_age=?,u_sex=?,u_note=? where u_id=(select u_id from user where u_name=?)";
			pstm =con.prepareStatement(sql);
			pstm.setString(1, info.getNickname());
			pstm.setString(2, info.getAge());
			pstm.setString(3, info.getSex());
			pstm.setString(4, info.getNote());
			pstm.setString(5, name);
			pstm.executeUpdate();
		}catch(Exception e){
			System.out.println("发生错误："+e);
			return false;
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return true;
	}

	@Override
	public boolean check_nickname(String nickname,String name) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "select * from u_info where u_nickname <>(select u_nickname from user,u_info where user.u_id=u_info.u_id and u_name=?) and u_nickname=?";
			pstm =con.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, nickname);
			rs = pstm.executeQuery();
			if(rs.next()){
				return false;	
			}
		}catch(Exception e){
			System.out.println("发生错误："+e);
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return true;
	}

	@Override
	public String find_token(String name) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String token = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "select token from user where u_name=?";
			pstm =con.prepareStatement(sql);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			if(rs.next()){
				token = rs.getString(1);	
			}
		}catch(Exception e){
			System.out.println("发生错误："+e);
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return token;
	}

	@Override
	public boolean update_token(String name,String token) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "update user set token=? where u_name=?";
			pstm =con.prepareStatement(sql);
			pstm.setString(1, token);
			pstm.setString(2, name);
			pstm.executeUpdate();
		}catch(Exception e){
			System.out.println("发生错误："+e);
			return false;
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return true;
	}

	@Override
	public int f_check(String name, String nickname) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int statue = 2;
		try{
			con = DbConnect.getDBconnection();
			String sql = "select statue from friend where u_id=(select u_id from user where u_name=?) and f_id=(select u_id from u_info where u_nickname=?)";
			pstm =con.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, nickname);
			rs = pstm.executeQuery();
			if(rs.next()){
				statue = rs.getInt(1);
			}
		}catch(Exception e){
			System.out.println("发生错误："+e);
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return statue;
	}

	@Override
	public boolean f_create(String name, String nickname) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "insert into friend(u_id,f_id,statue) values((select u_id from user where u_name=?),(select u_id from u_info where u_nickname=?),1)";
			pstm =con.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, nickname);
			pstm.executeUpdate();
		}catch(Exception e){
			System.out.println("发生错误："+e);
			return false;
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return true;
	}

	@Override
	public boolean f_update(String name, String nickname,int statue) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "update friend set statue=? where u_id=(select u_id from user where u_name=?) and f_id=(select u_id from u_info where u_nickname=?)";
			pstm =con.prepareStatement(sql);
			pstm.setInt(1, statue);
			pstm.setString(2, name);
			pstm.setString(3, nickname);
			pstm.executeUpdate();
		}catch(Exception e){
			System.out.println("发生错误："+e);
			return false;
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return true;
	}

	@Override
	public ArrayList<User> f_findAll(String name) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<User> fr = new ArrayList<User>();
		try{
			con = DbConnect.getDBconnection();
			String sql = "select u_nickname from u_info where u_id in (select f_id from friend where u_id=(select u_id from user where u_name=?) and statue=1)";
			pstm =con.prepareStatement(sql);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			while(rs.next()){
				User u = new User();
				u.setName(rs.getString(1));
				fr.add(u);
			}
		}catch(Exception e){
			System.out.println("发生错误："+e);
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return fr;
	}

}

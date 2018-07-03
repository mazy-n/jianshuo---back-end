package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Article;
import beans.Comments;
import beans.DbConnect;

public class ArticleDao implements IsArticleDao {

	@Override
	public boolean create_art(String name,String content) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "insert into article(u_id,a_content) values((select u_id from user where u_name=?),?)";
			pstm =con.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, content);
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
	public ArrayList<Article> find_all() throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Article> alist = new ArrayList<Article>();
		try{
			con = DbConnect.getDBconnection();
			String sql = "select a_id,u_nickname,a_content,a_data from u_info,article where u_info.u_id=article.u_id order by rand()";
			pstm =con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Article a = new Article();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setContent(rs.getString(3));
				a.setData(rs.getTimestamp(4));
				a.setCount(sel_comments(a.getId()));
				alist.add(a);
			}
		}catch(Exception e){
			System.out.println("发生错误："+e);
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return alist;
	}

	@Override
	public Article find_art(int a_id) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Article a2 = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "select u_nickname,a_content,a_data from u_info,article where u_info.u_id=article.u_id and a_id=?";
			pstm =con.prepareStatement(sql);
			pstm.setInt(1,a_id);
			rs = pstm.executeQuery();
			if(rs.next()){
				a2 = new Article();
				a2.setName(rs.getString(1));
				a2.setContent(rs.getString(2));
				a2.setData(rs.getTimestamp(3));
				a2.setCount(sel_comments(a_id));
			}
		}catch(Exception e){
			System.out.println("发生错误："+e);
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return a2;
	}

	@Override
	public boolean delete_art(int a_id) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "delete from article where a_id = ?";
			pstm =con.prepareStatement(sql);
			pstm.setInt(1, a_id);
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
	public boolean delete_art_coms(int a_id) throws Exception {
			// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "delete from a_comments where a_id = ?";
			pstm =con.prepareStatement(sql);
			pstm.setInt(1, a_id);
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
	public boolean create_coms(Comments coms,int a_id) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "insert into a_comments(a_id,u_id,c_content) values(?,(select u_id from user where u_name=?),?)";
			pstm =con.prepareStatement(sql);
			pstm.setInt(1, a_id);
			pstm.setString(2, coms.getName());
			pstm.setString(3, coms.getContent());
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
	public ArrayList<Comments> find_coms(int a_id) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Comments> clist = new ArrayList<Comments>();
		try{
			con = DbConnect.getDBconnection();
			String sql = "select c_id,u_nickname,c_content,c_data from u_info,a_comments where u_info.u_id=a_comments.u_id and a_id=? order by c_data desc";
			pstm =con.prepareStatement(sql);
			pstm.setInt(1, a_id);
			rs = pstm.executeQuery();
			while(rs.next()){
				Comments c = new Comments();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setContent(rs.getString(3));
				c.setData(rs.getTimestamp(4));
				clist.add(c);
			}
		}catch(Exception e){
			System.out.println("发生错误："+e);
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return clist;
	}

	@Override
	public boolean delete_coms(int c_id) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try{
			con = DbConnect.getDBconnection();
			String sql = "delete from a_comments where c_id=? ";
			pstm =con.prepareStatement(sql);
			pstm.setInt(1, c_id);
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
	public int sel_comments(int a_id) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count=0;
		try{
			con = DbConnect.getDBconnection();
			String sql = "select count(*) from a_comments where a_id=?";
			pstm =con.prepareStatement(sql);
			pstm.setInt(1,a_id);
			rs = pstm.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		}catch(Exception e){
			System.out.println("发生错误："+e);
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return count;
	}

	@Override
	public ArrayList<Article> f_fand_art(String name) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Article> alist = new ArrayList<Article>();
		try{
			con = DbConnect.getDBconnection();
			String sql = "select a_id,u_nickname,a_content,a_data from u_info,article where u_info.u_id=article.u_id and article.u_id in (select f_id from friend where u_id=(select u_id from user where u_name=?) and statue=1)order by a_data desc";
			pstm =con.prepareStatement(sql);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			while(rs.next()){
				Article a = new Article();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setContent(rs.getString(3));
				a.setData(rs.getTimestamp(4));
				a.setCount(sel_comments(a.getId()));
				alist.add(a);
			}
		}catch(Exception e){
			System.out.println("发生错误："+e);
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return alist;
	}

	@Override
	public ArrayList<Article> s_fand_art(String name) throws Exception {
		// TODO 自动生成的方法存根
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Article> alist = new ArrayList<Article>();
		try{
			con = DbConnect.getDBconnection();
			String sql = "select a_id,u_nickname,a_content,a_data from u_info,article where u_info.u_id=article.u_id and article.u_id =(select u_id from user where u_name=?) order by a_data desc";
			pstm =con.prepareStatement(sql);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			while(rs.next()){
				Article a = new Article();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setContent(rs.getString(3));
				a.setData(rs.getTimestamp(4));
				a.setCount(sel_comments(a.getId()));
				alist.add(a);
			}
		}catch(Exception e){
			System.out.println("发生错误："+e);
		}finally{
			DbConnect.closeDB(con, pstm, rs);
		}
		return alist;
	}

	

}

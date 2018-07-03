package dao;

import java.util.ArrayList;

import beans.Article;
import beans.Comments;

public interface IsArticleDao {
	public abstract boolean create_art(String name,String content) throws Exception;
	public abstract ArrayList<Article>  find_all() throws Exception;
	public abstract Article find_art(int a_id) throws Exception;
	public abstract boolean delete_art_coms(int a_id) throws Exception;
	public abstract boolean delete_art(int a_id) throws Exception;
	
	public abstract ArrayList<Article> f_fand_art(String name) throws Exception;
	public abstract ArrayList<Article> s_fand_art(String name) throws Exception;
	
	public abstract int sel_comments(int a_id) throws Exception;
	public abstract boolean create_coms(Comments coms,int a_id) throws Exception;
	public abstract ArrayList<Comments> find_coms(int a_id) throws Exception;
	public abstract boolean delete_coms(int c_id) throws Exception;
}

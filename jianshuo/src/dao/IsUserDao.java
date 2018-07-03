package dao;

import java.util.ArrayList;

import beans.User;
import beans.User_info;

public interface IsUserDao {
	public abstract User find_user(User u) throws Exception;
	public abstract boolean create_user(User u) throws Exception;
	public abstract boolean update_pwd(User u) throws Exception;
	
	public abstract String find_token(String name) throws Exception;
	public abstract boolean update_token(String name,String token) throws Exception;
	
	public abstract User_info self_info(String name) throws Exception;
	public abstract User_info find_info(String name) throws Exception;
	public abstract boolean update_info(User_info info,String name) throws Exception;
	public abstract boolean check_nickname(String nickname,String name) throws Exception;
	
	public abstract int f_check(String name,String nickname) throws Exception;
	public abstract boolean f_create(String name,String nickname) throws Exception;
	public abstract boolean f_update(String name,String nickname,int statue) throws Exception;
	public abstract ArrayList<User> f_findAll(String name) throws Exception;
}

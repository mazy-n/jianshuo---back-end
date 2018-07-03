package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import beans.MD5_pwd;
import beans.Token;
import beans.User;
import dao.IsUserDao;
import dao.UserDao;

public class login extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public login() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setHeader("Access-Control-Allow-Credentials", "true");
//		response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");
//		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		response.setHeader("Access-Control-Allow-Origin", "*");  
//		response.setHeader("Access-Control-Allow-Methods", "POST");  
//		response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");  
		response.setContentType("text/html;charset=utf-8");  
		response.setCharacterEncoding("utf-8"); 
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		JSONObject res = new JSONObject();
		JSONObject user = null;
		IsUserDao ud = new UserDao();
		if(request.getParameter("user")!=null){
			try {
				res.put("result", false);
				user = new JSONObject(request.getParameter("user"));
				User u1 = new User();
				u1.setName(user.getString("name"));
				User u2 = ud.find_user(u1);
				if(u2!=null){
					MD5_pwd md = new MD5_pwd();
					String pwd = md.MD5(user.getString("pwd"));
					if(pwd.equals(u2.getPwd())){
						String token = Token.createJWT(u2.getName());
						boolean f = ud.update_token(u2.getName(), token);
						if(f){
							res.put("name", u2.getName());
							res.put("result", true);
							res.put("token", token);
						}
					}
				}					
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		System.out.println(res);
		out.write(res.toString());
		out.flush();
		out.close();
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}

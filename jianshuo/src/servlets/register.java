package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import beans.MD5_pwd;
import beans.User;
import dao.IsUserDao;
import dao.UserDao;

public class register extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public register() {
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
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		JSONObject res = new JSONObject();
		JSONObject user = null;
		IsUserDao ud = new UserDao();
		if(request.getParameter("user")!=null){
			try {
				res.put("isName", false);
				res.put("isTel", false);
				res.put("all", false);
				user = new JSONObject(request.getParameter("user"));
				User u1 = new User();
				u1.setName(user.getString("name"));
				if(ud.find_user(u1)==null){
					res.put("isName", true);
					User u2 = new User();
					u2.setName(user.getString("tel"));
					if(ud.find_user(u2)==null){
						res.put("isTel", true);
						User u = new User();
						u.setName(user.getString("name"));
						u.setTel(user.getString("tel"));
						MD5_pwd md = new MD5_pwd();
						u.setPwd(md.MD5(user.getString("pwd")));
						boolean f = ud.create_user(u);
						if(f){
							res.put("all", true);
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

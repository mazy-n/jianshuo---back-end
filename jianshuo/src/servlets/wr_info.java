package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import beans.User_info;
import dao.IsUserDao;
import dao.UserDao;

public class wr_info extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public wr_info() {
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
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject res = new JSONObject();
		IsUserDao ud = new UserDao();
		String name = null;
		String token = null;
		if(request.getParameter("name")!=null && request.getParameter("token")!=null){
			name = request.getParameter("name");
			token = request.getParameter("token");
			try {
				String tok = ud.find_token(name);
				if(tok!=null&&tok!="0"){
					if(tok.equals(token)){
						res.put("admin", true);
						User_info inf = new User_info();
						JSONObject info = new JSONObject(request.getParameter("info"));
						inf.setNickname(info.getString("nickname"));
						inf.setAge(info.getString("age"));
						inf.setSex(info.getString("sex"));
						inf.setNote(info.getString("note"));
						if(ud.check_nickname(inf.getNickname(),name)){
							res.put("name", true);
							boolean f = ud.update_info(inf, name);
							res.put("update", f);
						}else{
							res.put("name", false);
						}
					}
				}else{
					res.put("admin",false);
				}
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
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

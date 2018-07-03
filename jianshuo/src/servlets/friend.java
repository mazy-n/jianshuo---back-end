package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import beans.Article;
import beans.User;
import dao.ArticleDao;
import dao.IsArticleDao;
import dao.IsUserDao;
import dao.UserDao;
import net.sf.json.JSONArray;

public class friend extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public friend() {
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
		int way;
		int statue;
		if(request.getParameter("name")!=null && request.getParameter("token")!=null && request.getParameter("way")!=null){
			name = request.getParameter("name");
			token = request.getParameter("token");
			way = Integer.parseInt(request.getParameter("way"));
			try {
				String tok = ud.find_token(name);
				if(tok!=null&&tok!="0"){
					if(tok.equals(token)){
						res.put("admin", true);
						if(way==1){
							if(request.getParameter("nickname")!=null && request.getParameter("statue")!=null){
								statue = Integer.parseInt(request.getParameter("statue"));
								String nickname = request.getParameter("nickname");
								boolean f = false;
								if(statue==2){
									f = ud.f_create(name, nickname);
									res.put("create", f);
								}else if(statue==1){
									f = ud.f_update(name, nickname, 0);
									res.put("update_cancel", f);
								}else{
									f = ud.f_update(name, nickname, 1);
									res.put("update_gz", f);
								}
							}
						}else if(way==2){
							ArrayList<User> f = ud.f_findAll(name);
							JSONArray friend = JSONArray.fromObject(f);
							res.put("friend", friend);
						}else if(way==3){
							IsArticleDao ad = new ArticleDao();
							ArrayList<Article> f_art = ad.f_fand_art(name);
							JSONArray fr_art = JSONArray.fromObject(f_art);
							res.put("f_art", fr_art);
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

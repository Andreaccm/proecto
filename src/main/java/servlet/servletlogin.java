package servlet;
import helpers.connectDB;
import helpers.md5;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cotrollers.loginB;
import cotrollers.perfiluser;

@WebServlet("/servletlogin")
public class servletlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private connectDB db;
	
	public void init() {
		db = new connectDB();
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pwd1 = req.getParameter("pwd");
		String pwd;
		
		md5 en = new md5();
		pwd = en.ecnode(pwd1);
		
		loginB lb = new loginB();
		lb.setEmail(email);
		perfiluser.email = email;
		lb.setPwd(pwd);
		
			try {
				if(db.login(lb)) {
					HttpSession session = req.getSession();
					session.setAttribute("email",email);
					res.sendRedirect("perfil.html");
				}else {

					res.sendRedirect("login.html");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
	}

}
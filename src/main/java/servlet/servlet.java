package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import helpers.JSONManage;
import helpers.connectDB;
import helpers.md5;

@WebServlet("/servlet")

public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	connectDB db = new connectDB();
	md5 encrypt = new md5();
	
		String name = req.getParameter("name");
		String user = req.getParameter("nick"); 
		String email = req.getParameter("email");
		String pwd1 = req.getParameter("pwd");
		String pwd;
		
		pwd = encrypt.ecnode(pwd1);
	
			db.register(name, user, email, pwd);
			res.sendRedirect("login.html");
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}


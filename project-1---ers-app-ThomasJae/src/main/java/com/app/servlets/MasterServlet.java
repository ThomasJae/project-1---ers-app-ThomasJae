package com.app.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException{
		//System.out.println("in MasterServlet, doGet");
		req.getRequestDispatcher(RequestHelper.process(req)).forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException{
		//System.out.println("in MasterServlet, doPost");
		String process = RequestHelper.process(req);
		RequestDispatcher dispatch = req.getRequestDispatcher(process);
		if (process.equals("home.change"))
			res.sendRedirect("http://localhost:8080/project-1---ers-app-ThomasJae/resources/html/home.html");
		else if (process.equals("index.change"))
			res.sendRedirect("http://localhost:8080/project-1---ers-app-ThomasJae/resources/html/index.html");
		else if (process.equals("homeplus.change"))
			res.sendRedirect("http://localhost:8080/project-1---ers-app-ThomasJae/resources/html/homePlus.html");
		else if (process.equals("wrongcreds.change"))
			res.sendRedirect("http://localhost:8080/project-1---ers-app-ThomasJae/resources/html/unsuccessfulLogin.html");
		else if (process.equals("logout.change"))
			res.sendRedirect("http://localhost:8080/project-1---ers-app-ThomasJae/resources/html/logoutSuccess.html");
	}
}

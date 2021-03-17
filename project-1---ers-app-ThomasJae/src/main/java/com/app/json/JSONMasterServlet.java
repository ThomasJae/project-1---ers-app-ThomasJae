package com.app.json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSONMasterServlet extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException{
		JSONRequestHelper.process(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException{
		JSONRequestHelper.process(req, res);
	}
}

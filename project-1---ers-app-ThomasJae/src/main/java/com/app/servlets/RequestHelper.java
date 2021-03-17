package com.app.servlets;

import javax.servlet.http.HttpServletRequest;

import com.app.controllers.CreateController;
import com.app.controllers.HomeController;
import com.app.controllers.LoginController;
import com.app.controllers.LogoutController;
import com.app.controllers.NewUserController;
import com.app.controllers.ResolveController;

public class RequestHelper {

	public static String process(HttpServletRequest req) {
		//System.out.println(req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/project-1---ers-app-ThomasJae/login.change":
			//System.out.println("In login.change rhelper");
			return LoginController.login(req);
		case "/project-1---ers-app-ThomasJae/home.change":
			//System.out.println("In home.change rhelper");
			return HomeController.home(req);
		case "/project-1---ers-app-ThomasJae/logout.change":
			//System.out.println("In logout.change rhelper");
			return LogoutController.logout(req);
		case "/project-1---ers-app-ThomasJae/create.change":
			//System.out.println("In create.change rhelper");
			return CreateController.create(req);
		case "/project-1---ers-app-ThomasJae/resolve.change":
			return ResolveController.resolve(req);
		case "/project-1---ers-app-ThomasJae/newuser.change":
			return NewUserController.newUser(req);
		default:
			//System.out.println("In default");
			return "resources/html/unsuccessfulLogin.html";
		}
	}
}

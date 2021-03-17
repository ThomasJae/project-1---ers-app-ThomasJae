package com.app.controllers;

import javax.servlet.http.HttpServletRequest;

public class HomeController {

	public static String home(HttpServletRequest req) {
		return "resources/html/home.html";
	}
}

package com.app.json;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.beans.User;
import com.app.beans.Ticket;
import com.app.beans.UserRole.URole;
import com.app.controllers.LoginController;
import com.app.beans.ReimbursementStatus.RStatus;
import com.app.beans.ReimbursementType.RType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONRequestHelper {

	public static void process(HttpServletRequest req, HttpServletResponse res)
		throws JsonProcessingException, IOException{
		//System.out.println(req.getRequestURI()); 
		switch(req.getRequestURI()) {
		case "/project-1---ers-app-ThomasJae/user.json":
			UserController.userFinder(req, res);
			break;
		case "/project-1---ers-app-ThomasJae/list.json":
			ListController.listFinder(req, res);
			break;
		case "/project-1---ers-app-ThomasJae/listplus.json":
			ListPlusController.listFinder(req, res);
			break;
		default:
			
		}
	}
}

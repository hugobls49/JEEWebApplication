package com.octest.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePage implements Action{
	
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String buttonPage = request.getParameter("buttonPage");
        if(buttonPage.equals("Team")) {
        	return "/WEB-INF/team.jsp";
        }
		return "/WEB-INF/index.jsp";
	}

}

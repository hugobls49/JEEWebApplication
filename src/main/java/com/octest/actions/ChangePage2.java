package com.octest.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePage2 implements Action{

	
	@Override
	public HttpServletRequest execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String buttonPage = request.getParameter("buttonPage");
        if(buttonPage.equals("Index")) {
        	request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
        	return request;
        }
        request.getRequestDispatcher("/WEB-INF/team.jsp").forward(request, response);
		return request;
	}

}


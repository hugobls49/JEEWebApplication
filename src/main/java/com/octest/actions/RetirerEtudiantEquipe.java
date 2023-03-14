package com.octest.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.dao.DaoException;
import com.octest.dao.DaoFactory;
import com.octest.dao.StudentDao;

public class RetirerEtudiantEquipe implements Action{

	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DaoFactory daoFactory = DaoFactory.getInstance();
	    StudentDao studentDao = daoFactory.StudentDao();
	    int studentId = Integer.parseInt(request.getParameter("studentId"));
	    
	    try {
	        studentDao.removeStudentFromTeam(studentId);
	    } catch (DaoException e) {
	        e.printStackTrace();
	    }
	    
		return response;
	}

}

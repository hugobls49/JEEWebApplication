package com.octest.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.beans.Student;
import com.octest.dao.DaoException;
import com.octest.dao.DaoFactory;
import com.octest.dao.StudentDao;

public class AfficherEtudiantParEquipe implements Action{
	
	private StudentDao studentDao;
	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		 this.studentDao = daoFactory.StudentDao();
		try {
            List<Student> studentsByTeam = studentDao.getStudentsByTeam();
            request.setAttribute("studentsByTeam", studentsByTeam);
        } catch (DaoException e) {
            request.setAttribute("errorMessage", "Erreur lors de la récupération des étudiants par équipe.");
        }
		
		return response;
	}

}

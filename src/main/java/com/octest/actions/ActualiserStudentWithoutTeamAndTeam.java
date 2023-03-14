package com.octest.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.beans.Student;
import com.octest.beans.Team;
import com.octest.dao.DaoException;
import com.octest.dao.DaoFactory;
import com.octest.dao.StudentDao;
import com.octest.dao.TeamDao;

public class ActualiserStudentWithoutTeamAndTeam implements Action{

	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DaoFactory daoFactory = DaoFactory.getInstance();
	    StudentDao studentDao = daoFactory.StudentDao();
	    TeamDao teamDao = daoFactory.TeamDao();
		List<Student> students = studentDao.getStudentsWithoutTeam();
        List<Team> teams = teamDao.getTeams();
        try {
			List<Student> studentTeam = studentDao.getStudentsByTeam();
			request.setAttribute("studentsByTeam", studentTeam);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.setAttribute("studentsWithoutTeam", students);
        request.setAttribute("teams", teams);
        
        
		return response;
	}

}

package com.octest.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.beans.Student;
import com.octest.dao.DaoFactory;
import com.octest.dao.StudentDao;

public class AjouterEtudiant implements Action {

	private StudentDao studentDao;
	
	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DaoFactory daoFactory = DaoFactory.getInstance();
        this.studentDao = daoFactory.StudentDao();
        Student student = new Student();
        String ajouter = request.getParameter("ajouter");
        
        if (ajouter != null && ajouter.equals("Ajouter")) {
        	String name = request.getParameter("Name");
            String firstName = request.getParameter("FirstName");
            int idGender = Integer.parseInt(request.getParameter("idGender"));
            int idSite = Integer.parseInt(request.getParameter("idSite"));
            int idFormation = Integer.parseInt(request.getParameter("idFormation"));
           
            student.setName(name);
            student.setFirstName(firstName);
            student.setIdGender(idGender);
            student.setIdSite(idSite);
            student.setIdFormation(idFormation);
            student.setIdTeam(0);
            
            studentDao.ajouter(student);
        }
		return response;
	}

}
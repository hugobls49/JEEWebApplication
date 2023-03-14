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
import com.octest.dao.TeamDao;

public class ModifierNomEquipe implements Action{

	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DaoFactory daoFactory = DaoFactory.getInstance();
	    TeamDao teamDao = daoFactory.TeamDao();
		int idTeam = Integer.parseInt(request.getParameter("idTeam"));
		String newName = request.getParameter("newName");
        
		try {
		    teamDao.updateTeamName(idTeam, newName);
		    request.setAttribute("message", "Le nom de l'équipe a été modifié avec succès.");
		} catch (DaoException e) {
		    request.setAttribute("message", "Erreur lors de la modification du nom de l'équipe : " + e.getMessage());
		}
		List<Team> teams = teamDao.getTeams();
        request.setAttribute("teams", teams);
		
		
		return response;
	}

}

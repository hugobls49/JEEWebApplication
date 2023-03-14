package com.octest.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.beans.Team;
import com.octest.dao.DaoException;
import com.octest.dao.DaoFactory;
import com.octest.dao.TeamDao;

public class selectNbTeam implements Action{
	
	private TeamDao teamDao;
	@Override
	public Object execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DaoFactory daoFactory = DaoFactory.getInstance();
	    this.teamDao = daoFactory.TeamDao();
	    int nbEquipes = Integer.parseInt(request.getParameter("nb-equipes"));
	   
		try {
			teamDao.ajouterEquipes(nbEquipes);
		} catch (DaoException e) {
			e.printStackTrace();
		}

	    List<Team> teams = teamDao.getTeams();
	    request.setAttribute("teams", teams);
		
		return response;	
	}	 
 
}
	